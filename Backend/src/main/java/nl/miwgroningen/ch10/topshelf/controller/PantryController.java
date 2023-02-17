package nl.miwgroningen.ch10.topshelf.controller;

import nl.miwgroningen.ch10.topshelf.dto.PantryDTO;
import nl.miwgroningen.ch10.topshelf.service.PantryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Robbin Drent <r.v.drent@st.hanze.nl>
 * Talks with the frontend
 */

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/pantry")
public class PantryController {

    private final PantryService pantryService;

    public PantryController(PantryService pantryService) {
        this.pantryService = pantryService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PantryDTO>> getAllPantries() {
        List<PantryDTO> pantries = pantryService.findAllPantries();
        return new ResponseEntity<>(pantries, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<String> addPantry(@RequestBody PantryDTO pantryDTO) {
        pantryService.addPantry(pantryDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}