package nl.miwgroningen.ch10.topshelf.mapper;
import nl.miwgroningen.ch10.topshelf.dto.ProductDefinitionDTO;
import nl.miwgroningen.ch10.topshelf.model.ProductDefinition;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * @author Inge Dikland
 *
 */
@Service
public class ProductDefinitionDTOMapper implements Function<ProductDefinition, ProductDefinitionDTO>
{
    @Override
    public ProductDefinitionDTO apply(ProductDefinition productDefinition) {
        return new ProductDefinitionDTO(
                productDefinition.getName());
    }
}
