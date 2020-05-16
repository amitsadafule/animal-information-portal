package com.portal.animals.controllers;

import com.portal.animals.presenters.AllAnimalRequest;
import com.portal.animals.presenters.AllAnimalResponse;
import com.portal.animals.presenters.AnimalDetailResponse;
import com.portal.animals.services.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/animals")
public class AnimalController {

  @Autowired
  private AnimalService animalService;

  @GetMapping
  public AllAnimalResponse getAllAnimals(AllAnimalRequest animalRequest) {
    return animalService.getAnimals(animalRequest);
  }

  @GetMapping("/{animal_id}")
  public AnimalDetailResponse getAllAnimals(@PathVariable("animal_id") String animalId) {
    return animalService.getAnimal(animalId);
  }
}
