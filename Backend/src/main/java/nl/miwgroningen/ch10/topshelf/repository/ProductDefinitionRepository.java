package nl.miwgroningen.ch10.topshelf.repository;

import nl.miwgroningen.ch10.topshelf.model.ProductDefinition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDefinitionRepository extends JpaRepository<ProductDefinition, Long> {
    ProductDefinition findProductDefinitionByName(String name);
}
