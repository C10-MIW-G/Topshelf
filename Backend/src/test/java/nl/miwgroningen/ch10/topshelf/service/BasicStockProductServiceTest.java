package nl.miwgroningen.ch10.topshelf.service;

import nl.miwgroningen.ch10.topshelf.dto.BasicStockProductDTO;
import nl.miwgroningen.ch10.topshelf.exception.ProductWithNameAlreadyExistsException;
import nl.miwgroningen.ch10.topshelf.mapper.BasicStockProductDTOMapper;
import nl.miwgroningen.ch10.topshelf.model.BasicStockProduct;
import nl.miwgroningen.ch10.topshelf.model.Pantry;
import nl.miwgroningen.ch10.topshelf.model.ProductDefinition;
import nl.miwgroningen.ch10.topshelf.repository.BasicStockProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * @author Robbin Drent <r.v.drent@st.hanze.nl>
 * <p>
 * Tests the function to edit products in your Minimum Stock.
 */

@ExtendWith(MockitoExtension.class)
public class BasicStockProductServiceTest {

    @Mock
    BasicStockProductRepository basicStockProductRepository;

    @Mock
    BasicStockProductDTOMapper basicStockProductDTOMapper;

    @InjectMocks
    private BasicStockProductService basicStockProductService;

    @BeforeEach
    public void addBasicStockProductTest() {
        basicStockProductService = new BasicStockProductService
                (basicStockProductRepository, basicStockProductDTOMapper);
    }

    @Test
    @DisplayName("Test exception when name already exists")
    void testEditNameExists() {
        BasicStockProductDTO basicStockProductToEdit = new BasicStockProductDTO(-3L, -2L, "ProductToBeEdited", 2);
        BasicStockProduct basicStockProduct = new BasicStockProduct(-2L, 2, new ProductDefinition(), new Pantry());

        when(basicStockProductDTOMapper.convertFromDTO(basicStockProductToEdit)).thenReturn(basicStockProduct);
        when(basicStockProductService.findSameProductInPantry(basicStockProduct)).thenReturn(Optional.of(basicStockProduct));
        when(basicStockProductService.findProductWithSameNameInPantry(basicStockProduct)).thenReturn((Optional.of(basicStockProduct)));

        assertThrows(ProductWithNameAlreadyExistsException.class, () -> basicStockProductService.edit(basicStockProductToEdit));
    }

    @Test
    @DisplayName("When name is not edited")
    void testEditNameNotChanged() {
        ProductDefinition productToBeEdited = new ProductDefinition("ProductToBeEdited");
        BasicStockProductDTO basicStockProductToEdit =
                new BasicStockProductDTO(-3L, -2L, "ProductToBeEdited", 2);
        BasicStockProduct basicStockProduct =
                new BasicStockProduct(-2L, 2, productToBeEdited, new Pantry());

        when(basicStockProductService.findSameProductInPantry(basicStockProduct))
                .thenReturn(Optional.of(basicStockProduct));

        assertEquals(basicStockProductToEdit.name(),
                basicStockProductService.findSameProductInPantry(basicStockProduct).
                        get().getProductDefinition().getName());
    }

    @Test
    @DisplayName("When edited name is still available")
    void testProductWithNewName() {
        ProductDefinition productToBeEdited = new ProductDefinition("ProductToBeEdited");
        BasicStockProduct basicStockProduct = new BasicStockProduct(-3L, 2, productToBeEdited, new Pantry());
        BasicStockProductDTO basicStockProductToEdit =
                new BasicStockProductDTO(-3L, -3L, "ProductWithNewName", 2);

        when(basicStockProductService.findProductWithSameNameInPantry(basicStockProduct)).thenReturn (Optional.of(basicStockProduct));

        assertNotEquals(basicStockProductToEdit.name(),
                basicStockProductService.findProductWithSameNameInPantry
                        (basicStockProduct).get().getProductDefinition().getName());
    }
}