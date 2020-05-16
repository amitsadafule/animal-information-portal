package com.portal.animals.presenters;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AllAnimalRequest {

  private String type;

  private String breed;
}
