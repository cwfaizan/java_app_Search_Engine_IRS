package com.qau.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qau.repositories.FilterRepository;

@Service
public class InitComponentService {

	@Autowired
	private FilterRepository filterRepository;
	@Autowired
	private com.qau.entites.Config config;
	
	@PostConstruct
	public void init() {
		if (filterRepository.stopWordsList.isEmpty()) {
			filterRepository.stopWordsListUpdate(config.getStopWordsPath());
		}
	}
}
