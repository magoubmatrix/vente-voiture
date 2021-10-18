package com.app.service.voiturePhoto;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.app.entity.VoiturePhoto;
import com.app.repository.VoiturePhotoRepository;
import com.app.services.ServicePhotoVoiture;

@Service

public class ServicePhotoVoitureImpl implements ServicePhotoVoiture {

	@Autowired
	private VoiturePhotoRepository repo;
	
	@Override
	@Transactional
	public VoiturePhoto addPhotoVoiture(MultipartFile file) {
	    VoiturePhoto photo = new VoiturePhoto();
	    photo.setNom(file.getOriginalFilename());
	    photo.setDataType(file.getContentType());
	    try {
			photo.setData(file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    repo.save(photo);
	   
	    return photo;
	}
	
}
