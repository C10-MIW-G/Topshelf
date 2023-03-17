package nl.miwgroningen.ch10.topshelf.service;

import nl.miwgroningen.ch10.topshelf.dto.BasicStockProductDTO;
import nl.miwgroningen.ch10.topshelf.exception.ProductAlreadyAddedException;
import nl.miwgroningen.ch10.topshelf.exception.ProductWithNameAlreadyExistsException;
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
        Optional<BasicStockProduct> existingBasicStockProduct = findProductWithSameNameInPantry(basicStockProduct);

        if(existingBasicStockProduct.isPresent()) {
            throw new ProductAlreadyAddedException("BasicStockProduct is already added");
        } else {
            basicStockProductRepository.save(basicStockProduct);
        }
    }

    public void edit(BasicStockProductDTO basicStockProductToBeEdited) {
        BasicStockProduct basicStockProduct = basicStockProductDTOMapper.convertFromDTO(basicStockProductToBeEdited);
        Optional<BasicStockProduct> existingBasicStockProduct = findSameProductInPantry(basicStockProduct);
        Optional<BasicStockProduct> sameNameBasicStockProduct = findProductWithSameNameInPantry(basicStockProduct);

        if (basicStockProductToBeEdited.name().equals
                (existingBasicStockProduct.get().getProductDefinition().getName())) {
            basicStockProductRepository.save(basicStockProduct);
        } else if (sameNameBasicStockProduct.isPresent()) {
            throw new ProductWithNameAlreadyExistsException("Product with this name already exists");
        } else {
            basicStockProductRepository.save(basicStockProduct);
        }
    }

    public Optional<BasicStockProduct> findSameProductInPantry (BasicStockProduct basicStockProduct) {
        Optional<BasicStockProduct> existingBasicStockProduct =
                basicStockProductRepository.findBasicStockProductByPantryAndBasicStockProductId
                        (basicStockProduct.getPantry(), basicStockProduct.getBasicStockProductId());
        return existingBasicStockProduct;
    }

    public Optional<BasicStockProduct> findProductWithSameNameInPantry (BasicStockProduct basicStockProduct) {
        Optional<BasicStockProduct> sameNameBasicStockProduct =
                basicStockProductRepository.findBasicStockProductByPantryAndProductDefinition
                        (basicStockProduct.getPantry(), basicStockProduct.getProductDefinition());
        return sameNameBasicStockProduct;
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
