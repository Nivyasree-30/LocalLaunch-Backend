package com.locallaunch.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class ImageStorageService {

    @Value("${file.upload-dir:uploads}")
    private String uploadDirectory;

    public String saveImage(MultipartFile file) throws IOException {

        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("Image file is empty");
        }

        String originalFilename = file.getOriginalFilename();

        if (originalFilename == null ||
                !originalFilename.matches("(?i).*\\.(jpg|jpeg|png|webp)$")) {

            throw new IllegalArgumentException(
                    "Only JPG, JPEG, PNG, and WEBP images are allowed"
            );
        }

        Path uploadPath = Paths.get(uploadDirectory)
                .toAbsolutePath()
                .normalize();

        Files.createDirectories(uploadPath);

        String extension = originalFilename.substring(
                originalFilename.lastIndexOf(".")
        );

        String uniqueFilename = UUID.randomUUID() + extension;

        Path targetPath = uploadPath.resolve(uniqueFilename);

        Files.copy(
                file.getInputStream(),
                targetPath,
                StandardCopyOption.REPLACE_EXISTING
        );

        return uniqueFilename;
    }
}
