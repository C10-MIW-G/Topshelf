package nl.miwgroningen.ch10.topshelf.service;

import nl.miwgroningen.ch10.topshelf.dto.GroceryProductDTO;
import nl.miwgroningen.ch10.topshelf.exception.GroceryProductNotFoundException;
import nl.miwgroningen.ch10.topshelf.exception.ProductAlreadyAddedException;
import nl.miwgroningen.ch10.topshelf.mapper.GroceryProductDTOMapper;
import nl.miwgroningen.ch10.topshelf.model.GroceryProduct;
import nl.miwgroningen.ch10.topshelf.model.Pantry;
import nl.miwgroningen.ch10.topshelf.repository.GroceryProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Auteur Jessica Schouten.
 * <p>
 * Controls the GroceryProductRepository
 */

@Service
public class GroceryProductService {
    private final GroceryProductRepository groceryProductRepository;
    private final GroceryProductDTOMapper groceryProductDTOMapper;

    @Autowired
    public GroceryProductService(GroceryProductRepository groceryProductRepository,
                                 GroceryProductDTOMapper groceryProductDTOMapper) {
        this.groceryProductRepository = groceryProductRepository;
        this.groceryProductDTOMapper = groceryProductDTOMapper;
    }

    //This method isn't used, but made for the sake of portfolio purpose.
    public GroceryProduct findGroceryProductByGroceryProductId(Long groceryProductId) {
        return groceryProductRepository.findGroceryProductByGroceryProductId(groceryProductId)
                .orElseThrow(() -> new GroceryProductNotFoundException(
                        "Grocery product with id: " + groceryProductId + " was not found"));
    }

    public List<GroceryProductDTO> findGroceryProductByPantry(Pantry pantry) throws GroceryProductNotFoundException {
        return groceryProductRepository.findGroceryProductByPantry(pantry)
                .stream()
                .map(groceryProductDTOMapper)
                .toList();
    }

    public void save(GroceryProductDTO groceryProductDTO) {
        GroceryProduct groceryProduct = groceryProductDTOMapper.convertFromDTO(groceryProductDTO);
        Optional<GroceryProduct> existingGroceryProduct = findProductWithSameNameInPantry(groceryProduct);

        if (existingGroceryProduct.isPresent()) {
            throw new ProductAlreadyAddedException("Product is already added to the grocery list!");
        } else {
            groceryProductRepository.save(groceryProduct);
        }
    }

    protected Optional<GroceryProduct> findProductWithSameNameInPantry(GroceryProduct groceryProduct) {
        return groceryProductRepository.findGroceryProductByPantryAndProductDefinition
                (groceryProduct.getPantry(), groceryProduct.getProductDefinition());
    }

    public void deleteGroceryProduct(Long groceryProductId) {
        groceryProductRepository.deleteById(groceryProductId);
    }
}