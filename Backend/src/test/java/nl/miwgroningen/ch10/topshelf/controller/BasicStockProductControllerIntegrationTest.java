package nl.miwgroningen.ch10.topshelf.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.miwgroningen.ch10.topshelf.TopshelfApplication;
import nl.miwgroningen.ch10.topshelf.dto.BasicStockProductDTO;
import nl.miwgroningen.ch10.topshelf.mapper.BasicStockProductDTOMapper;
import nl.miwgroningen.ch10.topshelf.mapper.PantryDTOMapper;
import nl.miwgroningen.ch10.topshelf.model.*;
import nl.miwgroningen.ch10.topshelf.repository.BasicStockProductRepository;
import nl.miwgroningen.ch10.topshelf.repository.PantryRepository;
import nl.miwgroningen.ch10.topshelf.repository.ProductDefinitionRepository;
import nl.miwgroningen.ch10.topshelf.repository.UserRepository;
import nl.miwgroningen.ch10.topshelf.security.config.JwtService;
import nl.miwgroningen.ch10.topshelf.service.BasicStockProductService;
import nl.miwgroningen.ch10.topshelf.service.PantryService;
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
    PantryService pantryService;

    @Autowired
    PantryDTOMapper pantryDTOMapper;

    @Autowired
    PantryRepository pantryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtService jwtService;

    String jwtToken;
    @Autowired
    ProductDefinitionRepository productDefinitionRepository;

    @BeforeEach
    void setUp() {
        basicStockProductService = new BasicStockProductService
            (basicStockProductRepository, basicStockProductDTOMapper);

        User user = new User(501L, "Peter", "peter@topshelf.com", "123456", Role.USER);
        jwtToken = jwtService.generateToken(user);
        userRepository.save(user);

//        Pantry pantry = new Pantry(-2L, "Pantry Name");
//        ProductDefinition productDefinition = new ProductDefinition("Product Naam");
//        productDefinitionRepository.save(productDefinition);
//        pantryRepository.save(pantry);
//        System.out.println(pantry.getName());
//        System.out.println(pantry.getPantryId());
//        String pantryName = pantry.getName();
//        BasicStockProductDTO basicStockProduct = new BasicStockProductDTO(-2L, pantryService.findPantryByName(pantryName).getPantryId(), "Product Naam", 2);
//        basicStockProductService.save(basicStockProduct);
    }

    @Test
    @DisplayName("Test if you can edit a basicStockProduct")
    void editBasicStockProduct_success() throws Exception {

        Pantry pantry = new Pantry();
        pantry.setName("Test Pantry");
        pantryRepository.save(pantry);

        ProductDefinition productDefinition = new ProductDefinition();
        productDefinition.setName("Test Product");
        productDefinitionRepository.save(productDefinition);

        BasicStockProduct basicStockProduct = new BasicStockProduct();
        basicStockProduct.setProductDefinition(productDefinition);
        basicStockProduct.setAmount(2);
        basicStockProduct.setPantry(pantry);
        productDefinitionRepository.save(productDefinition);
        List<BasicStockProduct> basicStockProducts = new ArrayList<>();
        pantryRepository.save(pantry);
        System.out.println(basicStockProduct.getPantry().getPantryId());

        BasicStockProductDTO basicStockProductDTO = new BasicStockProductDTO
                (basicStockProduct.getBasicStockProductId(), pantry.getPantryId(), "EditedName", 2);

        String jsonRequest = mapper.writeValueAsString(basicStockProductDTO);
        String token =
                "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyb2JiaW4iLCJpYXQiOjE2Nzk1NzMwMTksImV4cCI6MTY3OTY1OTQxOX0.oGod2njXck2fDkIn3lmOo4SB3A25WfIe8IfFEieggx0";

        MvcResult result = mockMvc
                .perform(put("/basicstockproduct/edit")
                .header("Authorization", "Bearer " + jwtToken)
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();

        Assertions.assertAll(() -> assertEquals(201, result.getResponse().getStatus()),
                () -> assertEquals(basicStockProductRepository.findBasicStockProductByBasicStockProductId(-2L).get()
                        .getProductDefinition().getName(),
                        "edited product"));
    }
//
//    @Test
//    @DisplayName("Test is you can edit a basicStockProduct")
//    void editBasicStockProduct_failed() throws Exception {
//        BasicStockProductDTO basicStockProductDTO = new BasicStockProductDTO
//                (252L, 5L, "EditedName", 2);
//
//        String jsonRequest = mapper.writeValueAsString(basicStockProductDTO);
//        String token =
//                "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyb2JiaW4iLCJpYXQiOjE2Nzk1NzMwMTksImV4cCI6MTY3OTY1OTQxOX0.oGod2njXck2fDkIn3lmOo4SB3A25WfIe8IfFEieggx0";
//
//        MvcResult result = mockMvc
//                .perform(put("/basicstockproduct/edit")
//                        .header("Authorization", "Bearer " + token)
//                        .content(jsonRequest)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated()).andReturn();
//
//        Assertions.assertAll(() -> assertEquals(201, result.getResponse().getStatus()),
//                () -> assertEquals(basicStockProductRepository.findBasicStockProductByBasicStockProductId(252L).get()
//                                .getProductDefinition().getName(),
//                        basicStockProductDTO.name()));
//    }
//
//    @Test
//    void addBasicStockProductToPantry_success() throws Exception {
//        int pantryStockSize = pantryService.findPantryByPantryId(5L).getBasicStock().size();
//        BasicStockProductDTO basicStockProductDTO = new BasicStockProductDTO
//                (-1L, 5L, "NieuwProduct", 2);
////        int pantryStockSize = pantryService.findPantryByPantryId(5L).getBasicStock().size();
//
//        String jsonRequest = mapper.writeValueAsString(basicStockProductDTO);
//        String token =
//                "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyb2JiaW4iLCJpYXQiOjE2Nzk1NzMwMTksImV4cCI6MTY3OTY1OTQxOX0.oGod2njXck2fDkIn3lmOo4SB3A25WfIe8IfFEieggx0";
//
//        MvcResult result = mockMvc
//                .perform(post("/basicstockproduct/add")
//                .header("Authorization", "Bearer " + token)
//                .content(jsonRequest)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk()).andReturn();
//
//        Assertions.assertAll(() -> assertEquals(200, result.getResponse().getStatus()),
//                () -> assertEquals(pantryStockSize + 1,
//                        pantryService.findPantryByPantryId(5L).getBasicStock().size()));
//    }

//    @Test
//    void addBasicStockProductToPantry_failed() throws Exception {
//        BasicStockProductDTO basicStockProductDTO = new BasicStockProductDTO
//                (-2L, 5L, "Vlees", 2);
//
//        String jsonRequest = mapper.writeValueAsString(basicStockProductDTO);
//        String token =
//                "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyb2JiaW4iLCJpYXQiOjE2Nzk0ODA2NzEsImV4cCI6MTY3OTU2NzA3MX0" +
//                        ".o_INQGggKV3RRE5RQhSu8aBi8MMBNyygfAQmj4KMZ-w";
//
//        MvcResult result = mockMvc
//                .perform(post("/basicstockproduct/add")
//                        .header("Authorization", "Bearer " + token)
//                        .content(jsonRequest)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(assertThrowsExactly(product)).andReturn();

    }


