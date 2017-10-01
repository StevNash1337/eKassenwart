package de.naju.ahlen.persistence;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import de.naju.ahlen.persistence.model.project.Project;
import de.naju.ahlen.util.ProjectUtil;
import de.naju.ahlen.util.orm.AbstractRoutingConnectionSource;
import de.naju.ahlen.util.orm.RoutingConnectionSource;
import de.naju.ahlen.util.orm.TableFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import java.sql.SQLException;
import java.util.List;

/**
 * Class that provide a bean for the {@link RoutingConnectionSource} that handles each project.
 */
@Controller
public class ConnectionController {

    @Autowired
    private Dao<Project, String> projectDao;

    @Bean
    public AbstractRoutingConnectionSource connectionSource() throws SQLException {
        List<Project> projects = projectDao.queryForAll();
        AbstractRoutingConnectionSource routingConnectionSource = new RoutingConnectionSource();
        for (Project project : projects) {
            String name = project.getName();
            ConnectionSource connectionSource =
                    new JdbcConnectionSource(
                            ProjectUtil.getUrl(project),
                            ProjectUtil.getUsername(project),
                            ProjectUtil.getPassword(project));
            routingConnectionSource.addConnectionSource(name, connectionSource);

            for (Class<?> clazz : TableFactory.getModelClasses()) {
                TableUtils.createTableIfNotExists(connectionSource, clazz);
            }
        }

        ConnectionSource mockConnectionSource = new JdbcConnectionSource(
                "jdbc:h2:mem:mock");
        for (Class<?> clazz : TableFactory.getModelClasses()) {
            TableUtils.createTableIfNotExists(mockConnectionSource, clazz);
        }
        routingConnectionSource.setDefaultConnectionSource(mockConnectionSource);

        return routingConnectionSource;
    }
}
