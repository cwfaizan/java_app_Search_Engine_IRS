package com.qau.entites;

import java.io.Serializable;

public class Terms implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer termId;
	private String term;
	
	public Terms() {
		super();
	}

	public Terms(Integer termId, String term) {
		super();
		this.termId = termId;
		this.term = term;
	}

	public Integer getTermId() {
		return termId;
	}

	public void setTermId(Integer termId) {
		this.termId = termId;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}
}
