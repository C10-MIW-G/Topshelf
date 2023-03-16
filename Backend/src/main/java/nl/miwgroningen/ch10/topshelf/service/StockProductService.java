package nl.miwgroningen.ch10.topshelf.service;
import nl.miwgroningen.ch10.topshelf.dto.StockProductDTO;
import nl.miwgroningen.ch10.topshelf.mapper.StockProductDTOMapper;
import nl.miwgroningen.ch10.topshelf.model.ProductDefinition;
import nl.miwgroningen.ch10.topshelf.model.StockProduct;
import nl.miwgroningen.ch10.topshelf.model.Pantry;
import nl.miwgroningen.ch10.topshelf.model.User;
import nl.miwgroningen.ch10.topshelf.repository.StockProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jack Wieringa <j.w.wieringa@st.hanze.nl>
 * Controls the stockProductRepository
 */

@Service
public class StockProductService {
    private final StockProductRepository stockProductRepository;
    private final StockProductDTOMapper stockProductDTOMapper;
    private final PantryService pantryService;
    private final ProductDefinitionService productDefinitionService;
    private final BasicStockProductService basicStockProductService;

    @Autowired
    public StockProductService(StockProductRepository stockProductRepository,
                               StockProductDTOMapper stockProductDTOMapper,
                               PantryService pantryService,
                               ProductDefinitionService productDefinitionService,
                               BasicStockProductService basicStockProductService) {
        this.stockProductRepository = stockProductRepository;
        this.stockProductDTOMapper = stockProductDTOMapper;
        this.pantryService = pantryService;
        this.productDefinitionService = productDefinitionService;
        this.basicStockProductService = basicStockProductService;
    }

    public List<StockProductDTO> findStockProductByPantryOrderByExpirationDate(Pantry pantry) {
        return stockProductRepository.findStockProductsByPantryOrderByExpirationDate(pantry)
                .stream()
                .peek(this::setStockStatus)
                .map(stockProductDTOMapper)
                .toList();
    }

    public void save(StockProductDTO pantryStockProductToBeSaved) {
        StockProduct stockProduct = stockProductDTOMapper.convertFromDTO(pantryStockProductToBeSaved);
        stockProductRepository.save(stockProduct);
    }

    public void deleteStockProduct(Long stockProductId){
        stockProductRepository.deleteById(stockProductId);
    }

    public boolean checkIfUserBelongsToPantry(Pantry pantry, User user){
       return pantry.getUsers().contains(user);
    }

    public int countStockProductByProductDefinition(ProductDefinition productDefinition, Pantry pantry){
        return stockProductRepository.countStockProductByProductDefinitionAndPantry(productDefinition, pantry);
    }

    public void setStockStatus(StockProduct stockProduct){
        Pantry pantry =  pantryService.findPantryByPantryId(stockProduct.getPantry().getPantryId());
        ProductDefinition productDefinition =
                productDefinitionService.findProductByName(stockProduct.getProductDefinition().getName());
        int count = this.countStockProductByProductDefinition(productDefinition, pantry);
        int amount = basicStockProductService.findBasicStockAmountByName(pantry, productDefinition);

        stockProduct.setStockStatus(count < amount && amount != 0);
    }
}
