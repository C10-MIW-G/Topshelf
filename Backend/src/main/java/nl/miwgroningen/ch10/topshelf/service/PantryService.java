package nl.miwgroningen.ch10.topshelf.service;

import nl.miwgroningen.ch10.topshelf.exception.PantryNotFoundException;
import nl.miwgroningen.ch10.topshelf.model.Pantry;
import nl.miwgroningen.ch10.topshelf.repository.PantryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Robbin Drent <r.v.drent@st.hanze.nl>
 * <p>
 * Controls the pantryRepository
 */

@Service
public class PantryService {
    private final PantryRepository pantryRepository;

    @Autowired
    public PantryService(PantryRepository pantryRepository) {
        this.pantryRepository = pantryRepository;
    }

    public Pantry addPantry(Pantry pantry) {
        return pantryRepository.save(pantry);
    }

    public List<Pantry> findAllPantries() {
        return pantryRepository.findAll();
    }

    public Pantry findPantryByPantryId(Long pantryId) {
        return pantryRepository.findPantryByPantryId(pantryId)
                .orElseThrow(() -> new PantryNotFoundException("Pantry with id: " + pantryId + " was not found!"));
    }
}
