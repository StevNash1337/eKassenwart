package de.naju.ahlen.gui.view.project;

import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.SerializablePredicate;
import com.vaadin.server.UserError;
import com.vaadin.ui.Upload;
import de.naju.ahlen.gui.view.base.BaseForm;
import de.naju.ahlen.persistence.model.project.Project;
import org.vaadin.viritin.fields.MTextField;

import java.io.*;

public class ProjectForm extends BaseForm<Project> {

    private MTextField name = new MTextField("ID");
    private MTextField fullName = new MTextField("Name");
    private Upload upload;

    public ProjectForm(ProjectController controller) {
        super(controller);

        SerializablePredicate<String> nameNotEmptyPredicate =
                (SerializablePredicate<String>) v -> !name.isEmpty();

        SerializablePredicate<String> nameExistsPredicate =
                (SerializablePredicate<String>) s -> !controller.existsName(name.getValue());

        SerializablePredicate<String> fullNameNotEmptyPredicate =
                (SerializablePredicate<String>) v -> !fullName.isEmpty();

        binder.forField(name)
                .withValidator(nameNotEmptyPredicate, "ID darf nicht leer sein")
                .withValidator(nameExistsPredicate, "ID ist bereits vergeben")
                .bind(Project::getName, Project::setName);

        binder.forField(fullName)
                .withValidator(fullNameNotEmptyPredicate, "Name darf nicht leer sein")
                .bind(Project::getFullName, Project::setFullName);

        bind();


        upload = new Upload("Icon Datei Upload", (Upload.Receiver) (filename, mimeType) -> {
            if (name.isEmpty()) {
                upload.setComponentError(new UserError("Die ID darf  nicht leer sein."));
                return new ByteArrayOutputStream();
            }
            if (!mimeType.equals("image/png")) {
                upload.setComponentError(new UserError("Datei muss ein Bild sein"));
                return new ByteArrayOutputStream();
            }
            FileOutputStream fos;
            File outputFile = new File("./icon.png");
            try {
                fos = new FileOutputStream(outputFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return new ByteArrayOutputStream();
            }
            controller.setIconChanged(true);
            return fos;
        });
    }
}
