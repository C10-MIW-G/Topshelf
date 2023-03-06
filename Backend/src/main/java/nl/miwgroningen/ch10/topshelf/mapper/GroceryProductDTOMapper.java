package nl.miwgroningen.ch10.topshelf.mapper;

import lombok.RequiredArgsConstructor;
import nl.miwgroningen.ch10.topshelf.dto.GroceryProductDTO;
import nl.miwgroningen.ch10.topshelf.model.GroceryProduct;
import nl.miwgroningen.ch10.topshelf.service.PantryService;
import nl.miwgroningen.ch10.topshelf.service.ProductDefinitionService;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * Auteur Jessica Schouten.
 * <p>
 * Makes it possible to use DTO's
 */

@Service
@RequiredArgsConstructor
public class GroceryProductDTOMapper implements Function<GroceryProduct, GroceryProductDTO> {

    private final PantryService pantryService;
    private final ProductDefinitionService productDefinitionService;

    public GroceryProductDTO apply(GroceryProduct groceryProduct) {
        return new GroceryProductDTO(
                groceryProduct.getGroceryProductId(),
                groceryProduct.getPantry().getPantryId(),
                groceryProduct.getProductDefinition().getName(),
                groceryProduct.getAmount()
        );
    }

    public GroceryProduct convertFromDTO(GroceryProductDTO groceryProductDTO) {
        return new GroceryProduct(
                groceryProductDTO.groceryProductId(),
                groceryProductDTO.amount(),
                productDefinitionService.findProductByName(groceryProductDTO.name()),
                pantryService.findPantryByPantryId(groceryProductDTO.pantryId())
        );
    }
}
