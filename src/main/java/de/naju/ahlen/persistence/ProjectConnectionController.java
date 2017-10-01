package de.naju.ahlen.persistence;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import de.naju.ahlen.persistence.model.project.Project;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import java.sql.SQLException;

/**
 * Class that provide a bean for the {@link ConnectionSource} that handles the projects.
 */
@Controller
public class ProjectConnectionController {

    private static final String projectDatabaseUrl = "jdbc:h2:file:./projects";
    private static final String projectDatabaseUsername = "";
    private static final String projectDatabasePassword = "";

    @Bean
    public ConnectionSource projectConnectionSource() throws SQLException {
        ConnectionSource projectConnectionSource =
                new JdbcConnectionSource(projectDatabaseUrl, projectDatabaseUsername, projectDatabasePassword);
        TableUtils.createTableIfNotExists(projectConnectionSource, Project.class);

        return projectConnectionSource;
    }
}
