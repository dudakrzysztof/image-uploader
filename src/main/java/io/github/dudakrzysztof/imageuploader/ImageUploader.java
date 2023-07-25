package io.github.dudakrzysztof.imageuploader;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import io.github.dudakrzysztof.imageuploader.model.Image;
import io.github.dudakrzysztof.imageuploader.repo.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class ImageUploader {

    private Cloudinary cloudinary;
    private ImageRepo imageRepo;

    @Autowired
    public ImageUploader(ImageRepo imageRepo) {
        this.imageRepo = imageRepo;

        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "ddsv2pv6n",
                "api_key", "376538565699983",
                "api_secret", "ybhlOIH4lqmRC2gbofH1UQ9P2S0"));
    }

    public String uploadFileAndSaveToDb(String path){
        File file = new File(path);
        Map uploadResult = null;
        try {
            uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            imageRepo.save(new Image(uploadResult.get("url").toString()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return uploadResult.get("url").toString();
    }
}
