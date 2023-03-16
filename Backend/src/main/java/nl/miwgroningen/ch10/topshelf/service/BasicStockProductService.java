package nl.miwgroningen.ch10.topshelf.service;

import nl.miwgroningen.ch10.topshelf.dto.BasicStockProductDTO;
import nl.miwgroningen.ch10.topshelf.mapper.BasicStockProductDTOMapper;
import nl.miwgroningen.ch10.topshelf.model.BasicStockProduct;
import nl.miwgroningen.ch10.topshelf.model.Pantry;
import nl.miwgroningen.ch10.topshelf.model.ProductDefinition;
import nl.miwgroningen.ch10.topshelf.repository.BasicStockProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Jack Wieringa <j.w.wieringa@st.hanze.nl>
 * Controls the BasicStockProduct repository
 */

@Service
public class BasicStockProductService {

    private final BasicStockProductRepository basicStockProductRepository;
    private final BasicStockProductDTOMapper basicStockProductDTOMapper;

    @Autowired
    public BasicStockProductService(BasicStockProductRepository basicStockProductRepository,
                                    BasicStockProductDTOMapper basicStockProductDTOMapper) {
        this.basicStockProductRepository = basicStockProductRepository;
        this.basicStockProductDTOMapper = basicStockProductDTOMapper;
    }

    public List<BasicStockProductDTO> findBasicStockProductByPantry(Pantry pantry) {
        return basicStockProductRepository.findBasicStockProductsByPantry(pantry)
                .stream()
                .map(basicStockProductDTOMapper)
                .toList();
    }

    public void save(BasicStockProductDTO basicStockProductDTO) {
        BasicStockProduct basicStockProduct = basicStockProductDTOMapper.convertFromDTO(basicStockProductDTO);
        basicStockProductRepository.save(basicStockProduct);
    }

    public int findBasicStockAmountByName(Pantry pantry, ProductDefinition productDefinition){
         Optional<BasicStockProduct> basicStockProduct =
                 basicStockProductRepository.findBasicStockProductByPantryAndProductDefinition
                         (pantry, productDefinition);
        return basicStockProduct.map(BasicStockProduct::getAmount).orElse(0);
    }

    public void deleteBasicStockProduct(Long basicStockProductId) {
        basicStockProductRepository.deleteById(basicStockProductId);
    }
}
