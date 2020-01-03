package com.mo16.petclinic.Controllers;

import com.mo16.petclinic.Service.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OwnersListController {

    private final OwnerService ownerService;

    public OwnersListController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping({"/owners", "/owners.html"})
    public String ownerList(Model model) {
        model.addAttribute("owners", ownerService.findAll());
        return "owners";
    }
}
