package com.mo16.petclinic.Controllers;

import com.mo16.petclinic.Service.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VetsListController {

    private VetService vetService;

    @Autowired
    public VetsListController(VetService vetService) {
        this.vetService = vetService;
    }

    @GetMapping({"/vets", "/vets.html"})
    public String VetList(Model model) {
        model.addAttribute("vets", vetService.findAll());
        return "vets/vetList";
    }
}
