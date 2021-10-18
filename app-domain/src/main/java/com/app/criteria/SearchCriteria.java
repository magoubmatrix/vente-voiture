package com.app.criteria;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor

public class SearchCriteria {


		private String key;
		private String operation;
		private Object value;
		
		public SearchCriteria(String key, String operation, Object value) {
			super();
			this.key = key;
			this.operation = operation;
			this.value = value;
		}
		
		

}
