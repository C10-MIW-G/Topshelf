package nl.miwgroningen.ch10.topshelf.mapper;

import lombok.RequiredArgsConstructor;
import nl.miwgroningen.ch10.topshelf.dto.StockProductDTO;
import nl.miwgroningen.ch10.topshelf.model.StockProduct;
import nl.miwgroningen.ch10.topshelf.service.PantryService;
import nl.miwgroningen.ch10.topshelf.service.ProductDefinitionService;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * @author Inge Dikland
 * This class makes it possible to make use of DTO's
 */
@Service
@RequiredArgsConstructor
public class StockProductDTOMapper implements Function<StockProduct, StockProductDTO> {

    private final PantryService pantryService;
    private final ProductDefinitionService productDefinitionService;

    public StockProductDTO apply (StockProduct stockProduct) {
        return new StockProductDTO(
                stockProduct.getStockProductId(),
                stockProduct.getPantry().getPantryId(),
                stockProduct.getProductDefinition().getName(),
                stockProduct.getExpirationDate()
        );
    }

    public StockProduct convertFromDTO (StockProductDTO stockProductDTO) {
        return new StockProduct(
                stockProductDTO.stockProductId(),
                stockProductDTO.expirationDate(),
                productDefinitionService.findProductByName(stockProductDTO.name()),
                pantryService.findPantryByPantryId(stockProductDTO.pantryId())
        );
    }
}
