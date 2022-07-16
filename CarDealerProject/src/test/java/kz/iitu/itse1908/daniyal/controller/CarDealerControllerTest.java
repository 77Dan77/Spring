package kz.iitu.itse1908.daniyal.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import kz.iitu.itse1908.daniyal.DaniyalApplication;
import kz.iitu.itse1908.daniyal.config.Config;
import kz.iitu.itse1908.daniyal.database.CarDealer;
import kz.iitu.itse1908.daniyal.database.Customer;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { DaniyalApplication.class, Config.class })
@WebAppConfiguration
class CarDealerControllerTest {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void testExistingOfCarDealerController() {
        ServletContext servletContext = webApplicationContext.getServletContext();
        assertNotNull(servletContext);
        assertTrue(servletContext instanceof MockServletContext);
        assertNotNull(webApplicationContext.getBean("CarDealerController"));
    }

    @Test
    public void testHomepage() throws Exception {
        this.mockMvc.perform(get("/api")).andDo(print());
    }

    @Test
    void getCarDealer() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get("/api/carDealers/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Honda"))
                .andReturn();

        Assert.assertEquals("application/json",
                mvcResult.getResponse().getContentType());
    }

    @Test
    void addCarDealer() throws Exception {
        CarDealer anObject = new CarDealer();
        anObject.setId(20L);
        anObject.setName("Porshe");
        // more
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(anObject);

        this.mockMvc.perform(post("/api/carDealers/addCarDealer").contentType(APPLICATION_JSON_UTF8).
                content(requestJson)).andExpect(status().isCreated());
    }

    @Test
    void updateCarDealer() throws Exception {
        CarDealer anObject = new CarDealer();
        anObject.setId(20L);
        anObject.setName("Porshe");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(anObject);
        this.mockMvc.perform(MockMvcRequestBuilders.put("/api/carDealers/updateCarDealer/21").
                contentType(APPLICATION_JSON_UTF8).content(requestJson)).andExpect(status().isOk());
    }

    @Test
    void deleteCarDealer() throws Exception {
        CarDealer anObject = new CarDealer();
        anObject.setId(20L);
        anObject.setName("Porshe");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(anObject);
        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/carDealers/deleteCarDealer/{id}", "5")
                        .contentType(APPLICATION_JSON_UTF8).content(requestJson))
                .andExpect(status().isOk());
    }
}