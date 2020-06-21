package com.portal.animals.services;

import com.portal.animals.presenters.AllAnimalRequest;
import com.portal.animals.presenters.AllAnimalResponse;
import com.portal.animals.presenters.AnimalDetailResponse;

public interface AnimalService {

  AllAnimalResponse getAnimals(AllAnimalRequest request);
  AnimalDetailResponse getAnimal(String animalId);

}
