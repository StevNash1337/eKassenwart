package de.naju.ahlen.util.orm;

import com.j256.ormlite.table.DatabaseTable;
import de.naju.ahlen.persistence.model.project.Project;
import org.reflections.Reflections;
import java.util.Set;

/**
 * This class gets a list with all database tables, to be created.
 */
public class TableFactory {

    public static Set<Class<?>> getModelClasses() {
        Reflections reflections = new Reflections("de.naju.ahlen.persistence.model");
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(DatabaseTable.class);

        // remove class Project, because it is not wanted for every project.
        annotated.remove(Project.class);

        return annotated;
    }
}
