package io.github.dudakrzysztof.imageuploader.repo;

import io.github.dudakrzysztof.imageuploader.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepo extends JpaRepository<Image, Long> {

}
