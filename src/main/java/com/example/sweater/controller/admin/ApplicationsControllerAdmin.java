package com.example.sweater.controller.admin;

import com.example.sweater.domain.Application;
import com.example.sweater.repos.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class ApplicationsControllerAdmin {
    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/admin/getAllApplications")
    public String getAllApplicationsAdmin(Map<String, Object> model) {
        //sorting applications by application date
        model.put("applications", applicationService.findAll().stream().sorted(Comparator.comparing(Application::getDate)).collect(Collectors.toList()));
        return "applications-admin";
    }
    @GetMapping("/admin/deleteApplication/{id}")
    public String deleteApplicationByIdAdmin(@PathVariable String id,
                                             Map<String, Object> model) {
        if (applicationService.findById(Long.valueOf(id)).isPresent()) {
            applicationService.delete(applicationService.findById(Long.valueOf(id)).get());
        }else {
            return "redirect://admin/getAllApplications";
        }
        //sorting applications by application date
        model.put("applications", applicationService.findAll().stream().sorted(Comparator.comparing(Application::getDate)).collect(Collectors.toList()));
        return "applications-admin";
    }
}
