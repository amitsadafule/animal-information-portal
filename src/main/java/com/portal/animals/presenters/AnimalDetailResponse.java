package com.portal.animals.presenters;

import lombok.*;

import java.util.List;

@Getter
@Setter
public class AnimalDetailResponse extends BasicAnimalInformationPresenter {

  private String description;
  private List<AnimalFeatures> features;

  @Builder(builderMethodName = "detailBuilder")
  AnimalDetailResponse(String id, String type, String name, String breed, String profilePicture,
                       String description, List<AnimalFeatures> features) {
    super(id, type, name, breed, profilePicture);
    this.description = description;
    this.features = features;
  }
}
