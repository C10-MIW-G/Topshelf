package nl.miwgroningen.ch10.topshelf.controller;

import nl.miwgroningen.ch10.topshelf.model.Pantry;
import nl.miwgroningen.ch10.topshelf.service.PantryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Robbin Drent <r.v.drent@st.hanze.nl>
 * <p>
 * Talks with the frontend
 */

@CrossOrigin(origins = "http://locolhost:4200")
@RestController
@RequestMapping("/pantry")
public class PantryResource {

    private final PantryService pantryService;


    public PantryResource(PantryService pantryService) {
        this.pantryService = pantryService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Pantry>> getAllPantries() {
        List<Pantry> pantries = pantryService.findAllPantries();
        return new ResponseEntity<>(pantries, HttpStatus.OK);
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<Pantry> getPantryById(@PathVariable("id") Long id) {
        Pantry pantry = pantryService.findPantryById(id);
        return new ResponseEntity<>(pantry, HttpStatus.OK);
    }
}
