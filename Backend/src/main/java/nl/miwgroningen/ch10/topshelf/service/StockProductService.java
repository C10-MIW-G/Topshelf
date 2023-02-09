package nl.miwgroningen.ch10.topshelf.service;
import nl.miwgroningen.ch10.topshelf.dto.StockProductDTO;
import nl.miwgroningen.ch10.topshelf.exception.StockProductNotFoundException;
import nl.miwgroningen.ch10.topshelf.mapper.StockProductDTOMapper;
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
    public StockProductService(StockProductRepository stockProductRepository, StockProductDTOMapper stockProductDTOMapper) {
        this.stockProductRepository = stockProductRepository;
        this.stockProductDTOMapper = stockProductDTOMapper;
    }

    public List<StockProductDTO> findAllStockProducts() {
        return stockProductRepository.findAll()
                .stream()
                .map(stockProductDTOMapper)
                .collect(Collectors.toList());
    }

    public StockProductDTO findStockProductByStockProductId(Long stockProductId) {
        return stockProductRepository.findStockProductByStockProductId(stockProductId)
                .map(stockProductDTOMapper)
                .orElseThrow(() -> new StockProductNotFoundException("StockProduct with id: " + stockProductId + " was not found!"));
    }

    public List<StockProductDTO> findStockProductByPantryId(Long pantryId) {
        return stockProductRepository
                .findAll()
                .stream()
                .filter(stockProduct -> Objects.equals(stockProduct.getPantry().getPantryId(), pantryId))
                .map(stockProductDTOMapper)
                .collect(Collectors.toList());
    }
}
