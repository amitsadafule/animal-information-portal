package com.portal.animals.presenters;

import lombok.*;

import javax.validation.constraints.Size;

import static com.portal.animals.constants.ErrorMessage.Constants.BREED_SIZE_NOT_VALID_CODE;
import static com.portal.animals.constants.ErrorMessage.Constants.TYPE_SIZE_NOT_VALID_CODE;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AllAnimalRequest {

  @Size(min = 3, message = TYPE_SIZE_NOT_VALID_CODE)
  private String type;

  @Size(min = 3, message = BREED_SIZE_NOT_VALID_CODE)
  private String breed;
}
