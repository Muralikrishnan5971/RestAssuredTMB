package com.pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FavFoods {

	private String breakFast;
	private String lunch;
	private List<String> dinner;
	
	public FavFoods() {
		
	}

}
