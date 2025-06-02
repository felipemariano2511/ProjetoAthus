package br.com.unicuritiba.projetoathus.application.services;

import br.com.unicuritiba.projetoathus.infrastructure.storage.UserImageStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Service
public class ImagesUploadService {

    private final UserImageStorageService imageStorageService;

    public ImagesUploadService(UserImageStorageService userImageStorageService) {
        this.imageStorageService = userImageStorageService;
    }

    public void upload(List<MultipartFile> images) {
        for (MultipartFile image : images) {
            imageStorageService.store(image);
        }
    }
}