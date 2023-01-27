package nl.miwgroningen.ch10.topshelf.repository;

import nl.miwgroningen.ch10.topshelf.model.Pantry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PantryRepository extends JpaRepository<Pantry, Long> {

    Optional<Pantry> findPantryById(Long id);
}