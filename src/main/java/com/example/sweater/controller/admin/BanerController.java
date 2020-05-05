package com.example.sweater.controller.admin;

import com.example.sweater.domain.Baner;
import com.example.sweater.repos.BanerRepo;
import com.example.sweater.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Controller
public class BanerController {
    @Autowired
    private BanerRepo banerRepo;
    @Autowired
    private FileService fileService;

    @GetMapping("/admin/getBaner")
    public String getBaner(Map<String, Object> model){
        model.put("baners" , banerRepo.findAll());
        return "baner";
    }

    @PostMapping("/admin/changeBaner/{banerId}")
    public String changeBaner(@RequestParam(value = "file", required = false) MultipartFile file,
                              Map<String, Object> model,
                              @PathVariable String banerId) throws IOException {
        Baner banerToDelete;
        if (banerRepo.findById(Long.valueOf(banerId)).isPresent()) {
            banerToDelete = banerRepo.findById(Long.valueOf(banerId)).get();
        }else return "/admin/getBaner";
        banerRepo.delete(banerToDelete);
        String resultFilename = fileService.fileService(file);
        //checking weather file is present or not
        if (resultFilename.equals("")) return "redirect:/admin/getBaner";
        Baner baner = new Baner(resultFilename);
        banerRepo.save(baner);
        model.put("baners" , banerRepo.findAll());
        return "baner";
    }
}
