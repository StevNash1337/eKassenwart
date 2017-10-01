package de.naju.ahlen.gui.view.project;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.vaadin.server.UserError;
import de.naju.ahlen.gui.MainWindowController;
import de.naju.ahlen.gui.view.base.BaseController;
import de.naju.ahlen.persistence.model.project.Project;
import de.naju.ahlen.util.ProjectUtil;
import de.naju.ahlen.util.orm.AbstractRoutingConnectionSource;
import de.naju.ahlen.util.orm.CurrentProject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.sql.SQLException;
import java.util.List;

/**
 * Ein Controller für die Projekte. Läuft jetzt erstmal. Nicht unter Windows getestet.
 */
@Controller
public class ProjectController extends BaseController<Project, String> {

    private static final Logger logger = LoggerFactory.getLogger(ProjectController.class);

    @Autowired
    MainWindowController mainWindowController;

    @Autowired
    AbstractRoutingConnectionSource connectionSource;

    private Project beforeUpdate;
    private boolean iconChanged = false;

    public ProjectController() {
        view = new ProjectView(this);
        form = new ProjectForm(this);
    }

    public void buttonActivateClicked() {
        logger.info("Button activate clicked!");

        Project project = view.getSelectedItem();
        mainWindowController.setMenuCaption(project.getFullName(), ProjectUtil.getIcon(project));
        CurrentProject.setCurrentProject(project);
        mainWindowController.showMenuItems();
    }

    @Override
    public void buttonEditClicked() {
        logger.info("Button edit clicked");

        Project project = view.getSelectedItem();

        beforeUpdate = project.copy();

        form.setEntity(project);
        form.openInModalPopup();
    }

    @Override
    public void buttonDeleteClicked() {
        logger.info("Button delete clicked");

        Project project = view.getSelectedItem();
        String key = project.getName();

        try {
            dao.delete(project);
            ProjectUtil.deleteProjectStructure(project);
            connectionSource.removeConnectionSource(key);
            if (CurrentProject.getCurrentProject() == project) {
                mainWindowController.resetSideMenu();
                CurrentProject.setCurrentProject(null);
                mainWindowController.hideMenuItemsExceptProject();
            }

        } catch (SQLException e) {
            logger.warn("Unable to delete Project from database");
            e.printStackTrace();
        } catch (IOException e) {
            logger.warn("Error deleting folder");
            e.printStackTrace();
        }
        updateGridItems();
    }

    @Override
    public void updateButtons() {
        super.updateButtons();
        if (!view.isSelected()) {
            ((ProjectView)view).setButtonActivateEnabled(false);
        } else {
            ((ProjectView)view).setButtonActivateEnabled(true);
        }
    }

    @Override
    public void formSaved(Project entity) {
        // new entity
        if (beforeUpdate == null) {
            newEntitySaved(entity);
        }
        // existing entity with key change
        else if (!entity.getName().equals(beforeUpdate.getName())) {
            existingProjectSavedWithKeyChange(entity);
        }
        // existing entity without key change
        else {
            existingProjectSavedWithoutKeyChange(entity);
        }
        // icon changed, move to right folder
        if (iconChanged) {
            // icon is located at ./icon.png
            moveIcon(entity);
        }
        // if activated project is changed, update the side menu
        if (CurrentProject.getCurrentProject() == entity) {
            mainWindowController.setMenuCaption(entity.getFullName(), ProjectUtil.getIcon(entity));
        }
        // update the grid
        updateGridItems();
        // close popup
        form.closePopup();
        beforeUpdate = null;
    }

    private void moveIcon(Project entity) {
        try {
            ProjectUtil.moveIcon(entity);
        } catch (IOException e) {
            e.printStackTrace();
            form.setComponentError(
                    new UserError(String.format("Error moving icon from %s to %s",
                            "./icon.png", String.format("%s/icon.png", ProjectUtil.getPath(entity)))));
        }

        iconChanged = false;
    }

    private void existingProjectSavedWithKeyChange(Project entity) {
        String sourceKey = beforeUpdate.getName();
        String targetKey = entity.getName();
        try {

            logger.info(String.format("Moving folder %s to %s",
                    ProjectUtil.getPath(beforeUpdate), ProjectUtil.getPath(entity)));

            ProjectUtil.moveProjectStructure(beforeUpdate, entity);

            // closing connection source
            connectionSource.getConnectionSource(sourceKey).close();
            // removing connection source from pool
            connectionSource.removeConnectionSource(sourceKey);

            // creating new connection source
            ConnectionSource newConnectionSource = new JdbcConnectionSource(
                    String.format("jdbc:h2:file:./Projekte/%s/%s", targetKey, targetKey));
            // adding connection source to pool
            connectionSource.addConnectionSource(targetKey, newConnectionSource);
            // update db
            dao.deleteById(sourceKey);
            dao.createOrUpdate(entity);
        } catch (IOException e) {
            e.printStackTrace();
            form.setComponentError(
                    new UserError(String.format("Error closing connection source %s or moving folders",
                            connectionSource.getConnectionSource(sourceKey).toString())));
        } catch (SQLException e) {
            e.printStackTrace();
            form.setComponentError(
                    new UserError(String.format("Error creating connection source %s",
                            connectionSource.getConnectionSource(targetKey).toString())));
        }
    }

    private void existingProjectSavedWithoutKeyChange(Project project) {
        String targetKey = project.getName();
        try {
            dao.createOrUpdate(project);
        } catch (SQLException e) {
            e.printStackTrace();
            form.setComponentError(
                    new UserError(String.format("Error saving project %s to database", targetKey)));
        }
    }

    private void newEntitySaved(Project project) {
        // creating folder
        String targetKey = project.getName();
        String targetPathString = ProjectUtil.getPath(project);
        logger.info(String.format("Creating folder %s", targetPathString));
        try {
            ProjectUtil.createProjectStructure(project);
            // creating new connection source
            ConnectionSource connectionSource = new JdbcConnectionSource(
                    String.format("jdbc:h2:file:./Projekte/%s/%s", targetKey, targetKey));
            // adding connection source to pool
            this.connectionSource.addConnectionSource(targetKey, connectionSource);
            // update db
            dao.createOrUpdate(project);
        } catch (FileAlreadyExistsException e) {
            e.printStackTrace();
            form.setComponentError(
                    new UserError(String.format("Folder %s already exists", ProjectUtil.getPath(project))));
        } catch (SQLException e) {
            e.printStackTrace();
            form.setComponentError(
                    new UserError(String.format("Error inserting project %s to database", targetKey)));
        } catch (IOException e) {
            e.printStackTrace();
            form.setComponentError(
                    new UserError(String.format("Error creating folder %s", ProjectUtil.getPath(project))));
        }
    }

    @Override
    public void formCanceled() {
        beforeUpdate = null;
        updateGridItems();
        form.closePopup();
    }

    @Override
    @Autowired
    public void setDao(Dao<Project, String> projectDao) {
        this.dao = projectDao;
    }

    @Override
    @PostConstruct
    protected void initData() {
        updateGridItems();
    }

    public boolean existsName(String name) {
        try {
            List<Project> projects = dao.queryForAll();
            for (Project project : projects) {
                if (project.getName().equals(name)) {
                    // if name was edited but didn't change
                    if (beforeUpdate != null & beforeUpdate.getName().equals(name)) {
                        continue;
                    }
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void setIconChanged(boolean b) {
        this.iconChanged = b;
    }
}
