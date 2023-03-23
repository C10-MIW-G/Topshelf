package nl.miwgroningen.ch10.topshelf.PantryTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.miwgroningen.ch10.topshelf.TopshelfApplication;
import nl.miwgroningen.ch10.topshelf.dto.PantryDTO;
import nl.miwgroningen.ch10.topshelf.mapper.PantryDTOMapper;
import nl.miwgroningen.ch10.topshelf.model.Pantry;
import nl.miwgroningen.ch10.topshelf.model.Role;
import nl.miwgroningen.ch10.topshelf.model.User;
import nl.miwgroningen.ch10.topshelf.repository.PantryRepository;
import nl.miwgroningen.ch10.topshelf.repository.UserRepository;
import nl.miwgroningen.ch10.topshelf.security.config.JwtService;
import nl.miwgroningen.ch10.topshelf.service.PantryService;
import nl.miwgroningen.ch10.topshelf.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

/**
 * @author Jack Wieringa <j.w.wieringa@st.hanze.nl>
 * <p>
 * Integration test for Pantry
 */

@SpringBootTest(classes = TopshelfApplication.class)
@AutoConfigureMockMvc
@Transactional
public class PantryIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @Autowired
    PantryDTOMapper pantryDTOMapper;
    @Autowired
    PantryService pantryService;
    @Autowired
    UserService userService;
    @Autowired
    JwtService jwtService;
    @Autowired
    PantryRepository pantryRepository;
    @Autowired
    UserRepository userRepository;

    String jwtToken;


    @BeforeEach
    void setUp() {
        pantryService = new PantryService(pantryRepository, pantryDTOMapper, userService);
        User user = new User(501L, "Peter", "peter@topshelf.com", "123456", Role.USER);
        jwtToken = jwtService.generateToken(user);
        userRepository.save(user);
    }

    @Test
    @DisplayName("When calling for a endpoint without authentication, a 403 http error is expected")
    public void testUnAuthenticatedEndPoint() throws Exception {
        mockMvc.perform(get("/pantry/all"))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("When calling for a endpoint with authentication, a status OK is expected")
    @WithMockUser(username = "Tom", roles = "User")
    public void testAuthenticatedEndPoint() throws Exception {
        mockMvc.perform(get("/pantry/all"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("With a post request, a new pantry should be created")
    public void testPantryAdding() throws Exception {
        //Arrange
        PantryDTO pantry = new PantryDTO(1001L, "Snackbar");
        String jsonRequest = mapper.writeValueAsString(pantry);

        //Activate
        MvcResult result = mockMvc
                .perform(post("/pantry/add")
                        .header("Authorization", "Bearer " + jwtToken)
                        .content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();

        Pantry newPantry = pantryService.findPantryByName(pantry.name());

        //Assert
        Assertions.assertAll(() -> assertEquals(201, result.getResponse().getStatus()),
                () -> assertEquals(pantry.name(), newPantry.getName()));
    }
}
