package com.springboot.cloud.contracts.controllers;


import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@AutoConfigureStubRunner(
    stubsMode = StubRunnerProperties.StubsMode.LOCAL,
    ids = "com.springboot.cloud.contract:producer:+:stubs:8090")
@DirtiesContext
public class BasicMathControllerIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void given_WhenPassEvenNumberInQueryParam_ThenReturnEven()
      throws Exception {

    mockMvc.perform(MockMvcRequestBuilders.get("/calculate?number=2")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string("Even"));
  }

  @Test
  public void given_WhenCallingSequence_ThenOk()
      throws Exception {

    mockMvc.perform(MockMvcRequestBuilders.get("/sequence/ok")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string("ok"));
  }

  // this would be invalid behaviour
  // only added to demonstrate that the order of the calls matter
  @Ignore
  @Test
  public void given_WhenCallingSequence_ThenNOk()
      throws Exception {

    mockMvc.perform(MockMvcRequestBuilders.get("/sequence/nok")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(content().string("nok"));
  }
}