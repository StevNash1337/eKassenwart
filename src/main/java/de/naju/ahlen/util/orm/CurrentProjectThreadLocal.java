package de.naju.ahlen.util.orm;

public class CurrentProjectThreadLocal {

    private static ThreadLocal<Object> currentProject = new ThreadLocal<>();

    public static void setCurrentProject(Object project) {
        currentProject.set(project);
    }

    public static Object getCurrentProject() {
        return currentProject.get();
    }
}
