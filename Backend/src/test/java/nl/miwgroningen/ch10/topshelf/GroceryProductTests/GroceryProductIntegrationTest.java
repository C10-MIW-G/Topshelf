package nl.miwgroningen.ch10.topshelf.GroceryProductTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.miwgroningen.ch10.topshelf.TopshelfApplication;
import nl.miwgroningen.ch10.topshelf.dto.GroceryProductDTO;
import nl.miwgroningen.ch10.topshelf.mapper.GroceryProductDTOMapper;
import nl.miwgroningen.ch10.topshelf.model.GroceryProduct;
import nl.miwgroningen.ch10.topshelf.model.Pantry;
import nl.miwgroningen.ch10.topshelf.model.Role;
import nl.miwgroningen.ch10.topshelf.model.User;
import nl.miwgroningen.ch10.topshelf.repository.GroceryProductRepository;
import nl.miwgroningen.ch10.topshelf.repository.PantryRepository;
import nl.miwgroningen.ch10.topshelf.repository.UserRepository;
import nl.miwgroningen.ch10.topshelf.security.config.JwtService;
import nl.miwgroningen.ch10.topshelf.service.GroceryProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Inge Dikland
 * <p>
 * Integration test for Grocery Product
 */
@SpringBootTest(classes = TopshelfApplication.class)
@AutoConfigureMockMvc
class GroceryProductIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @Autowired
    GroceryProductService groceryProductService;
    @Autowired
    GroceryProductRepository groceryProductRepository;
    @Autowired
    GroceryProductDTOMapper groceryProductDTOMapper;

    @Autowired
    PantryRepository pantryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtService jwtService;

    Pantry pantry;

    GroceryProductDTO groceryProductDTO;

    String jwtToken;

    List<GroceryProduct> groceryProducts = new ArrayList<>();

    @BeforeEach
    void Setup() {
        groceryProductService = new GroceryProductService(groceryProductRepository, groceryProductDTOMapper);

        User user = new User(3L, "Brenda", "brenda@topshelf.com", "123456", Role.USER);
        jwtToken = jwtService.generateToken(user);
        userRepository.save(user);

        pantry = new Pantry();
        pantry.setName("Knit2gether");
        pantryRepository.save(pantry);

        groceryProductDTO = new GroceryProductDTO(-1L, pantry.getPantryId(), "Chocolate", 2);
        pantry.setGroceryProducts(groceryProducts);
    }

    @Test
    @DisplayName("When calling for a endpoint without authentication, a 403 http error is expected")
    void testUnAuthenticatedEndPoint() throws Exception {
        mockMvc.perform(get("/groceryproduct/add"))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("With a post request, a new grocery product should be created")
    void testGroceryProductAdding() throws Exception {
        // Arrange (Get the amount of grocery products in list of the pantry before a new product is added)
        int groceryProductListSize = groceryProductService.findGroceryProductByPantry(pantry).size();

        String jsonRequest = mapper.writeValueAsString(groceryProductDTO);

        //Activate
        MvcResult result = mockMvc
                .perform(post("/groceryproduct/add")
                        .header("Authorization", "Bearer " + jwtToken)
                        .content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();

        //Assert
        Assertions.assertAll(() -> assertEquals(201, result.getResponse().getStatus()),
                () -> assertEquals(groceryProductListSize + 1,
                        groceryProductService.findGroceryProductByPantry(pantry).size()));

    }
}