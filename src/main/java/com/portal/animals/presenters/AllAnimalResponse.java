package com.portal.animals.presenters;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class AllAnimalResponse {

  private List<BasicAnimalInformationPresenter> animals;
}
