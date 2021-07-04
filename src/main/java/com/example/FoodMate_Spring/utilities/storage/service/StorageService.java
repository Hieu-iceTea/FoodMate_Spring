package com.example.FoodMate_Spring.utilities.storage.service;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    void store(MultipartFile file, String path);

}