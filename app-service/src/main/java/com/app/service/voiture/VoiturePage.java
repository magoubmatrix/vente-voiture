package com.app.service.voiture;

import org.springframework.data.domain.Sort;

import lombok.Data;

@Data
public class VoiturePage {

	private int pageNumber = 0;
	private int pageSize = 4;
	private Sort.Direction directionSort;
	private String sortMape;
	
	VoiturePage(int pageNumber , int pageSize){
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.directionSort = Sort.Direction.ASC;
		this.sortMape = "prix";
	}
}
