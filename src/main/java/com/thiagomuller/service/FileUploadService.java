package com.thiagomuller.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
	public void uploadFile(MultipartFile file);
}
