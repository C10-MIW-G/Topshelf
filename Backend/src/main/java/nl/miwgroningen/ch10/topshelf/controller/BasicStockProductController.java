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

//    @GetMapping("/details/{basicStockProductId}")
//    public ResponseEntity<BasicStockProductDTO> getBasicStockProductById
//            (@PathVariable("basicStockProductId") Long basicStockProductId) {
//        BasicStockProductDTO basicStockProductDTO = basicStockProductService
//                .findBasicStockProductByBasicStockProductId(basicStockProductId);
//        return new ResponseEntity<>(basicStockProductDTO, HttpStatus.OK);
//    }

    @GetMapping("/{pantryId}")
    public ResponseEntity<List<BasicStockProductDTO>> getBasicStockProductByPantryId
            (@PathVariable("pantryId") Pantry pantry) {
        List<BasicStockProductDTO> basicStockProductDTO = basicStockProductService.findBasicStockProductByPantry(pantry);
        return new ResponseEntity<>(basicStockProductDTO, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<String> saveBasicStockProductToPantry(
            @RequestBody BasicStockProductDTO basicStockProductToBeSaved, BindingResult result) {
        if (!result.hasErrors()) {
            basicStockProductService.save(basicStockProductToBeSaved);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
