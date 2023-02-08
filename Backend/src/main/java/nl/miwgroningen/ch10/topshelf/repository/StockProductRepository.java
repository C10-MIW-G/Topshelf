package nl.miwgroningen.ch10.topshelf.repository;

import nl.miwgroningen.ch10.topshelf.model.StockProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockProductRepository extends JpaRepository<StockProduct, Long> {

    Optional<StockProduct> findStockProductByStockProductId(Long stockProductId);
}