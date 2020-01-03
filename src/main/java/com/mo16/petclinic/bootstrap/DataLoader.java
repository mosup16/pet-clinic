package com.mo16.petclinic.bootstrap;

import com.mo16.petclinic.Service.*;
import com.mo16.petclinic.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@ComponentScan(basePackages = {"com.mo16.petclinic"})
public class DataLoader implements CommandLineRunner {

    private VetService vetService;
    private OwnerService ownerService;
    private PetService petService;
    private PetTypeService petTypeService;
    private SpecialityService specialityService;

    @Autowired
    public DataLoader(VetService vetService, OwnerService ownerService, PetService petService, PetTypeService petTypeService, SpecialityService specialityService) {
        this.vetService = vetService;
        this.ownerService = ownerService;
        this.petService = petService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
    }

    @Override
    public void run(String... args) throws Exception {
        PetType dog = new PetType();
        dog.setName("dog");

        PetType cat = new PetType();
        cat.setName("cat");


        Owner ownerBuild = Owner
                .builder()
                .firstName("mo")
                .lastName("Em")
                .address("sad")
                .city("cairo")
                .telephone("212")
                .id(12L)
                .build();

        Pet petBuild = Pet
                .builder()
                .name("dd")
                .birthDate(LocalDate.now())
                .petType(cat)
                .id(54L)
                .petOwner(ownerBuild)
                .build();
        petBuild.getVisits().add(new Visit());
        ownerBuild.getPets().add(petBuild);

        Owner michel = new Owner();
        michel.setFirstName("Michel");
        michel.setLastName("Dawn");

        Pet michelPet = new Pet();
        michelPet.setPetType(cat);
        michelPet.setName("vino");
        michelPet.setBirthDate(LocalDate.now());
        michelPet.setPetOwner(michel);

        michel.getPets().add(michelPet);

        Owner jack = new Owner();
        jack.setFirstName("Jack");
        jack.setLastName("Martin");

        Pet jackPet = new Pet();
        jackPet.setPetType(dog);
        jackPet.setName("max");
        jackPet.setBirthDate(LocalDate.now());
        jackPet.setPetOwner(jack);

        jack.getPets().add(jackPet);

        System.out.println("Loaded Owners ......... ");

        Vet william = new Vet();
        william.setFirstName("William");
        william.setLastName("Smith");

        Speciality radiology = new Speciality();
        radiology.setDescription("radiology");

        william.getSpecialities().add(radiology);

        Vet john = new Vet();
        john.setFirstName("John");
        john.setLastName("Martin");

        Speciality surgery = new Speciality();
        surgery.setDescription("surgery");

        Speciality dentistry = new Speciality();
        dentistry.setDescription("dentistry");

        john.getSpecialities().add(dentistry);
        john.getSpecialities().add(surgery);

        System.out.println("Loaded Vets ......... ");

        dog = petTypeService.save(dog);
        cat = petTypeService.save(cat);
        michel = ownerService.save(michel);
        ownerService.save(jack);
        petService.save(michelPet);
        petService.save(jackPet);

        radiology = specialityService.save(radiology);
        vetService.save(william);
        surgery = specialityService.save(surgery);
        dentistry = specialityService.save(dentistry);
        vetService.save(john);
        ownerService.save(ownerBuild);

    }
}
