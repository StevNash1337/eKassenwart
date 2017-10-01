package de.naju.ahlen.util.orm;

import de.naju.ahlen.persistence.model.project.Project;

/**
 * Class that holds the currently activated project.
 */
public class CurrentProject {

    private static Project currentProject = null;

    public static void setCurrentProject(Project project) {
        currentProject = project;
    }

    public static Project getCurrentProject() {
        return currentProject;
    }
}
