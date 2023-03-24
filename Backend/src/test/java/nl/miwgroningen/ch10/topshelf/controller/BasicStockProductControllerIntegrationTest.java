package nl.miwgroningen.ch10.topshelf.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.miwgroningen.ch10.topshelf.TopshelfApplication;
import nl.miwgroningen.ch10.topshelf.dto.BasicStockProductDTO;
import nl.miwgroningen.ch10.topshelf.mapper.BasicStockProductDTOMapper;
import nl.miwgroningen.ch10.topshelf.model.*;
import nl.miwgroningen.ch10.topshelf.repository.BasicStockProductRepository;
import nl.miwgroningen.ch10.topshelf.repository.PantryRepository;
import nl.miwgroningen.ch10.topshelf.repository.UserRepository;
import nl.miwgroningen.ch10.topshelf.security.config.JwtService;
import nl.miwgroningen.ch10.topshelf.service.BasicStockProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = TopshelfApplication.class)
@AutoConfigureMockMvc
@Transactional
class BasicStockProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    BasicStockProductService basicStockProductService;

    @Autowired
    BasicStockProductDTOMapper basicStockProductDTOMapper;

    @Autowired
    BasicStockProductRepository basicStockProductRepository;

    @Autowired
    PantryRepository pantryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtService jwtService;

    String jwtToken;

    Pantry pantry;

    BasicStockProductDTO basicStockProductDTO;

    List<BasicStockProduct> basicStockProducts = new ArrayList<>();

    @BeforeEach
    void setUp() {
        basicStockProductService = new BasicStockProductService
            (basicStockProductRepository, basicStockProductDTOMapper);

        User user = new User(-1L, "Test User", "testUser@topshelf.com", "123456", Role.USER);
        jwtToken = jwtService.generateToken(user);
        userRepository.save(user);

        pantry = new Pantry();
        pantry.setName("Test Pantry");
        pantryRepository.save(pantry);

        basicStockProductDTO = new BasicStockProductDTO
                (-1L, pantry.getPantryId(), "NieuwProduct", 2);
        pantry.setBasicStock(basicStockProducts);
    }

    @Test
    @DisplayName("Test to see of a product can be added in the basicStock of a pantry")
    void addBasicStockProductToPantry_success() throws Exception {

        // Get the amount of basicStockProducts in the minimum stock of the pantry, before adding a product
        int pantryStockSize = basicStockProductService.findBasicStockProductByPantry(pantry).size();

        String jsonRequest = mapper.writeValueAsString(basicStockProductDTO);

        MvcResult result = mockMvc
                .perform(post("/basicstockproduct/add")
                .header("Authorization", "Bearer " + jwtToken)
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        // Compare the expected response to the actual response, and check of there is now 1 more product
        // in the minimum stock
        Assertions.assertAll(() -> assertEquals(200, result.getResponse().getStatus()),
                () -> assertEquals(pantryStockSize + 1,
                        basicStockProductService.findBasicStockProductByPantry(pantry).size()));
    }
}


