package nl.miwgroningen.ch10.topshelf.PantryTests;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import nl.miwgroningen.ch10.topshelf.dto.PantryDTO;
import nl.miwgroningen.ch10.topshelf.mapper.PantryDTOMapper;
import nl.miwgroningen.ch10.topshelf.model.Pantry;
import nl.miwgroningen.ch10.topshelf.repository.PantryRepository;
import nl.miwgroningen.ch10.topshelf.service.PantryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class PantryServiceTest {

    @Mock
    private PantryDTOMapper pantryDTOMapper;
    @Mock
    private PantryRepository pantryRepository;
    @InjectMocks
    private PantryService pantryService;

    @BeforeEach
    public void setUp() {
        openMocks(this);
    }

    @Test
    public void testAddPantry() {
        // Create a mocked PantryDTO and Pantry objects
        PantryDTO mockedPantryDTO = mock(PantryDTO.class);
        Pantry mockedPantry = mock(Pantry.class);

        // Set up the mock behavior of the pantryDTOMapper
        when(pantryDTOMapper.toPantry(mockedPantryDTO)).thenReturn(mockedPantry);

        // Set up the mock behavior of the pantryRepository
        when(pantryRepository.save(mockedPantry)).thenReturn(mockedPantry);

        // Call the addPantry method
        Pantry resultPantry = pantryService.addPantry(mockedPantryDTO);

        // Verify that the method returns the mocked Pantry object
        Assertions.assertEquals(mockedPantry, resultPantry);
    }
}