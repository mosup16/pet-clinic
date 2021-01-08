package com.mo16.petclinic.Controllers;

import com.mo16.petclinic.Service.OwnerService;
import com.mo16.petclinic.model.Owner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
public class OwnerController {

    private final String Create_Or_Update_Form_View = "owners/createOrUpdateOwnerForm";
    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping({"/owners/ownersList", "/owners/ownersList.html"})
    public String ownerList(Model model) {
        model.addAttribute("owners", ownerService.findAll());
        return "owners/ownersList";
    }

    @GetMapping("/owners/{ownerId}")
    public String showOwner(@PathVariable Long ownerId, Model model) {
        model.addAttribute("owner", ownerService.findById(ownerId));
        return "owners/ownerDetails";
    }

    @GetMapping("/owners/find")
    public String findOwner(Model model) {
        model.addAttribute("owner", new Owner());
        return "owners/findOwners";
    }

    @GetMapping("/owners")
    public String processFindOwnerForm(Owner owner, BindingResult result, Model model) {
        if (owner.getLastName() == null) owner.setLastName("");
        Set<Owner> owners = ownerService.findByLastNameLike("%" + owner.getLastName() + "%");
        if (owners.isEmpty()) {
            result.rejectValue("lastName", "notfound", "not found");
            return "owners/findOwners";
        } else if (owners.size() == 1) {
            Owner nextOwner = owners.iterator().next();
            model.addAttribute("owner", nextOwner);
            return "redirect:/owners/" + nextOwner.getId();
        } else {
            model.addAttribute("owners", owners);
            return "owners/ownersList";
        }
    }

    @GetMapping("owners/new")
    public String createOwner(Model model){
        model.addAttribute("owner" , new Owner());
        return Create_Or_Update_Form_View;
    }

    @GetMapping("owners/{ownerId}/edit")
    public String editOwner(Model model, @PathVariable Long ownerId){
        model.addAttribute("owner" ,ownerService.findById(ownerId));
        return Create_Or_Update_Form_View;
    }

    @PostMapping("owners/new")
    public String processCreateOwner(@ModelAttribute("owner") Owner owner){
        Owner savedOwner = ownerService.save(owner);
        return "redirect:/owners/" + savedOwner.getId();
    }

    @PostMapping("owners/{ownerId}/edit")
    public String processEditOwner(@PathVariable Long ownerId ,@ModelAttribute("owner") Owner owner){
        owner.setId(ownerId);
        Owner savedOwner = ownerService.save(owner);
        return "redirect:/owners/" + savedOwner.getId();
    }
}
