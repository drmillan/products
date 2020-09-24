package com.chocodev.products.api;

import com.chocodev.products.api.model.CreateProductRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

public class FunctionalTest extends AbstractTest {

    private ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Test
    @Sql("/delete_tables.sql")
    public void productsCanBeCreated() throws Exception {
        String uri = "/products";
        CreateProductRequest createProductRequest = new CreateProductRequest();
        createProductRequest.setName("example_object");
        createProductRequest.setPrice(new BigDecimal(10));
        mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(OBJECT_MAPPER.writeValueAsString(createProductRequest)));
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        String result = new String(mvcResult.getResponse().getContentAsString());
        System.out.println(result);
    }
}
