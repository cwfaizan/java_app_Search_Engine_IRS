package com.qau.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qau.entites.DocumentInfo;

@Controller
public class IndexController {

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String indexController(){
		
		return "index";
	}
	
	/*@RequestMapping(value = "/json", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody String jsonController(){
		JSONArray arr = new JSONArray();
		JSONObject j = new JSONObject();
		j.put("name","Tokyo");
		arr.put(j);
		return arr.toString();
	}*/
}
