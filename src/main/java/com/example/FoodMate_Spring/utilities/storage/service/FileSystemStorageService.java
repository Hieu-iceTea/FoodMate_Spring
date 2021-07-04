package com.example.FoodMate_Spring.utilities.storage.service;

import com.example.FoodMate_Spring.utilities.Common;
import com.example.FoodMate_Spring.utilities.storage.exception.StorageException;
import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Service
public class FileSystemStorageService implements StorageService {

    /**
     * <b>Hiếu_iceTea</b> <br> <br>
     *
     * //Xử lý file:<br>
     * if (!file.isEmpty()) { <br>
     * String fileName =  storageService.store(file, "src/main/resources/static"); <br>
     * product.setImage(fileName); <br>
     * }
     * <br> <br>
     *
     * Create At: 2021-07-04 22:30
     *
     * <br> <br>
     *
     * @param file Chú ý thêm trường này trong form: enctype="multipart/form-data"
     * @param path Không để ký tự / ở đầu hoặc cuối
     */
    @Override
    public String store(MultipartFile file, String path) {
        try {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
            }
            String uniqueFileName = getUniqueFileName(file);
            Files.copy(file.getInputStream(), Paths.get(path).resolve(uniqueFileName));

            return uniqueFileName;
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
        }
    }

    /**
     * <b>Hiếu_iceTea</b> <br> <br>
     *
     * Create At: 2021-07-04 22:30
     *
     * <br> <br>
     */
    public String getUniqueFileName(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf('.') + 1);
        String fileNameWithoutContentType = originalFilename.replace("." + extension, "");

        String dateTimeNow = (new SimpleDateFormat("yyyyMMdd-HHmmss")).format(Calendar.getInstance().getTime());

        String fileName = Common.toSlug(fileNameWithoutContentType) + "_" + RandomString.make(6) + "_" + dateTimeNow + "." + extension;

        return fileName;
    }

}