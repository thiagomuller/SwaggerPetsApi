package com.thiagomuller.service.impl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.thiagomuller.service.FileUploadService;

@Service
public class FileUploadServiceImpl implements FileUploadService{
	
	@Value("${app.upload.dir:${user.home}}")
	public String uploadDir;

	@Override
	public void uploadFile(MultipartFile file) {
		try {
			byte[] bytes = file.getBytes();
			Path copyLocation = Paths.get(uploadDir + File.separator + 
					StringUtils.cleanPath(file.getOriginalFilename()));
			Files.write(copyLocation, bytes);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}