package nl.miwgroningen.ch10.topshelf.controller;

import lombok.RequiredArgsConstructor;
import nl.miwgroningen.ch10.topshelf.dto.GroceryProductDTO;
import nl.miwgroningen.ch10.topshelf.model.Pantry;
import nl.miwgroningen.ch10.topshelf.service.GroceryProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Auteur Jessica Schouten.
 * <p>
 * Communicates with the frontend and database
 */

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/groceryproduct")
@RequiredArgsConstructor
public class GroceryProductController {

    private final GroceryProductService groceryProductService;

    @GetMapping("/{pantryId}")
    public ResponseEntity<List<GroceryProductDTO>> getGroceryProductsByPantryId(
            @PathVariable("pantryId") Pantry pantry) {
        List<GroceryProductDTO> groceryProductDTO = groceryProductService.findGroceryProductByPantry(pantry);
        return new ResponseEntity<>(groceryProductDTO, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> saveGroceryProductToPantry(
            @RequestBody GroceryProductDTO groceryProductToBeSaved, BindingResult result) {
        if (!result.hasErrors()) {
            groceryProductService.save(groceryProductToBeSaved);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{groceryProductId}")
    public ResponseEntity<String> deleteGroceryProduct(
            @PathVariable("groceryProductId") Long groceryProductId) {
        groceryProductService.deleteGroceryProduct(groceryProductId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
