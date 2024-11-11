package com.mistergold.mistergold.application.ports.out.upload_image;

import org.springframework.web.multipart.MultipartFile;

public interface UploadImagePort {
    String uploadImage(MultipartFile file);
}
