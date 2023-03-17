package nl.miwgroningen.ch10.topshelf.controller;

import lombok.RequiredArgsConstructor;
import nl.miwgroningen.ch10.topshelf.dto.BasicStockProductDTO;
import nl.miwgroningen.ch10.topshelf.model.Pantry;
import nl.miwgroningen.ch10.topshelf.service.BasicStockProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jack Wieringa <j.w.wieringa@st.hanze.nl>
 * Talks with the frontend
 */

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/basicstockproduct")
@RequiredArgsConstructor
public class BasicStockProductController {

    private final BasicStockProductService basicStockProductService;

    @GetMapping("/{pantryId}")
    public ResponseEntity<List<BasicStockProductDTO>> getBasicStockProductByPantryId
            (@PathVariable("pantryId") Pantry pantry) {
        List<BasicStockProductDTO> basicStockProductDTO =
                basicStockProductService.findBasicStockProductByPantry(pantry);
        return new ResponseEntity<>(basicStockProductDTO, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> saveBasicStockProductToPantry(
            @RequestBody BasicStockProductDTO basicStockProductToBeSaved, BindingResult result) {
        if (!result.hasErrors()) {
            String basicStockProductName = setProductNameToUpperCase(basicStockProductToBeSaved);
            BasicStockProductDTO basicStockProductDTO = new BasicStockProductDTO(
                    basicStockProductToBeSaved.basicStockProductId(),
                    basicStockProductToBeSaved.pantryId(),
                    basicStockProductName,
                    basicStockProductToBeSaved.amount());

            basicStockProductService.save(basicStockProductDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/edit")
    public ResponseEntity<String> editBasicStockProduct(
            @RequestBody BasicStockProductDTO basicStockProductToBeEdited, BindingResult result) {
        if (!result.hasErrors()) {
            basicStockProductService.edit(basicStockProductToBeEdited);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{basicStockProductId}")
    public ResponseEntity<String> deleteBasicStockProduct(
            @PathVariable("basicStockProductId") Long basicStockProductId) {
        basicStockProductService.deleteBasicStockProduct(basicStockProductId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public String setProductNameToUpperCase (BasicStockProductDTO basicStockProductDTO) {
        String nameToBeAdjusted = basicStockProductDTO.name();

        return nameToBeAdjusted.substring(0, 1).toUpperCase() + nameToBeAdjusted.substring(1);
    }
}
