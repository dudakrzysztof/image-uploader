package io.github.dudakrzysztof.imageuploader.gui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import io.github.dudakrzysztof.imageuploader.ImageUploader;


@Route("upload")
public class UploadGui extends VerticalLayout {

    private ImageUploader imageUploader;
    private TextField textField;
    private Button button;

    public UploadGui(final ImageUploader imageUploader){
        this.imageUploader = imageUploader;

        Label label = new Label();

        this.button = new Button("Upload");
        this.textField = new TextField();

        button.addClickListener(clickEvent -> {
            String uploadedImage = imageUploader.uploadFileAndSaveToDb(textField.getValue());
            Image image = new Image(uploadedImage, "nie ma obrazka :(");
            label.setText("Udało sie wrzucic obrazek");
            add(label);
            add(image);
        });

        add(textField);
        add(button);
    }

}
