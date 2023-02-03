package nl.miwgroningen.ch10.topshelf.controller;

import nl.miwgroningen.ch10.topshelf.model.StockProduct;
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
    public ResponseEntity<List<StockProduct>> getAllStockProducts() {
        List<StockProduct> stockProducts = stockProductService.findAllStockProducts();
        return new ResponseEntity<>(stockProducts, HttpStatus.OK);
    }

    @GetMapping("/details/{stockProductId}")
    public ResponseEntity<StockProduct> getStockProductById(@PathVariable("stockProductId") Long stockProductId) {
        StockProduct stockProduct = stockProductService.findStockProductByStockProductId(stockProductId);
        return new ResponseEntity<>(stockProduct, HttpStatus.OK);
    }

    @GetMapping("/pantry/{pantryId}")
    public ResponseEntity<List<StockProduct>> getStockProductByPantryId(@PathVariable("pantryId") Long pantryId) {
        List<StockProduct> stockProduct = stockProductService.findStockProductByPantryId(pantryId);
        return new ResponseEntity<>(stockProduct, HttpStatus.OK);
    }

}
