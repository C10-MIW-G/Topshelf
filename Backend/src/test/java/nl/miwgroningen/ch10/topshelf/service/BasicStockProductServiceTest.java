package nl.miwgroningen.ch10.topshelf.service;

import nl.miwgroningen.ch10.topshelf.dto.BasicStockProductDTO;
import nl.miwgroningen.ch10.topshelf.exception.ProductAlreadyAddedException;
import nl.miwgroningen.ch10.topshelf.mapper.BasicStockProductDTOMapper;
import nl.miwgroningen.ch10.topshelf.model.BasicStockProduct;
import nl.miwgroningen.ch10.topshelf.model.Pantry;
import nl.miwgroningen.ch10.topshelf.model.ProductDefinition;
import nl.miwgroningen.ch10.topshelf.repository.BasicStockProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * @author Jack Wieringa <j.w.wieringa@st.hanze.nl>
 * <p>
 * Test function for BasicStockProductService.
 */

@ExtendWith(MockitoExtension.class)
public class BasicStockProductServiceTest {

    @Mock
    BasicStockProductRepository basicStockProductRepository;
    @Mock
    BasicStockProductDTOMapper basicStockProductDTOMapper;
    @InjectMocks
    BasicStockProductService basicStockProductService;

    @Test
    void productAlreadyAddedExceptionTest() {
        BasicStockProductDTO basicStockProductDTO =
                new BasicStockProductDTO(1L, 2L, "Pasta", 5);
        BasicStockProduct basicStockProduct =
                new BasicStockProduct(1L, 5, new ProductDefinition(), new Pantry());
        when(basicStockProductDTOMapper.convertFromDTO(basicStockProductDTO)).thenReturn(basicStockProduct);
        when(basicStockProductRepository
                .findBasicStockProductByProductDefinition(basicStockProduct.getProductDefinition()))
                .thenReturn(Optional.of(basicStockProduct));
        assertThrows(ProductAlreadyAddedException.class,
                () -> basicStockProductService.save(basicStockProductDTO));
    }
}
