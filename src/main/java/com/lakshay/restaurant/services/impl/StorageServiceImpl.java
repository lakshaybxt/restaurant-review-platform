package com.lakshay.restaurant.services.impl;

import com.lakshay.restaurant.exceptions.StorageException;
import com.lakshay.restaurant.services.StorageService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Service
@Slf4j
public class StorageServiceImpl implements StorageService {

    @Value("${app.storage.location:uploads}")
    private String storageLocation;

    private Path rootLocation;

    @PostConstruct
    public void init() {
        rootLocation = Paths.get(storageLocation);
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage location", e);
        }
    }

    @Override
    public String store(MultipartFile file, String filename) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file");
            }
            String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
            String finalFileName = filename + "." + extension;

            Path destinationFile = rootLocation
                    .resolve(Paths.get(finalFileName))
                    .normalize()
                    .toAbsolutePath();

            // This ensures that the file doesn’t get stored outside the allowed folder,
            // preventing directory traversal attacks
            if (!destinationFile.getParent().equals(rootLocation.toAbsolutePath())) {
                throw new StorageException("Cannot store file outside specified directory");
            }

            // Reads the input stream of the uploaded file.
            try (InputStream inputStream = file.getInputStream()) {
                // Copies it to the target location.
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }

            return finalFileName;

        } catch (IOException e) {
            throw new StorageException("Failed to store file", e);
        }
    }

    @Override
    public Optional<Resource> loadAsResource(String filename) {
        try {
            Path file = rootLocation.resolve(filename);

            Resource resource = new UrlResource(file.toUri());

            if(resource.exists() || resource.isReadable()) {
                return Optional.of(resource);
            } else {
                return Optional.empty();
            }
        } catch (MalformedURLException e) {
            log.warn("Could not read file: %s".formatted(filename), e);
            return Optional.empty();
        }
    }
}
