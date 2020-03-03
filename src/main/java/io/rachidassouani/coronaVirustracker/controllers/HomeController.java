package io.rachidassouani.coronaVirustracker.controllers;

import io.rachidassouani.coronaVirustracker.models.LocationStats;
import io.rachidassouani.coronaVirustracker.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private CoronaVirusDataService dataService;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("locationStats", dataService.getLocationStats());
        int totalReportedCases = dataService.getLocationStats()
                                                .stream()
                                                .mapToInt(stat -> stat.getLatestTotalCases())
                                                .sum();

        int totalNewCases = dataService.getLocationStats()
                                                .stream()
                                                .mapToInt(stat -> stat.getDiffFromPreviousDay())
                                                .sum();

        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("locationStats", dataService.getLocationStats());
        model.addAttribute("totalNewCases", totalNewCases);
        return "home";
    }
}
