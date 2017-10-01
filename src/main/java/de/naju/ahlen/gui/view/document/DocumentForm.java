package de.naju.ahlen.gui.view.document;

import com.vaadin.server.SerializablePredicate;
import com.vaadin.server.UserError;
import com.vaadin.ui.Upload;
import de.naju.ahlen.gui.view.base.BaseForm;
import de.naju.ahlen.persistence.model.Document;
import de.naju.ahlen.persistence.model.enums.DocumentFormat;
import de.naju.ahlen.util.ProjectUtil;
import de.naju.ahlen.util.orm.CurrentProject;
import org.vaadin.viritin.fields.MTextField;
import sun.nio.cs.ext.MacThai;

import javax.print.Doc;
import java.io.*;

public class DocumentForm extends BaseForm<Document> {

    private MTextField path = new MTextField("Pfad").withEnabled(false);
    private MTextField name = new MTextField("Name");
    private Upload upload;

    public DocumentForm(DocumentController controller) {
        super(controller);

        SerializablePredicate<String> nameNotEmptyPredicate =
                (SerializablePredicate<String>) v -> !name.isEmpty();

        SerializablePredicate<String> nameExistsPredicate =
                (SerializablePredicate<String>) v -> !controller.existsName(name.getValue());

        binder.forField(name)
                .withValidator(nameNotEmptyPredicate, "Name darf nicht leer sein")
                .withValidator(nameExistsPredicate, "Name existiert bereits")
                .bind(Document::getName, Document::setName);

        upload = new Upload("Dokument Upload", new Upload.Receiver() {
            @Override
            public OutputStream receiveUpload(String filename, String mimeType) {
                System.out.println(mimeType);
                if (name.isEmpty()) {
                    upload.setComponentError(new UserError("Name darf  nicht leer sein."));
                    return new ByteArrayOutputStream();
                }
                if (!mimeType.equals("image/png")
                        && !mimeType.equals("iamge/jpg")
                        && !mimeType.equals("application/pdf")) {
                    upload.setComponentError(new UserError("Unbekannter Dateityp. Muss pdf, png oder jpg sein"));
                    return new ByteArrayOutputStream();
                }
                String extension = "";
                switch (mimeType) {
                    case "image/png":
                        getEntity().setFormat(DocumentFormat.PNG);
                        extension = "png";
                        break;
                    case "image/jpg":
                        getEntity().setFormat(DocumentFormat.JPG);
                        extension = "jpg";
                        break;
                    case "application/pdf":
                        getEntity().setFormat(DocumentFormat.PDF);
                        extension = "pdf";
                        break;
                }
                FileOutputStream fos;
                File outputFile = new File(String.format("%s/%s.%s",
                        ProjectUtil.getDocumentPathString(CurrentProject.getCurrentProject()),
                        name.getValue(),
                        extension));
                try {
                    fos = new FileOutputStream(outputFile);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    return new ByteArrayOutputStream();
                }
                //controller.setIconChanged(true);
                return fos;
            }
        });

        bind();
    }
}
