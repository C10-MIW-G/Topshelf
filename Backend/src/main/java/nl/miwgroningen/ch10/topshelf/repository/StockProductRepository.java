package nl.miwgroningen.ch10.topshelf.repository;

import nl.miwgroningen.ch10.topshelf.model.Pantry;
import nl.miwgroningen.ch10.topshelf.model.ProductDefinition;
import nl.miwgroningen.ch10.topshelf.model.StockProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockProductRepository extends JpaRepository<StockProduct, Long> {
    List<StockProduct> findStockProductsByPantryOrderByExpirationDate(Pantry pantry);

    int countStockProductByProductDefinitionAndPantry(ProductDefinition productDefinition, Pantry pantry);
}
