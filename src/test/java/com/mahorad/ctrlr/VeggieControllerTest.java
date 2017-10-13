package com.mahorad.ctrlr;

import com.mahorad.model.Veggie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(VeggieController.class)
public class VeggieControllerTest {

    private static final String baseUrlTemplate = "/api/v1/veggies";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private VeggieController controller;

    @Test
    public void getAll() throws Exception {
        Veggie veggie = new Veggie("10", "carrot", new BigDecimal("2.22"));
        List<Veggie> veggies = singletonList(veggie);
        final ResponseEntity<List<Veggie>> response = new ResponseEntity<>(veggies, OK);

        given(controller.getAll()).willReturn(response);

        mvc.perform(get(baseUrlTemplate)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(veggie.getName())))
                .andExpect(jsonPath("$[0].price", is(veggie.getFee().toString())));
    }

    @Test
    public void getById() throws Exception {
        final String veggieId = "10";
        Veggie veggie = new Veggie(veggieId, "carrot", new BigDecimal("2.22"));
        ResponseEntity response = new ResponseEntity<>(veggie, OK);

        given(controller.getById(veggieId)).willReturn(response);

        mvc.perform(get(baseUrlTemplate.concat("/").concat(veggieId))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(veggie.getName())))
                .andExpect(jsonPath("$.price", is(veggie.getFee().toString())));
    }

}
