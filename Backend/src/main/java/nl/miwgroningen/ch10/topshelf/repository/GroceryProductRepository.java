package nl.miwgroningen.ch10.topshelf.repository;

import nl.miwgroningen.ch10.topshelf.model.GroceryProduct;
import nl.miwgroningen.ch10.topshelf.model.Pantry;
import nl.miwgroningen.ch10.topshelf.model.ProductDefinition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroceryProductRepository extends JpaRepository<GroceryProduct, Long> {
    List<GroceryProduct> findGroceryProductByPantry(Pantry pantry);
    Optional<GroceryProduct> findGroceryProductByGroceryProductId(Long groceryProductId);
    Optional<GroceryProduct> findGroceryProductByPantryAndProductDefinition
            (Pantry pantry, ProductDefinition productDefinition);
}