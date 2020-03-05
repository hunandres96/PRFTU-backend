package com.hunandres.demopostgres.controllers;

import com.hunandres.demopostgres.dto.ProfessorDTO;
import com.hunandres.demopostgres.service.ProfessorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(ProfessorController.class)
public class ProfessorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfessorService professorService;

    @Test
    public void getProfessorsTest() throws Exception {

        List<ProfessorDTO> professorDTOS = new ArrayList<>();
        professorDTOS.add(ProfessorDTO.builder()
            .id(1)
            .professor_name("Drake Perry")
            .professor_email("drakeperry@umsl.edu")
            .build());
        when(professorService.findAll(any())).thenReturn(professorDTOS);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/professors");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();

        verify(professorService, times(1)).findAll(any());
        assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());

    }

    @Test
    public void getProfessorById() throws Exception {

        ProfessorDTO professorDTO = ProfessorDTO.builder()
                .id(1)
                .professor_name("John Doe")
                .professor_email("johndoe@gmail.com")
                .build();
        when(professorService.findProfessorById(1)).thenReturn(professorDTO);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/professor/1");
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();

        verify(professorService, times(1)).findProfessorById(1);
        assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
    }

}