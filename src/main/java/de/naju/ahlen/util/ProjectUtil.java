package de.naju.ahlen.util;

import com.vaadin.server.FileResource;
import com.vaadin.server.ThemeResource;
import de.naju.ahlen.persistence.model.project.Project;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ProjectUtil {

    public static String getUrl(Project project) {
        return String.format("jdbc:h2:file:./Projekte/%s/%s", project.getName(), project.getName());
    }

    public static String getUsername(Project project) {
        return "";
    }

    public static String getPassword(Project project) {
        return "";
    }

    public static String getPath(Project project) {
        return String.format("./Projekte/%s", project.getName());
    }

    public static FileResource getIcon(Project project) {
        String iconPath = getPath(project) + File.separator + "icon.png";
        if (!Files.exists(Paths.get(iconPath))) {
            return null;
        } else {
            return new FileResource(new File(iconPath));
        }
    }

    public static String getDocumentPathString(Project project) {
        return String.format("%s/Daten/Dokumente", getPath(project));
    }

    public static Path getDocumentPath(Project project) {
        return Paths.get(getDocumentPathString(project));
    }

    public static void createProjectStructure(Project project) throws IOException {
        Path root = Paths.get(getPath(project));
        Path data = Paths.get(String.format("%s/Daten", getPath(project)));
        Path documents = Paths.get(String.format("%s/Daten/Dokumente", getPath(project)));

        if (Files.exists(root)) {
            throw new FileAlreadyExistsException(String.format("Folder %s already exists", root.toString()));
        }

        Files.createDirectory(root);
        Files.createDirectory(data);
        Files.createDirectory(documents);
    }

    public static void deleteProjectStructure(Project project) throws IOException {
        Path root = Paths.get(getPath(project));

        FileUtils.deleteDirectory(root.toFile());

    }

    public static void moveProjectStructure(Project sourceProject, Project targetProject) throws IOException {
        Path sourceRoot = Paths.get(getPath(sourceProject));
        Path targetRoot = Paths.get(getPath(targetProject));
        Path sourceDB = Paths.get(String.format("%s/%s.mv.db", getPath(targetProject), sourceProject.getName()));
        Path targetDB = Paths.get(String.format("%s/%s.mv.db", getPath(targetProject), targetProject.getName()));

        Files.move(sourceRoot, targetRoot);
        Files.move(sourceDB, targetDB);
    }

    public static void moveIcon(Project entity) throws IOException {
        Path sourceIconPath = Paths.get("./icon.png");
        Path targetIconPath = Paths.get(String.format("%s/icon.png", getPath(entity)));

        if (Files.exists(targetIconPath)) {
            Files.delete(targetIconPath);
        }
        Files.copy(sourceIconPath, targetIconPath);
        Files.delete(sourceIconPath);
    }
}
