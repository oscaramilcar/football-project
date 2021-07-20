package com.project.football.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileServiceImpl implements IUploadFileService{

    private final static String UPLOAD_DIRECTORY = "uploads";

    @Override
    public Resource load(String nameImage) throws MalformedURLException {
        Path filePath = getPath(nameImage);

        Resource resource = new UrlResource(filePath.toUri());

        if(!resource.exists() && !resource.isReadable()) {
            throw new RuntimeException("error could not load the image: "+ nameImage);
        }
        return resource;
    }

    @Override
    public String copy(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID().toString() + "_" +  file.getOriginalFilename().replace(" ", "");
        Path rutaArchivo = getPath(fileName);
        Files.copy(file.getInputStream(), rutaArchivo);
        return fileName;
    }

    @Override
    public boolean delete(String nameImage) {
        if(nameImage !=null && nameImage.length() >0) {
            Path routePreviusImage = Paths.get("uploads").resolve(nameImage).toAbsolutePath();
            File fileImagePrevius = routePreviusImage.toFile();
            if(fileImagePrevius.exists() && fileImagePrevius.canRead()) {
                fileImagePrevius.delete();
                return true;
            }
        }

        return false;
    }

    @Override
    public Path getPath(String nameImage) {
        return Paths.get(UPLOAD_DIRECTORY).resolve(nameImage).toAbsolutePath();
    }
}
