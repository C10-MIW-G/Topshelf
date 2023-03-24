package nl.miwgroningen.ch10.topshelf.service;

import nl.miwgroningen.ch10.topshelf.dto.GroceryProductDTO;
import nl.miwgroningen.ch10.topshelf.exception.ProductAlreadyAddedException;
import nl.miwgroningen.ch10.topshelf.mapper.GroceryProductDTOMapper;
import nl.miwgroningen.ch10.topshelf.model.GroceryProduct;
import nl.miwgroningen.ch10.topshelf.model.Pantry;
import nl.miwgroningen.ch10.topshelf.model.ProductDefinition;
import nl.miwgroningen.ch10.topshelf.repository.GroceryProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

/**
 * @author Inge Dikland
 * <p>
 * Test function for GroceryProductService
 */

@ExtendWith(MockitoExtension.class)
class GroceryProductServiceTest {

    @Mock
    GroceryProductRepository groceryProductRepository;

    @Mock
    GroceryProductDTOMapper groceryProductDTOMapper;

    @InjectMocks
    GroceryProductService groceryProductService;

    @Test
    @DisplayName("Test if deleteBasicStockProduct method is throwing an exception")
    void saveGroceryProductTest() {
        GroceryProductDTO groceryProductDTO = new GroceryProductDTO
                (1L, 3L, "Feta", 15);
        GroceryProduct groceryProduct = new GroceryProduct
                (1L, 10, new ProductDefinition(), new Pantry());
        when(groceryProductDTOMapper.convertFromDTO(groceryProductDTO)).thenReturn(groceryProduct);
        when(groceryProductRepository.findGroceryProductByPantryAndProductDefinition
                (groceryProduct.getPantry(), groceryProduct.getProductDefinition()))
                .thenReturn(Optional.of(groceryProduct));
        assertThrows(ProductAlreadyAddedException.class,
                () -> groceryProductService.save(groceryProductDTO));
    }
}
