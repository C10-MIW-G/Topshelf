package nl.miwgroningen.ch10.topshelf.service;

import nl.miwgroningen.ch10.topshelf.exception.PantryNotFoundException;
import nl.miwgroningen.ch10.topshelf.exception.StockProductNotFoundException;
import nl.miwgroningen.ch10.topshelf.model.Pantry;
import nl.miwgroningen.ch10.topshelf.model.StockProduct;
import nl.miwgroningen.ch10.topshelf.repository.PantryRepository;
import nl.miwgroningen.ch10.topshelf.repository.StockProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Jack Wieringa <j.w.wieringa@st.hanze.nl>
 * <p>
 * Controls the stockProductRepository
 */

@Service
public class StockProductService {
    private final StockProductRepository stockProductRepository;
    private final PantryRepository pantryRepository;

    @Autowired
    public StockProductService(StockProductRepository stockProductRepository, PantryRepository pantryRepository) {
        this.stockProductRepository = stockProductRepository;
        this.pantryRepository = pantryRepository;
    }

    public StockProduct addStockProduct(StockProduct stockProduct) {
        return stockProductRepository.save(stockProduct);
    }

    public List<StockProduct> findAllStockProducts() {
        return stockProductRepository.findAll();
    }

    public StockProduct findStockProductByStockProductId(Long stockProductId) {
        return stockProductRepository.findStockProductByStockProductId(stockProductId)
                .orElseThrow(() -> new StockProductNotFoundException
                        ("StockProduct with id: " + stockProductId + " was not found!"));
    }

    public List<StockProduct> findStockProductByPantryId(Long pantryId) {
        List<StockProduct> stockProducts = stockProductRepository.findAll();
        List<StockProduct> stockProductInPantry = new ArrayList<>();

        for (StockProduct stockProduct : stockProducts) {
            if(stockProduct.getPantry().getPantryId() == pantryId) {
                stockProductInPantry.add(stockProduct);
            }
        }
        return stockProductInPantry;
    }
}
