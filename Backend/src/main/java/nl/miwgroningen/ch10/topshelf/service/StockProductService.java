package nl.miwgroningen.ch10.topshelf.service;
import nl.miwgroningen.ch10.topshelf.dto.StockProductDTO;
import nl.miwgroningen.ch10.topshelf.exception.StockProductNotFoundException;
import nl.miwgroningen.ch10.topshelf.mapper.StockProductDTOMapper;
import nl.miwgroningen.ch10.topshelf.model.StockProduct;
import nl.miwgroningen.ch10.topshelf.repository.StockProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Jack Wieringa <j.w.wieringa@st.hanze.nl>
 * <p>
 * Controls the stockProductRepository
 */

@Service
public class StockProductService {
    private final StockProductRepository stockProductRepository;
    private final StockProductDTOMapper stockProductDTOMapper;

    @Autowired
    public StockProductService(StockProductRepository stockProductRepository,
                               StockProductDTOMapper stockProductDTOMapper) {
        this.stockProductRepository = stockProductRepository;
        this.stockProductDTOMapper = stockProductDTOMapper;
    }

    public StockProduct addStockProduct(StockProduct stockProduct) {
        return stockProductRepository.save(stockProduct);
    }

    public List<StockProductDTO> findAllStockProducts() {
        return stockProductRepository.findAll()
                .stream()
                .map(stockProductDTOMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    public StockProductDTO findStockProductByStockProductId(Long stockProductId) {
        return stockProductRepository.findStockProductByStockProductId(stockProductId)
                .map(stockProductDTOMapper::convertToDTO)
                .orElseThrow(() -> new StockProductNotFoundException("StockProduct with id: " +
                        stockProductId + " was not found!"));
    }

    public List<StockProductDTO> findStockProductByPantryId(Long pantryId) {
        return stockProductRepository.findAll() // TODO this shouldn't be a find all according to Vincent ;-)
                .stream()
                .filter(stockProduct -> Objects.equals(stockProduct.getPantry().getPantryId(), pantryId))
                .map(stockProductDTOMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    public void save(StockProductDTO pantryStockProductToBeSaved) {
        StockProduct stockProduct = stockProductDTOMapper.convertFromDTO(pantryStockProductToBeSaved);
        stockProductRepository.save(stockProduct);
    }
}
