package nl.miwgroningen.ch10.topshelf.controller;

import nl.miwgroningen.ch10.topshelf.dto.StockProductDTO;
import nl.miwgroningen.ch10.topshelf.service.StockProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class StockProductResource {

    private final StockProductService stockProductService;

    public StockProductResource(StockProductService stockProductService) {
        this.stockProductService = stockProductService;
    }

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

    @GetMapping("/pantry/{pantryId}")
    public ResponseEntity<List<StockProductDTO>> getStockProductByPantryId(@PathVariable("pantryId") Long pantryId) {
        List<StockProductDTO> stockProduct = stockProductService.findStockProductByPantryId(pantryId);
        return new ResponseEntity<>(stockProduct, HttpStatus.OK);
    }
}
