package com.sample;

import com.sample.controller.CountryController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles({"demo"})
public class CountryControllerDemoTests {

    @Autowired
    private CountryController countryController;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_retrieve_countries_from_external_source_successfully() throws Exception {
        MvcResult result = mockMvc.perform(get("/countries"))
                .andExpect(status().isOk()).andReturn();
        String content = result.getResponse().getContentAsString();
        Path path = Paths.get(ClassLoader.getSystemResource("wiremock_response.txt").toURI());
        String expectedContent = Files.lines(path).findFirst().get();
        assertThat(content).isEqualTo(expectedContent);
    }
}
