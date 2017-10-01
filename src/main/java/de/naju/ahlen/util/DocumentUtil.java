package de.naju.ahlen.util;

import com.vaadin.server.FileResource;
import de.naju.ahlen.persistence.model.Document;
import de.naju.ahlen.persistence.model.project.Project;
import de.naju.ahlen.util.orm.CurrentProject;
import org.apache.commons.io.FileUtils;

import javax.print.Doc;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DocumentUtil {

    public static String filePathString(Document document) {
        return String.format("%s/%s.%s",
                ProjectUtil.getDocumentPathString(CurrentProject.getCurrentProject()),
                document.getName(),
                document.getFormat().extension());
    }

    public static Path filePath(Document document) {
        return Paths.get(filePathString(document));
    }

    public static void deleteDocument(Document document) throws IOException {
        Path filePath = filePath(document);
        if (Files.exists(filePath)) {
            Files.delete(filePath);
        }
    }

    public static void moveDocument(Document sourceDocument, Document targetDocument) throws IOException {
        Path sourcePath = filePath(sourceDocument);
        Path targetPath = filePath(targetDocument);

        if (Files.exists(targetPath)) {
            throw new FileAlreadyExistsException(String.format("File %s already exists", targetPath.toString()));
        } else {
            Files.move(sourcePath, targetPath);
        }
    }

    public static FileResource getData(Document document) {
        String dataPath = filePathString(document);
        if (!Files.exists(filePath(document))) {
            return null;
        } else {
            return new FileResource(new File(dataPath));
        }
    }
}
