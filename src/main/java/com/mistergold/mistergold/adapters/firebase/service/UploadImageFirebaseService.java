package com.mistergold.mistergold.adapters.firebase.service;

import com.mistergold.mistergold.application.ports.out.upload_image.UploadImagePort;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.BucketInfo;
import com.mistergold.mistergold.adapters.firebase.FirebaseInit;
import com.mistergold.mistergold.configuration.web.advice.exception.InternalError;
import com.mistergold.mistergold.configuration.web.enums.RunErrorEnum;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.mistergold.mistergold.adapters.firebase.FirebaseInit.storage;

@Service
public class UploadImageFirebaseService implements UploadImagePort {

    @Override
    public String uploadImage(MultipartFile file) {
        String fileName = generateFileName(file.getOriginalFilename());

        try {
            Map<String, String> map = new HashMap<>();
            map.put("firebaseStorageDownloadTokens", fileName);
            BlobInfo blobInfo = BlobInfo.newBuilder(BlobId.of(FirebaseInit.BUCKET_NAME, fileName))
                    .setMetadata(map)
                    .setContentType(file.getContentType())
                    .build();
            storage().createFrom(blobInfo, file.getInputStream());

            return getUrlImage(fileName);
        } catch (Exception e) {
            throw new InternalError(RunErrorEnum.ERR0012);
        }
    }

    private String generateFileName(String originalFileName) {
        return UUID.randomUUID() + "." + StringUtils.getFilenameExtension(originalFileName);
    }

    private String getUrlImage(String fileName) {
        return "https://firebasestorage.googleapis.com/v0/b/" + FirebaseInit.BUCKET_NAME + "/o/" + fileName + "?alt=media&token=" + fileName;
    }
}