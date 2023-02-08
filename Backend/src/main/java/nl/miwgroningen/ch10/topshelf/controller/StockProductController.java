package nl.miwgroningen.ch10.topshelf.controller;

import jakarta.validation.Valid;
import nl.miwgroningen.ch10.topshelf.model.Pantry;
import nl.miwgroningen.ch10.topshelf.model.ProductDefinition;
import nl.miwgroningen.ch10.topshelf.model.StockProduct;
import nl.miwgroningen.ch10.topshelf.repository.PantryRepository;
import nl.miwgroningen.ch10.topshelf.repository.ProductDefinitionRepository;
import nl.miwgroningen.ch10.topshelf.repository.StockProductRepository;
import nl.miwgroningen.ch10.topshelf.service.StockProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author Jack Wieringa <j.w.wieringa@st.hanze.nl>
 * <p>
 * Talks with the frontend
 */

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/stockproduct")
public class StockProductController {

    private final StockProductService stockProductService;
    private final PantryRepository pantryRepository;
    private final ProductDefinitionRepository productDefinitionRepository;
    private final StockProductRepository stockProductRepository;

    public StockProductController(StockProductService stockProductService, PantryRepository pantryRepository,
                                  ProductDefinitionRepository productDefinitionRepository,
                                  StockProductRepository stockProductRepository) {
        this.stockProductService = stockProductService;
        this.pantryRepository = pantryRepository;
        this.productDefinitionRepository = productDefinitionRepository;
        this.stockProductRepository = stockProductRepository;
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

    @PostMapping("/add")
    protected String saveStockProductToPantryStock(@ModelAttribute("addProductToPantryDTO") Pantry pantryStockToBeSaved,
                                                   BindingResult result, @PathVariable String pantryId) {//TODO is it a protected StockProduct or String?
        if (!result.hasErrors()) {
            pantryRepository.save(pantryStockToBeSaved);
            //TODO add to productDefinition

        }

        return "redirect:/pantry/{pantryId}";
    }
}