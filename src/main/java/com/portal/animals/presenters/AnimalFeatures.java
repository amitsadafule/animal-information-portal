package com.portal.animals.presenters;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AnimalFeatures {

  private String name;
  private String value;
}
