package com.qau.entites;

import java.io.Serializable;

public class DocumentInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer documentId;
	private String document;
	private Double weight;
	
	public DocumentInfo() {
		super();
	}
	
	public DocumentInfo(Integer documentId, String document, Double weight) {
		super();
		this.documentId = documentId;
		this.document = document;
		this.weight = weight;
	}

	public Integer getDocumentId() {
		return documentId;
	}
	
	public void setDocumentId(Integer documentId) {
		this.documentId = documentId;
	}
	
	public String getDocument() {
		return document;
	}
	
	public void setDocument(String document) {
		this.document = document;
	}
	
	public Double getWeight() {
		return weight;
	}
	
	public void setWeight(Double weight) {
		this.weight = weight;
	}
}
