package com.app.services;

import org.springframework.web.multipart.MultipartFile;

import com.app.entity.VoiturePhoto;

public interface ServicePhotoVoiture {

	public VoiturePhoto addPhotoVoiture(MultipartFile file);
}
