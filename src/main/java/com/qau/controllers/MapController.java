package com.qau.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qau.services.MapService;

@Controller
@RequestMapping("/map")
public class MapController {

	@Autowired
	private MapService mapService;
	@Autowired
	private com.qau.entites.Config config;
	
	@RequestMapping("/nnn-atc")
	public String nnnAtcMap(Model model){
		model.addAttribute("precisionRecall", mapService.getMap(100, config.getClassLabelPath(), config.getNnnPath()+" atc "));
		return "precision-recall";
	}
	
	@RequestMapping("/ntc-atc")
	public String ntcAtcMap(Model model){
		model.addAttribute("precisionRecall", mapService.getMap(100, config.getClassLabelPath(), config.getNtcPath()+" atc "));
		return "precision-recall";
	}
	
	@RequestMapping(value="**", method = RequestMethod.GET)
	public String pageNotFound(){
		return "404";
	}
}
