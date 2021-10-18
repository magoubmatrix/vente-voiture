package com.app.service.voiture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Operation {
	
	public int somme(int a , int b) {
		return a+b;
	}
}

class ServiceVoitureCriteriaTest2 {

	Operation operation = new Operation();
	
	
	@Test
	void testSearchVoiture() {
		
       // given 
		
		int x = 12;
		int y = 13;
		
		//when
		int result = operation.somme(x, y);
		
		
		//then
		
		assertThat(result).isEqualTo(35);
		
	}

}
