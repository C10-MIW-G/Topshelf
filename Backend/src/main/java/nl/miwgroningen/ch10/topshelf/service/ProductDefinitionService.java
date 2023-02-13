package nl.miwgroningen.ch10.topshelf.service;

import nl.miwgroningen.ch10.topshelf.dto.ProductDefinitionDTO;
import nl.miwgroningen.ch10.topshelf.mapper.ProductDefinitionDTOMapper;
import nl.miwgroningen.ch10.topshelf.model.ProductDefinition;
import nl.miwgroningen.ch10.topshelf.repository.ProductDefinitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Robbin Drent <r.v.drent@st.hanze.nl>
 * <p>
 * Controls the productDefinitionRepository
 */

@Service
public class ProductDefinitionService {

    private final ProductDefinitionRepository productDefinitionRepository;
    private final ProductDefinitionDTOMapper productDefinitionDTOMapper;

    @Autowired
    public ProductDefinitionService(ProductDefinitionRepository productDefinitionRepository, ProductDefinitionDTOMapper productDefinitionDTOMapper) {
        this.productDefinitionRepository = productDefinitionRepository;
        this.productDefinitionDTOMapper = productDefinitionDTOMapper;
    }

    public ProductDefinition addProduct(ProductDefinition productDefinition) {
        return productDefinitionRepository.save(productDefinition);
    }

    public List<ProductDefinitionDTO> findAllProducts() {
        return productDefinitionRepository.findAll()
                .stream()
                .map(productDefinitionDTOMapper)
                .collect(Collectors.toList());
    }
}
