package com.qau.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qau.entites.DocumentInfo;
import com.qau.services.TechniqueService;

@Controller
@RequestMapping("/techniques")
public class TechniqueController {

	@Autowired
	private TechniqueService techniqueService;
	@Autowired
	private com.qau.entites.Config config;
	
	@RequestMapping(value = "/calculate-nnn", method = RequestMethod.GET)
	public String calculateNNN(){
		return "calculate-nnn";
	}
	
	@RequestMapping(value = "/get-nnn", method = RequestMethod.GET)
	public @ResponseBody List<DocumentInfo> getNNN(){
		return techniqueService.getCalculationNNN(config.getDatasetPath(), config.getNnnPath());
	}
	
	@RequestMapping(value = "/calculate-ntc", method = RequestMethod.GET)
	public String calculateNTC(){
		return "calculate-ntc";
	}
	
	@RequestMapping(value = "/get-ntc", method = RequestMethod.GET)
	public @ResponseBody List<DocumentInfo> getNTC(){
		return techniqueService.getCalculationNTC(config.getDatasetPath(), config.getUniqueTermsPath(), config.getInvertedIndexPath(), config.getNtcPath());	
	}
	
	@RequestMapping(value = "/calculate-atc", method = RequestMethod.GET)
	public String calculateATC(){
		return "calculate-atc";
	}
	
	@RequestMapping(value = "/get-atc", method = RequestMethod.GET)
	public @ResponseBody List<DocumentInfo> getATC(){
		return techniqueService.getCalculationATC(config.getDatasetPath(), config.getUniqueTermsPath(), config.getInvertedIndexPath(), config.getAtcPath());
	}
	
	@RequestMapping(value="**", method = RequestMethod.GET)
	public String pageNotFound(){
		return "404";
	}
}
