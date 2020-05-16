package com.portal.animals.presenters;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BasicAnimalInformationPresenter {

  private String id;
  private String type;
  private String name;
  private String breed;
  private String profilePicture;
}
