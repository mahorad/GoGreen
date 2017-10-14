package com.mahorad.ctrlr;

import com.mahorad.model.Veggie;
import com.mahorad.service.VeggieService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(VeggieController.class)
public class VeggieControllerTest {

    private static final String baseUrlTemplate = "/api/v1/veggies";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private VeggieService service;

    private HttpMessageConverter httpMessageConverter;

    @Autowired
    void setupConverter(HttpMessageConverter<?>[] converters) {
        httpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);
        assertNotNull(httpMessageConverter);
    }

    @Test
    public void getAll() throws Exception {
        Veggie veggie = new Veggie("10", "carrot", new BigDecimal("2.22"));
        List<Veggie> veggies = singletonList(veggie);

        Mockito
                .when(service.findAll())
                .thenReturn(veggies);

        RequestBuilder request = MockMvcRequestBuilders
                .get(baseUrlTemplate)
                .accept(APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(veggie.getName())))
                .andExpect(jsonPath("$[0].price", is(veggie.getFee().toString())));
    }

    @Test
    public void getById() throws Exception {
        Veggie veggie = new Veggie("10", "carrot", new BigDecimal("2.22"));

        Mockito
                .when(service.findById(anyString()))
                .thenReturn(veggie);

        RequestBuilder request = MockMvcRequestBuilders
                .get(baseUrlTemplate.concat("/").concat("10"))
                .accept(APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(veggie.getName())))
                .andExpect(jsonPath("$.price", is(veggie.getFee().toString())));
    }

    @Test
    public void create() throws Exception {
        Veggie veggie = new Veggie("10", "carrot", new BigDecimal("2.22"));
        String jsonVeggie = toJson(veggie);

        Mockito
                .when(service.create(any(Veggie.class)))
                .thenReturn(veggie);

        RequestBuilder request = MockMvcRequestBuilders
                .post(baseUrlTemplate)
                .accept(APPLICATION_JSON)
                .content(jsonVeggie)
                .contentType(APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(veggie.getName())))
                .andExpect(jsonPath("$.price", is(veggie.getFee().toString())));

    }

    @Test
    public void update() throws Exception {
        Veggie veggie = new Veggie("10", "carrot", new BigDecimal("2.22"));
        String jsonVeggie = toJson(veggie);

        Mockito
                .when(service.update(anyString(), any(Veggie.class)))
                .thenReturn(veggie);

        RequestBuilder request = MockMvcRequestBuilders
                .put(baseUrlTemplate.concat("/").concat("10"))
                .accept(APPLICATION_JSON)
                .content(jsonVeggie)
                .contentType(APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(veggie.getName())))
                .andExpect(jsonPath("$.price", is(veggie.getFee().toString())));
    }

    private String toJson(Object object) throws IOException {
        MockHttpOutputMessage mockedMessage = new MockHttpOutputMessage();
        httpMessageConverter.write(object, APPLICATION_JSON, mockedMessage);
        return mockedMessage.getBodyAsString();
    }

    @Test
    public void delete() throws Exception {
        Mockito
                .when(service.delete(anyString()))
                .thenReturn(true);

        RequestBuilder request = MockMvcRequestBuilders
                .delete(baseUrlTemplate.concat("/").concat("10"))
                .accept(APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isNoContent());
    }
}
