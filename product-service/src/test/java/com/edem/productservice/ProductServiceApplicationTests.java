//package com.edem.productservice;
//
//import com.edem.productservice.dto.ProductRequest;
//import com.edem.productservice.repository.ProductRepository;
//import com.mongodb.assertions.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.DynamicPropertyRegistry;
//import org.springframework.test.context.DynamicPropertySource;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.testcontainers.containers.MongoDBContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
//
//import java.math.BigDecimal;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@Testcontainers
//@AutoConfigureMockMvc
//class ProductServiceApplicationTests {
//    @Container
//    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");
//
//    private final MockMvc mockMvc;
//    private  final ProductRepository repository;
//    private final ObjectMapper objectMapper;
//
//    public ProductServiceApplicationTests(MockMvc mockMvc, ProductRepository repository, ObjectMapper objectMapper) {
//        this.mockMvc = mockMvc;
//        this.repository = repository;
//        this.objectMapper = objectMapper;
//    }
//
//    @DynamicPropertySource
//    static void setMongoDBContainer (DynamicPropertyRegistry dynamicPropertyRegistry){
//        dynamicPropertyRegistry.add("spring.data.mongodb.uri",mongoDBContainer::getReplicaSetUrl);
//    }
//    @Test
//    //Create a product request object and make it a string then pass it into the mvc request builder as a string and expect a created respon se
//    void shouldCreateProduct() throws Exception {
//       ProductRequest productRequest =  getProductRequest();
//       String productRequestString = objectMapper.writeValueAsString(productRequest);
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(productRequestString))
//                .andExpect(status().isCreated());
//
//        Assertions.assertTrue(repository.findAll().size()==1);//assert that the repository has only one item
//    }
//
//    private ProductRequest  getProductRequest() {
//        return ProductRequest.builder()
//                .name("Iphone 13")
//                .description("Iphone")
//                .price(BigDecimal.valueOf(2000))
//                .build();
//    }
//
//}
