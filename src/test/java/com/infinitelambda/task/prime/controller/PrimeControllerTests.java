package com.infinitelambda.task.prime.controller;

import com.infinitelambda.task.prime.service.PrimeService;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigInteger;

@ExtendWith(SpringExtension.class)
@WebMvcTest
@AutoConfigureMockMvc
public class PrimeControllerTests {

    @Autowired
    PrimeController controller;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PrimeService service;

    @Test
    public void checkPrimeNumberTest() throws Exception{
        BigInteger number=new BigInteger("5");
        BigInteger nextPrime=new BigInteger("7");
        Mockito.when(service.isPrime(number)).thenReturn(true);
        Mockito.when(service.nextPrime(number)).thenReturn(nextPrime);

        String request = "{\"possiblePrime\": 5}";
        mockMvc.perform(MockMvcRequestBuilders.post("/prime")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.prime").value("true"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nextPrime").value("7"))
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON));

        Mockito.verify(service).isPrime(number);
        Mockito.verify(service).nextPrime(number);
    }

    @Test
    public void checkPrimeNumberWithNegativeNumberTest() throws Exception {
        String request = "{\"possiblePrime\": -5}";

        mockMvc.perform(MockMvcRequestBuilders.post("/prime")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.possiblePrime", Is.is("must be greater than or equal to 2")))
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void checkPrimeNumberWithLessThan2Test() throws Exception {
        String request = "{\"possiblePrime\": 1}";

        mockMvc.perform(MockMvcRequestBuilders.post("/prime")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.possiblePrime", Is.is("must be greater than or equal to 2")))
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void checkPrimeNumberWithNullRequestTest() throws Exception {
        String request = "{}";

        mockMvc.perform(MockMvcRequestBuilders.post("/prime")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.possiblePrime", Is.is("must not be null")))
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON));
    }
}
