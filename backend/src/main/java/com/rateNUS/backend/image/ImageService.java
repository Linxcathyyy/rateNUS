package com.rateNUS.backend.image;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    @Autowired

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public void save(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getOriginalFilename());
        image.setData(file.getBytes());

        imageRepository.save(image);
    }

    public Optional<Image> getImage(Long id) {
        return imageRepository.findById(id);
    }

}
