package com.mistergold.mistergold.adapters.firebase.service;

import com.mistergold.mistergold.application.ports.out.upload_image.UploadImagePort;
import com.mistergold.mistergold.configuration.web.advice.exception.InternalError;
import com.mistergold.mistergold.configuration.web.enums.RunErrorEnum;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Service
public class UploadImageFirebaseService implements UploadImagePort {

    String url = "/home/vitor/college/interdiscipline_project/mistergold_frontend/images/upload/";

    public String uploadImage(MultipartFile file) {
        String fileName = UUID.randomUUID() + extensionImage(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(url + fileName);
            Files.write(path, bytes);
        } catch (IOException e) {
            throw new InternalError(RunErrorEnum.ERR0012);
        }
        return "/images/upload/" + fileName;
    }

    private String extensionImage(String nameImage) {
        String[] strings = nameImage.split("\\.");
        return "." + strings[strings.length - 1];
    }
}