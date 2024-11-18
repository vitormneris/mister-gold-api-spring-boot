package com.mistergold.mistergold.adapters.upload.service;

import com.mistergold.mistergold.application.ports.out.upload_image.UploadImagePort;
import com.mistergold.mistergold.configuration.web.advice.exception.InternalErrorException;
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
public class UploadImageService implements UploadImagePort {

    String url1 = "/home/vitor/college/interdiscipline_project/mistergold_frontend";
    String url2 = "/home/vitor/college/interdiscipline_project/MisterGoldFrontEndAdministrator";

    public String uploadImage(MultipartFile file) {
        String fileName = UUID.randomUUID() + extensionImage(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            byte[] bytes = file.getBytes();

            Path path1 = Paths.get(url1 + "/images/upload/"  + fileName);
            Files.write(path1, bytes);

            Path path2 = Paths.get(url2 + "/images/upload/" + fileName);
            Files.write(path2, bytes);
        } catch (IOException e) {
            throw new InternalErrorException(RunErrorEnum.ERR0012);
        }
        return "/images/upload/" + fileName;
    }

    private String extensionImage(String nameImage) {
        String[] strings = nameImage.split("\\.");
        return "." + strings[strings.length - 1];
    }
}