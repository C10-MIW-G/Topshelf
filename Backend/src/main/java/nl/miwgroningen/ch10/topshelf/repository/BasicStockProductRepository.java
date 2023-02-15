package nl.miwgroningen.ch10.topshelf.repository;

import nl.miwgroningen.ch10.topshelf.model.BasicStockProduct;
import nl.miwgroningen.ch10.topshelf.model.Pantry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BasicStockProductRepository extends JpaRepository<BasicStockProduct, Long> {

    List<BasicStockProduct> findBasicStockProductsByPantry(Pantry pantry);

    Optional<BasicStockProduct> findBasicStockProductByBasicStockProductId(Long basicStockProductId);
}
