
package com.joham.onlineshopping.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUpload {

        
 @Value("${uploadDir}")
 private String uploadDir;
 
    

    public String  uploadFile(MultipartFile file){
        final Path fileStorageLocation = Paths.get(uploadDir)
                                        .toAbsolutePath().normalize();  
             try {
          
                if(!Files.isDirectory(fileStorageLocation)) {
                Files.createDirectories(fileStorageLocation);}

            Files.copy(file.getInputStream(), fileStorageLocation.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);

            } catch (IOException e) {

                e.printStackTrace();
            }


	return file.getOriginalFilename();
 }
    
}