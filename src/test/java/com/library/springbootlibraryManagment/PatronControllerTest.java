package com.library.springbootlibraryManagment;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.library.springbootlibraryManagment.patron.Patron;
import com.library.springbootlibraryManagment.patron.PatronController;
import com.library.springbootlibraryManagment.patron.PatronService;
import com.fasterxml.jackson.databind.ObjectWriter;

@RunWith(MockitoJUnitRunner.class)
public class PatronControllerTest {
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new ParameterNamesModule())
            .registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule()); // new module

    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private PatronService patronService;

    @InjectMocks
    private PatronController patronController;

    Patron RECORD_1 = new Patron(1L, "Omar", "omar@gmail.com", "Giza", "+201116391269", LocalDate.of(2011, Month.JANUARY, 5));
    Patron RECORD_2 = new Patron(2L, "Ali", "ali@gmail.com", "Alex", "+201116391269", LocalDate.of(1995, Month.JANUARY, 5));
    Patron RECORD_3 = new Patron(3L, "Ahmed", "ahmed@gmail.com", "Cairo", "+201116391269", LocalDate.of(1999, Month.JANUARY, 5));
    Patron RECORD_4 = new Patron(4L, "Ibrahim", "ibrahim@gmail.com", "Luxor", "+201116391269", LocalDate.of(2002, Month.JANUARY, 5));

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        patronController = new PatronController(patronService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(patronController).build();
    }

    @Test
    public void getPatrons_sucess() throws Exception {
        List<Patron> patrons = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3, RECORD_4));
        Mockito.when(patronService.getPatrons()).thenReturn(patrons);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/v1/patron")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[2].name", is("Ahmed")))
                .andExpect(jsonPath("$[3].name", is("Ibrahim")));

    }

    @Test
    public void getPatronById_sucess() throws Exception {
        Mockito.when(patronService.getPatronById(RECORD_1.getId())).thenReturn(RECORD_1);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/api/v1/patron/" + RECORD_1.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("Omar")));
    }

    @Test
    public void addPatron_sucess() throws Exception {
        Patron newRecord = Patron.builder()
                .id(5L)
                .name("Mahmoud")
                .email("mahmoud@gmail.com")
                .address("Alex")
                .phone("+201006932457")
                .dob(LocalDate.of(2000, Month.OCTOBER, 14))
                .age(24)
                .build();
        Mockito.when(patronService.addNewPatron(newRecord)).thenReturn(newRecord);
        String content = objectMapper.writeValueAsString(newRecord);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/v1/patron")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());
    }

    @Test
    public void updatePatron_sucess() throws Exception {
        Patron updatedRecord = Patron.builder()
            .id(1L)
            .name("Tarek")
            .email("tarek@gmail.com")
            .address("Alex")
            .phone("+201006932457")
            .dob(LocalDate.of(2000, Month.OCTOBER, 14))
            .age(24)
            .build();

        Mockito.when(patronService.updatePatron(1L, 
            "Tarek", 
            "tarek@gmail.com",
            "Alex",
            "+2201006932457",
            LocalDate.of(1818, Month.OCTOBER, 14)))
        .thenReturn(updatedRecord);
                
        String content = objectMapper.writeValueAsString(updatedRecord);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/api/v1/patron/" + updatedRecord.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(content);
        
        mockMvc.perform(mockRequest)
                        .andExpect(status().isOk());
    }

    @Test
    public void deletePatron_sucess() throws Exception {
        Mockito.when(patronService.deletePatron(RECORD_2.getId())).thenReturn(RECORD_2);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .delete("/api/v1/patron/" + RECORD_2.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("Ali")));
    }


    
}
