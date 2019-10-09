package com.keya.demoelastic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
@Service
@Slf4j
public class FileStorageServiceImpl implements FileStorageService {

    @Value("${invoice.storage.dir} :/Users/kodjovi1/uploads")
    private String directory;


    @Override
    public void save(File file) {
        try {
            Files.copy(Paths.get(file.toURI()),Paths.get(file.getName()), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
           log.error(e.getMessage(),e);
        }


    }
}
