package nl.miwgroningen.ch10.topshelf.repository;

import nl.miwgroningen.ch10.topshelf.model.Pantry;
import nl.miwgroningen.ch10.topshelf.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PantryRepository extends JpaRepository<Pantry, Long> {

    Optional<Pantry> findPantryByPantryId(Long pantryId);

    List<Pantry> findPantriesByUsers(User user);

    List<User> findUsersByPantryId(Long pantryId);


}
