package nl.miwgroningen.ch10.topshelf.mapper;

import lombok.RequiredArgsConstructor;
import nl.miwgroningen.ch10.topshelf.dto.BasicStockProductDTO;
import nl.miwgroningen.ch10.topshelf.model.BasicStockProduct;
import nl.miwgroningen.ch10.topshelf.service.PantryService;
import nl.miwgroningen.ch10.topshelf.service.ProductDefinitionService;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * @author Jack Wieringa <j.w.wieringa@st.hanze.nl>
 * This class makes it possible to make use of DTO's
 */

@Service
@RequiredArgsConstructor
public class BasicStockProductDTOMapper implements Function<BasicStockProduct, BasicStockProductDTO> {

    private final PantryService pantryService;
    private final ProductDefinitionService productDefinitionService;

    public BasicStockProductDTO apply(BasicStockProduct basicStockProduct) {
        return new BasicStockProductDTO(
                basicStockProduct.getBasicStockProductId(),
                basicStockProduct.getPantry().getPantryId(),
                basicStockProduct.getProductDefinition().getName(),
                basicStockProduct.getAmount()
        );
    }

    public BasicStockProduct convertFromDTO(BasicStockProductDTO basicStockProductDTO) {
        return new BasicStockProduct(
                basicStockProductDTO.basicStockProductId(),
                basicStockProductDTO.amount(),
                productDefinitionService.findProductByName(basicStockProductDTO.name()),
                pantryService.findPantryByPantryId(basicStockProductDTO.pantryId())
        );
    }
}
