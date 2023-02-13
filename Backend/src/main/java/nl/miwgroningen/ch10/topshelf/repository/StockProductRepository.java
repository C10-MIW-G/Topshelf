package nl.miwgroningen.ch10.topshelf.repository;

import nl.miwgroningen.ch10.topshelf.model.Pantry;
import nl.miwgroningen.ch10.topshelf.model.StockProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StockProductRepository extends JpaRepository<StockProduct, Long> {
    List<StockProduct> findStockProductsByPantry(Pantry pantry);

    Optional<StockProduct> findStockProductByStockProductId(Long stockProductId);
}
