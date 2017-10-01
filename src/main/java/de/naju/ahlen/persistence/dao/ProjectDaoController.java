package de.naju.ahlen.persistence.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import de.naju.ahlen.persistence.model.project.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import java.sql.SQLException;

/**
 * Class that provides beans for the dao that handles the projects.
 */
@Controller
public class ProjectDaoController {

    @Autowired
    private ConnectionSource projectConnectionSource;

    @Bean
    public Dao<Project, String> projectDao() throws SQLException {
        return DaoManager.createDao(projectConnectionSource, Project.class);
    }
}
