package nl.miwgroningen.ch10.topshelf.mapper;

import nl.miwgroningen.ch10.topshelf.dto.StockProductDTO;
import nl.miwgroningen.ch10.topshelf.model.StockProduct;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * @author Inge Dikland
 *
 */
@Service
public class StockProductDTOMapper implements Function<StockProduct, StockProductDTO> {
    public StockProductDTO apply (StockProduct stockProduct) {
        return new StockProductDTO(
                stockProduct.getStockProductId(),
                stockProduct.getPantry().getPantryId(),
                stockProduct.getProductDefinition().getName(),
                stockProduct.getExpirationDate()
        );
    }
}
