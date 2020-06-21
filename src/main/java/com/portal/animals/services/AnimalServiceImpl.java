package com.portal.animals.services;

import com.portal.animals.presenters.AllAnimalRequest;
import com.portal.animals.presenters.AllAnimalResponse;
import com.portal.animals.presenters.AnimalDetailResponse;
import org.springframework.stereotype.Service;

@Service
public class AnimalServiceImpl implements AnimalService {
    @Override
    public AllAnimalResponse getAnimals(AllAnimalRequest request) {
        return null;
    }

    @Override
    public AnimalDetailResponse getAnimal(String animalId) {
        return null;
    }
}
