package nl.miwgroningen.ch10.topshelf.repository;

import nl.miwgroningen.ch10.topshelf.model.Pantry;
import nl.miwgroningen.ch10.topshelf.model.ProductDefinition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductDefinitionRepository extends JpaRepository<ProductDefinition, Long> {
    Optional<ProductDefinition> findProductDefinitionByName(String name);
}
