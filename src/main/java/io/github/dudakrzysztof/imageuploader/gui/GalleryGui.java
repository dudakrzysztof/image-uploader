package io.github.dudakrzysztof.imageuploader.gui;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import io.github.dudakrzysztof.imageuploader.model.Image;
import io.github.dudakrzysztof.imageuploader.repo.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route("gallery")
public class GalleryGui extends VerticalLayout {
    private ImageRepo imageRepo;

    @Autowired
    public GalleryGui(ImageRepo imageRepo) {
        this.imageRepo = imageRepo;
        List<Image> all = imageRepo.findAll();

        all.forEach(element -> {
            com.vaadin.flow.component.html.Image image =
                    new com.vaadin.flow.component.html.Image(element.getImageAddress(), "brak");
            add(image);
        });

    }
}
