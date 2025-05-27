package br.com.unicuritiba.projetoathus.infrastructure.storage;

import org.springframework.web.multipart.MultipartFile;

public interface UserImageStorageService {
    void store(MultipartFile file);
}
