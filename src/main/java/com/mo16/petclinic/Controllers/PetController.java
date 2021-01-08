package com.mo16.petclinic.Controllers;

import com.mo16.petclinic.Service.OwnerService;
import com.mo16.petclinic.Service.PetService;
import com.mo16.petclinic.Service.PetTypeService;
import com.mo16.petclinic.model.Owner;
import com.mo16.petclinic.model.Pet;
import com.mo16.petclinic.model.PetType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@RequestMapping("owners/{ownerId}")
public class PetController {

    private OwnerService ownerService;
    private PetTypeService petTypeService;
    private PetService petService;

    public PetController(OwnerService ownerService, PetTypeService petTypeService, PetService petService) {
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable Long ownerId){
        return ownerService.findById(ownerId);
    }

    @ModelAttribute("types")
    public Set<PetType> populatePetTypes(){
        return petTypeService.findAll();
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder binder){
        binder.setDisallowedFields("id");
    }

    @GetMapping("/pets/new")
    public String createPet(Owner owner ,Model model){
        Pet pet = new Pet();
        owner.addPet(pet);
        model.addAttribute("pet" , pet);
        return "pets/createOrUpdatePetForm";
    }

    @GetMapping("/pets/{petId}/edit")
    public String editPet(@PathVariable Long petId ,Model model){
        Pet pet = petService.findById(petId);
        model.addAttribute("pet" , pet);
        return "pets/createOrUpdatePetForm";
    }

    @PostMapping("/pets/new")
    public String processCreatePet(@ModelAttribute("pet") Pet pet ,@PathVariable Long ownerId){
        Owner owner = ownerService.findById(ownerId);
        owner.addPet(pet);
        Pet savedPet = petService.save(pet);
        return "redirect:/owners/"+ ownerId;
    }

    @PostMapping("/pets/{petId}/edit")
    public String processEditPet(@PathVariable Long ownerId, @PathVariable Long petId ,@ModelAttribute("pet") Pet pet , BindingResult result){
        Owner owner = ownerService.findById(ownerId);
        pet.setPetOwner(owner);
        petService.save(pet);
        return "redirect:/owners/"+ ownerId;
    }
}
