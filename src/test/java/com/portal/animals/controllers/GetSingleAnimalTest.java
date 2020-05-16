package com.portal.animals.controllers;

import com.portal.animals.presenters.AnimalDetailResponse;
import com.portal.animals.presenters.AnimalFeatures;
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

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class GetSingleAnimalTest {

  private static final String ALL_ANIMALS_BASE_URL = "/v1/animals";

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private AnimalService animalService;

  private AnimalDetailResponse animalResponse;

  @BeforeEach
  public void setUp() {
    animalResponse = AnimalDetailResponse.detailBuilder()
      .id("1")
      .type("dog")
      .name("dog1")
      .breed("testbreeddog")
      .profilePicture("dogpic")
      .description("This is test description")
      .features(Arrays.asList(
        AnimalFeatures.builder()
          .name("color")
          .value("brown")
          .build(),
        AnimalFeatures.builder()
          .name("height")
          .value("24")
          .build()
      ))
      .build();
  }

  @Test
  @DisplayName("api /v1/animals/{animal_id} should return animal information if animal is present in system")
  public void shouldReturnAnimalIfPresentV1() throws Exception {

    when(animalService.getAnimal(eq("1"))).thenReturn(animalResponse);

    this.mockMvc
      .perform(get(ALL_ANIMALS_BASE_URL + "/1"))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.id", equalTo("1")))
      .andExpect(jsonPath("$.name", equalTo("dog1")))
      .andExpect(jsonPath("$.type", equalTo("dog")))
      .andExpect(jsonPath("$.breed", equalTo("testbreeddog")))
      .andExpect(jsonPath("$.profilePicture", equalTo("dogpic")))
      .andExpect(jsonPath("$.description", equalTo("This is test description")))
      .andExpect(jsonPath("$.features").isArray())
      .andExpect(jsonPath("$.features", hasSize(2)))
      .andExpect(jsonPath("$.features[0].name", equalTo("color")))
      .andExpect(jsonPath("$.features[0].value", equalTo("brown")))
      .andExpect(jsonPath("$.features[1].name", equalTo("height")))
      .andExpect(jsonPath("$.features[1].value", equalTo("24")));

  }

  @Test
  @DisplayName("api /v1/animals/{animal_id} should return 404 http status code if animal is not present in system")
  public void shouldReturnNotfoundIfAnimalWithGivenIdIsNotPresentInSystemV1() throws Exception {

    when(animalService.getAnimal(eq("2"))).thenReturn(null);

    this.mockMvc
      .perform(get(ALL_ANIMALS_BASE_URL + "/2"))
      .andExpect(status().isNotFound())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.code").exists())
      .andExpect(jsonPath("$.description").exists());
  }
}
