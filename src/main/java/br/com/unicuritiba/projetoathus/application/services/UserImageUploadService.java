package br.com.unicuritiba.projetoathus.application.services;

import br.com.unicuritiba.projetoathus.infrastructure.storage.UserImageStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserImageUploadService {

    private final UserImageStorageService userImageStorageService;

    public UserImageUploadService(UserImageStorageService userImageStorageService) {
        this.userImageStorageService = userImageStorageService;
    }

    public void upload(MultipartFile image) {
        userImageStorageService.store(image);
    }
}