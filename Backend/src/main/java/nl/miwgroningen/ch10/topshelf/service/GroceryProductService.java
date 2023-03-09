package nl.miwgroningen.ch10.topshelf.service;
import nl.miwgroningen.ch10.topshelf.dto.GroceryProductDTO;
import nl.miwgroningen.ch10.topshelf.mapper.GroceryProductDTOMapper;
import nl.miwgroningen.ch10.topshelf.model.GroceryProduct;
import nl.miwgroningen.ch10.topshelf.model.Pantry;
import nl.miwgroningen.ch10.topshelf.repository.GroceryProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

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

    public List<GroceryProductDTO> findGroceryProductByPantry(Pantry pantry) {
        return groceryProductRepository.findGroceryProductByPantry(pantry)
                .stream()
                .map(groceryProductDTOMapper)
                .toList();
    }

    public void save(GroceryProductDTO groceryProductDTO) {
        GroceryProduct groceryProduct = groceryProductDTOMapper.convertFromDTO(groceryProductDTO);
        groceryProductRepository.save(groceryProduct);
    }

    public void deleteGroceryProduct(Long groceryProductId) { groceryProductRepository.deleteById(groceryProductId); }
}
