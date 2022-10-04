package com.qau.entites;

import java.io.Serializable;

public class Documents implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer documentId;
	private String document;
	private String documentLabel;
	
	public Documents() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Documents(Integer documentId, String document, String documentLabel) {
		super();
		this.documentId = documentId;
		this.document = document;
		this.documentLabel = documentLabel;
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

	public String getDocumentLabel() {
		return documentLabel;
	}

	public void setDocumentLabel(String documentLabel) {
		this.documentLabel = documentLabel;
	}
}
