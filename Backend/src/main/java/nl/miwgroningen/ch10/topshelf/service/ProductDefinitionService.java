package nl.miwgroningen.ch10.topshelf.service;

import nl.miwgroningen.ch10.topshelf.model.ProductDefinition;
import nl.miwgroningen.ch10.topshelf.repository.ProductDefinitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Robbin Drent <r.v.drent@st.hanze.nl>
 * <p>
 * Controls the productDefinitionRepository
 */

@Service
public class ProductDefinitionService {

    private final ProductDefinitionRepository productDefinitionRepository;

    @Autowired
    public ProductDefinitionService(ProductDefinitionRepository productDefinitionRepository) {
        this.productDefinitionRepository = productDefinitionRepository;
    }

    public ProductDefinition addProduct(ProductDefinition productDefinition) {
        return productDefinitionRepository.save(productDefinition);
    }

    public List<ProductDefinition> findAllProducts() {
        return productDefinitionRepository.findAll();
    }
}
