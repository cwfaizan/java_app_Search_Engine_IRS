package com.qau.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qau.services.SimilarityService;

@Controller
@RequestMapping("/nnn-atc")
public class NnnAtcController {

	@Autowired
	private SimilarityService similarityService;
	@Autowired
	private com.qau.entites.Config config;
	
	@RequestMapping("/{id}")
	public String rankedDocuments(Model model, @PathVariable int id){
		model.addAttribute("rankedDocuments", similarityService.getRankedDocuments(id, config.getNnnPath(), config.getAtcPath(), config.getDatasetPath(), config.getNnnPath()+" atc "+id));
		return "ranked-documents";
	}
	
	@RequestMapping("/precision-recall/{id}")
	public String getPrecisionRecall(Model model, @PathVariable int id){
		model.addAttribute("precisionRecall", similarityService.getPrecisionRecall(id, 100, config.getNnnPath()+" atc "+id));
		return "precision-recall";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public @ResponseBody List<String> searchRankedDocuments(@RequestParam String query){
		String[] qry = query.split(",");
		return similarityService.getRankedDocuments(qry[0].trim(), 100, qry[1].trim(), config.getClassLabelPath(), config.getNnnPath(), config.getDatasetPath(), config.getUniqueTermsPath(), config.getInvertedIndexPath(), config.getNnnPath()+" atc search");
	}
	
	@RequestMapping(value="**", method = RequestMethod.GET)
	public String pageNotFound(){
		return "404";
	}
}
