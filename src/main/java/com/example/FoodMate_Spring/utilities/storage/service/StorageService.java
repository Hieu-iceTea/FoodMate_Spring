package com.example.FoodMate_Spring.utilities.storage.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    String store(MultipartFile file, String path);

    void delete(String fileName, String path);

}