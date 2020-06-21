package com.portal.animals.controllers;

import com.portal.animals.presenters.AllAnimalRequest;
import com.portal.animals.presenters.AllAnimalResponse;
import com.portal.animals.presenters.BasicAnimalInformationPresenter;
import com.portal.animals.services.AnimalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;

import static com.portal.animals.constants.ErrorMessage.Constants.BREED_SIZE_NOT_VALID_CODE;
import static com.portal.animals.constants.ErrorMessage.Constants.TYPE_SIZE_NOT_VALID_CODE;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class GetAllAnimalsTest {

  private static final String ALL_ANIMALS_BASE_URL = "/v1/animals";

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private AnimalService animalService;

  private AllAnimalResponse animalResponse;

  @BeforeEach
  public void setUp() {
    animalResponse = AllAnimalResponse.builder()
      .animals(Arrays.asList(
        BasicAnimalInformationPresenter.builder()
          .id("1")
          .type("dog")
          .name("dog1")
          .breed("testbreeddog")
          .profilePicture("dogpic")
          .build(),
        BasicAnimalInformationPresenter.builder()
          .id("2")
          .type("cat")
          .name("cat1")
          .breed("testbreedcat")
          .profilePicture("catpic")
          .build()
      ))
      .build();
  }

  @Test
  @DisplayName("api /v1/animals should return all animals if no parameter is given")
  public void shouldReturnAllAnimalsV1() throws Exception {

    when(animalService.getAnimals(any(AllAnimalRequest.class))).thenReturn(animalResponse);

    this.mockMvc
      .perform(get(ALL_ANIMALS_BASE_URL))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.animals").isArray())
      .andExpect(jsonPath("$.animals", hasSize(2)))
      .andExpect(jsonPath("$.animals[0].id", equalTo("1")))
      .andExpect(jsonPath("$.animals[0].name", equalTo("dog1")))
      .andExpect(jsonPath("$.animals[0].type", equalTo("dog")))
      .andExpect(jsonPath("$.animals[0].breed", equalTo("testbreeddog")))
      .andExpect(jsonPath("$.animals[0].profilePicture", equalTo("dogpic")))
      .andExpect(jsonPath("$.animals[1].id", equalTo("2")))
      .andExpect(jsonPath("$.animals[1].name", equalTo("cat1")))
      .andExpect(jsonPath("$.animals[1].type", equalTo("cat")))
      .andExpect(jsonPath("$.animals[1].breed", equalTo("testbreedcat")))
      .andExpect(jsonPath("$.animals[1].profilePicture", equalTo("catpic")));

  }

  @Test
  @DisplayName("api /v1/animals should return ok with empty data if there is no matching animal found")
  public void shouldReturnOkIfNoMatchingAnimalFound() throws Exception {

    when(animalService.getAnimals(any(AllAnimalRequest.class))).thenReturn(AllAnimalResponse.builder()
      .animals(Collections.emptyList())
      .build());

    this.mockMvc
      .perform(get(ALL_ANIMALS_BASE_URL)
        .param("type", "dogtest"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.animals").isArray())
      .andExpect(jsonPath("$.animals", hasSize(0)));

  }

  @Test
  @DisplayName("api /v1/animals should return ok if type has greater than 3 characters")
  public void shouldReturnOkIfTypeHasGreaterThan3Chars() throws Exception {

    when(animalService.getAnimals(any(AllAnimalRequest.class))).thenReturn(AllAnimalResponse.builder()
      .animals(Collections.emptyList())
      .build());

    this.mockMvc
      .perform(get(ALL_ANIMALS_BASE_URL)
        .param("type", "dogtest"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.animals").isArray());

  }

  @Test
  @DisplayName("api /v1/animals should return ok with matching animal data if type has equal to 3 characters")
  public void shouldReturnOkIfTypeHasExact3Chars() throws Exception {

    when(animalService.getAnimals(any(AllAnimalRequest.class))).thenReturn(AllAnimalResponse.builder()
      .animals(Arrays.asList(animalResponse.getAnimals().get(0)))
      .build());

    this.mockMvc
      .perform(get(ALL_ANIMALS_BASE_URL)
        .param("type", "dog"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.animals").isArray())
      .andExpect(jsonPath("$.animals", hasSize(1)))
      .andExpect(jsonPath("$.animals[0].id", equalTo("1")))
      .andExpect(jsonPath("$.animals[0].name", equalTo("dog1")))
      .andExpect(jsonPath("$.animals[0].type", equalTo("dog")))
      .andExpect(jsonPath("$.animals[0].breed", equalTo("testbreeddog")))
      .andExpect(jsonPath("$.animals[0].profilePicture", equalTo("dogpic")));

  }

  @Test
  @DisplayName("api /v1/animals should return bad request if type has less than 3 characters")
  public void shouldReturnBadRequestWithErrorMessageIfTypeHasLessThan3Chars() throws Exception {

    when(animalService.getAnimals(any(AllAnimalRequest.class))).thenReturn(animalResponse);

    this.mockMvc
      .perform(get(ALL_ANIMALS_BASE_URL)
        .param("type", "ab"))
      .andExpect(status().isBadRequest())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("code").exists())
      .andExpect(jsonPath("description").exists())
      .andExpect(jsonPath("code", equalTo(TYPE_SIZE_NOT_VALID_CODE)));

  }

  @Test
  @DisplayName("api /v1/animals should return ok if breed has greater than 3 characters")
  public void shouldReturnOkIfBreedHasGreaterThan3Chars() throws Exception {

    when(animalService.getAnimals(any(AllAnimalRequest.class))).thenReturn(AllAnimalResponse.builder()
      .animals(Collections.emptyList())
      .build());

    this.mockMvc
      .perform(get(ALL_ANIMALS_BASE_URL)
        .param("breed", "dogtest"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.animals").isArray());

  }

  @Test
  @DisplayName("api /v1/animals should return ok with animal data if breed has equal to 3 characters")
  public void shouldReturnOkIfBreedHasExact3Chars() throws Exception {

    when(animalService.getAnimals(any(AllAnimalRequest.class))).thenReturn(AllAnimalResponse.builder()
      .animals(Arrays.asList(animalResponse.getAnimals().get(0)))
      .build());

    this.mockMvc
      .perform(get(ALL_ANIMALS_BASE_URL)
        .param("breed", "dog"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.animals").isArray())
      .andExpect(jsonPath("$.animals", hasSize(1)))
      .andExpect(jsonPath("$.animals[0].id", equalTo("1")))
      .andExpect(jsonPath("$.animals[0].name", equalTo("dog1")))
      .andExpect(jsonPath("$.animals[0].type", equalTo("dog")))
      .andExpect(jsonPath("$.animals[0].breed", equalTo("testbreeddog")))
      .andExpect(jsonPath("$.animals[0].profilePicture", equalTo("dogpic")));

  }

  @Test
  @DisplayName("api /v1/animals should return bad request if breed has less than 3 characters")
  public void shouldReturnBadRequestWithErrorMessageIfBreedHasLessThan3Chars() throws Exception {

    when(animalService.getAnimals(any(AllAnimalRequest.class))).thenReturn(animalResponse);

    this.mockMvc
      .perform(get(ALL_ANIMALS_BASE_URL)
        .param("breed", "ab"))
      .andExpect(status().isBadRequest())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("code").exists())
      .andExpect(jsonPath("description").exists())
      .andExpect(jsonPath("code", equalTo(BREED_SIZE_NOT_VALID_CODE)));

  }

}
