package nl.miwgroningen.ch10.topshelf.controller;

import lombok.RequiredArgsConstructor;
import nl.miwgroningen.ch10.topshelf.dto.StockProductDTO;
import nl.miwgroningen.ch10.topshelf.model.Pantry;
import nl.miwgroningen.ch10.topshelf.service.StockProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jack Wieringa <j.w.wieringa@st.hanze.nl>
 * <p>
 * Talks with the frontend
 */

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/stockproduct")
@RequiredArgsConstructor
public class StockProductController {

    private final StockProductService stockProductService;

    @GetMapping("/all")
    public ResponseEntity<List<StockProductDTO>> getAllStockProducts() {
        List<StockProductDTO> stockProducts = stockProductService.findAllStockProducts();
        return new ResponseEntity<>(stockProducts, HttpStatus.OK);
    }

    @GetMapping("/details/{stockProductId}")
    public ResponseEntity<StockProductDTO> getStockProductById(@PathVariable("stockProductId") Long stockProductId) {
        StockProductDTO stockProduct = stockProductService.findStockProductByStockProductId(stockProductId);
        return new ResponseEntity<>(stockProduct, HttpStatus.OK);
    }

    @GetMapping("/{pantryId}")
    public ResponseEntity<List<StockProductDTO>> getStockProductByPantryId(@PathVariable("pantryId") Pantry pantry) {
        List<StockProductDTO> stockProduct = stockProductService.findStockProductByPantry(pantry);
        return new ResponseEntity<>(stockProduct, HttpStatus.OK);
    }

    @PostMapping("/add")
    public String saveStockProductToPantryStock(@RequestBody StockProductDTO pantryStockProductToBeSaved,
                                                BindingResult result) {
        if (!result.hasErrors()) {
            stockProductService.save(pantryStockProductToBeSaved);
        }

        return "redirect:/pantry/{pantryId}";
    }
}
