package nl.miwgroningen.ch10.topshelf.controller;

import lombok.RequiredArgsConstructor;

import nl.miwgroningen.ch10.topshelf.dto.StockProductDTO;
import nl.miwgroningen.ch10.topshelf.model.Pantry;
import nl.miwgroningen.ch10.topshelf.model.User;
import nl.miwgroningen.ch10.topshelf.security.config.JwtService;
import nl.miwgroningen.ch10.topshelf.service.StockProductService;
import nl.miwgroningen.ch10.topshelf.service.UserService;
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
@RequestMapping("/stockproduct")
@RequiredArgsConstructor
public class StockProductController {

    private final StockProductService stockProductService;
    private final JwtService jwtService;
    private final UserService userService;


    @GetMapping("/{pantryId}")
    public ResponseEntity<List<StockProductDTO>> getStockProductByPantryId(@PathVariable("pantryId") Pantry pantry,
    @RequestHeader (name = "Authorization") String jwt ) {
        String username = jwtService.extractUsername(jwt.substring(7));
        User user = userService.findUserByUsername(username);
        if (stockProductService.checkIfUserBelongsToPantry(pantry, user)) {
            List<StockProductDTO> stockProduct = stockProductService
                    .findStockProductByPantryOrderByExpirationDate(pantry);
            return new ResponseEntity<>(stockProduct, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PostMapping("/add")
    public ResponseEntity<String> saveStockProductToPantryStock(
            @RequestBody StockProductDTO pantryStockProductToBeSaved, BindingResult result) {
        if (!result.hasErrors()) {
            String stockProductName = setProductNameToUpperCase(pantryStockProductToBeSaved);
            StockProductDTO stockProductDTO = new StockProductDTO(
                    pantryStockProductToBeSaved.stockProductId(),
                    pantryStockProductToBeSaved.pantryId(),
                    stockProductName,
                    pantryStockProductToBeSaved.expirationDate(),
                    pantryStockProductToBeSaved.stockStatus());

            stockProductService.save(stockProductDTO);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{stockProductId}")
    public ResponseEntity<String> deleteStockProduct(
            @PathVariable("stockProductId") Long stockProductId) {
        stockProductService.deleteStockProduct(stockProductId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public String setProductNameToUpperCase(StockProductDTO stockProductDTO) {
        String nameToBeAdjusted = stockProductDTO.name();

        return nameToBeAdjusted.substring(0, 1).toUpperCase() + nameToBeAdjusted.substring(1);
    }
}
