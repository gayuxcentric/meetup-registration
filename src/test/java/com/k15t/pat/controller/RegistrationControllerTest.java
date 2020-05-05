package com.k15t.pat.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.k15t.pat.model.User;
import com.k15t.pat.service.RegistrationService;
import com.k15t.pat.util.RegistrationUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RegistrationControllerTest
{
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private RegistrationService registrationService;

  @MockBean
  private RegistrationUtil registrationUtil;

  @Test
  public void testSaveRegistration() throws Exception
  {
    User user = new User();
    user.setName("Gayathri");
    user.setEmail("gayathrisekar@test.com");
    user.setAddress("12,xyz");
    user.setCity("kornwestheim");
    user.setPassword("12345678");
    user.setConfirmPassword("12345678");
    user.setPhone("0123456789");
    user.setCountry("DE");

    Mockito.when(
      registrationUtil.matchPasswords(Mockito.any())).thenReturn(true);
    Mockito.when(
      registrationService.saveUser(Mockito.any())).thenReturn(user);

    RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/save/registration")
      .accept(MediaType.ALL);

    mockMvc.perform(requestBuilder)
      .andExpect(status().isOk())
      .andReturn();
  }

  @Test
  public void testGetAllRegisteredUsers() throws Exception {
    User user = new User();
    user.setName("Gayathri");
    user.setEmail("gayathrisekar11@test.com");
    user.setAddress("12,xyz");
    user.setCity("kornwestheim");
    user.setPassword("12345678");
    user.setConfirmPassword("12345678");
    user.setPhone("0123456789");
    user.setCountry("DE");


    User user1 = new User();
    user1.setName("Gayathri");
    user1.setEmail("gayathrisekar12@test.com");
    user1.setAddress("12,xyz");
    user1.setCity("kornwestheim");
    user1.setPassword("12345678");
    user1.setConfirmPassword("12345678");
    user1.setPhone("0123456789");
    user1.setCountry("DE");

    List<User> registrationList = new ArrayList<User>();
    registrationList.add(user);
    registrationList.add(user1);
    Mockito.when(
      registrationService.list()).thenReturn(registrationList);

    RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
      "/registeredUsers").accept(
      MediaType.ALL);

    MvcResult result = mockMvc.perform(requestBuilder)
      .andExpect(status().isOk())
      .andExpect(view().name("success"))
      .andReturn();

    Map<String,Object> resultMap =  result.getModelAndView().getModel();
    List<User> registrationNewList = (List<User>) resultMap.get("registeredUsers");

    assertEquals("gayathrisekar11@test.com", registrationNewList.get(0).getEmail());
    assertEquals("Gayathri", registrationNewList.get(0).getName());
  }

  @Test
  public void testUserExistsRegistration() throws Exception
  {
    User user = new User();
    user.setName("Gayathri");
    user.setEmail("gayathrisekar123@test.com");
    user.setAddress("12,xyz");
    user.setCity("kornwestheim");
    user.setPassword("12345678");
    user.setConfirmPassword("12345678");
    user.setPhone("0123456789");
    user.setCountry("DE");

    Mockito.when(
      registrationUtil.matchPasswords(Mockito.any())).thenReturn(true);
    Mockito.when(
      registrationService.saveUser(Mockito.any())).thenReturn(user);

    RequestBuilder requestBuilderUser1 = MockMvcRequestBuilders.post("/save/registration")
      .accept(MediaType.ALL);

    mockMvc.perform(requestBuilderUser1)
      .andExpect(status().isOk())
      .andReturn();

    Mockito.when(
      registrationUtil.matchPasswords(Mockito.any())).thenReturn(true);
    Mockito.when(
      registrationService.saveUser(Mockito.any())).thenReturn(user);

    RequestBuilder requestBuilderUser2 = MockMvcRequestBuilders.post("/save/registration")
      .accept(MediaType.ALL);

    mockMvc.perform(requestBuilderUser2)
      .andExpect(status().isOk())
      .andReturn();

  }

}
