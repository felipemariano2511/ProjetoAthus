package br.com.unicuritiba.projetoathus.infrastructure.storage;

import org.springframework.web.multipart.MultipartFile;

public interface ImageStorageService {
    void store(MultipartFile file);
}
