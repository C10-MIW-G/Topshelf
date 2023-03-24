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
            String groceryProductName = setProductNameToUpperCase(groceryProductToBeSaved);
            GroceryProductDTO groceryProductDTO = new GroceryProductDTO(
                    groceryProductToBeSaved.groceryProductId(),
                    groceryProductToBeSaved.pantryId(),
                    groceryProductName,
                    groceryProductToBeSaved.amount()
            );
            groceryProductService.save(groceryProductDTO);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete/{groceryProductId}")
    public ResponseEntity<String> deleteGroceryProduct(
            @PathVariable("groceryProductId") Long groceryProductId) {
        groceryProductService.deleteGroceryProduct(groceryProductId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public String setProductNameToUpperCase (GroceryProductDTO groceryProductDTO) {
        String nameToBeAdjusted = groceryProductDTO.name();

        return nameToBeAdjusted.substring(0, 1).toUpperCase() + nameToBeAdjusted.substring(1);
    }
}
