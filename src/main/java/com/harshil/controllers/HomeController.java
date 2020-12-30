package com.harshil.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.harshil.models.LocationStats;
import com.harshil.services.CoronaVirusDataService;
import com.harshil.services.CoronaVirusDeathService;
import com.harshil.services.CoronaVirusRecoverService;

@Controller
public class HomeController {

	@Autowired
	CoronaVirusDataService coronavirusdataservice;
	@Autowired
	CoronaVirusDeathService coronavirusdeathservice;
	@Autowired
	CoronaVirusRecoverService coronavirusrecoverservice;
	
	@GetMapping("/")
	public String home(Model model) {
		List<LocationStats> allStats=coronavirusdataservice.getAllstats();
		List<LocationStats> allStats1=coronavirusdeathservice.getAllstatsDeath();
		List<LocationStats> allStats2=coronavirusrecoverservice.getAllstatsRecover();
		int totalReportedCases =allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
		int PrevReportedCases =allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
		int totalDeathCases=allStats1.stream().mapToInt(stat -> stat.getLatestDeathCases()).sum();
		int recovercases=allStats2.stream().mapToInt(stat -> stat.getLatestRecoverCases()).sum();
		int PrevDeathCases=allStats1.stream().mapToInt(stat -> stat.getDiffdeathFromPrevDay()).sum();
		int PrevRecoverCases=allStats2.stream().mapToInt(stat -> stat.getDiffrecoverPrevDay()).sum();
		int ActiveCases=totalReportedCases-recovercases;
		model.addAttribute("locationStats", allStats);
		model.addAttribute("locationStats1", allStats1);
		model.addAttribute("locationStats2", allStats2);
		model.addAttribute("totalReportedCases", totalReportedCases);
		model.addAttribute("PrevReportedCases", PrevReportedCases);
		model.addAttribute("totalDeathCases", totalDeathCases);
		model.addAttribute("recovercases", recovercases);
		model.addAttribute("PrevDeathCases", PrevDeathCases);
		model.addAttribute("PrevRecoverCases", PrevRecoverCases);
		model.addAttribute("ActiveCases", ActiveCases);
		
		return "home";
	}
	
	@RequestMapping("/covid19")
	public String covid19() {
		return "covid19";
	}
    
	@RequestMapping("/about")
	public String about() {
		return "about";
	}
}
