package com.qau.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qau.entites.Documents;
import com.qau.entites.Terms;
import com.qau.services.FilterService;

@Controller
@RequestMapping("/filters")
public class FilterController {

	@Autowired
	private FilterService filterService;
	@Autowired
	private com.qau.entites.Config config;
	
	@RequestMapping(value = "/generate-dataset", method = RequestMethod.GET)
	public String generateDataset(Model model){
//		filterService.getInvertedIndex(config.getDatasetPath(), config.getUniqueTermsPath());
		return "index";
	}
	
	@RequestMapping(value = "/filtered-documents", method = RequestMethod.GET)
	public String filteringStemming(){
		return "filtered-document";
	}
	
	@RequestMapping(value = "/get-filtered-documents", method = RequestMethod.GET)
	public @ResponseBody List<Documents> getFilteringStemming(){
		return filterService.getFilteredDocuments(config.getDatasetPath(), config.getUnFilteredDSPath(), config.getClassLabelPath());
	}
	
	@RequestMapping(value = "/tokenization", method = RequestMethod.GET)
	public String tokenization(){
		return "terms";
	}
	
	@RequestMapping(value = "/get-tokens", method = RequestMethod.GET)
	public @ResponseBody List<Terms> getTokens(Model model){
		return filterService.getTokens(config.getDatasetPath(), config.getUniqueTermsPath());
	}
	
	@RequestMapping(value = "/inverted-index", method = RequestMethod.GET)
	public String invertedIndex(){
		return "inverted-index";
	}
	
	@RequestMapping(value = "/get-inverted-index", method = RequestMethod.GET)
	public @ResponseBody List<Terms>  getInvertedIndex(){
		return filterService.getInvertedIndex(config.getUniqueTermsPath(), config.getDatasetPath(), config.getInvertedIndexPath());
	}
	
	@RequestMapping(value="**")
	public String pageNotFound(){
		return "404";
	}
}
