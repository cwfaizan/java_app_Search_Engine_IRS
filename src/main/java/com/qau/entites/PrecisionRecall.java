package com.qau.entites;

import java.io.Serializable;

public class PrecisionRecall implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String key;
	private String value;
	
	public PrecisionRecall() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PrecisionRecall(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
