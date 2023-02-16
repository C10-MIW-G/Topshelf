package nl.miwgroningen.ch10.topshelf.service;

import nl.miwgroningen.ch10.topshelf.dto.BasicStockProductDTO;
import nl.miwgroningen.ch10.topshelf.exception.BasicStockProductNotFoundException;
import nl.miwgroningen.ch10.topshelf.mapper.BasicStockProductDTOMapper;
import nl.miwgroningen.ch10.topshelf.model.BasicStockProduct;
import nl.miwgroningen.ch10.topshelf.model.Pantry;
import nl.miwgroningen.ch10.topshelf.repository.BasicStockProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jack Wieringa <j.w.wieringa@st.hanze.nl>
 * <p>
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

    public BasicStockProductDTO findBasicStockProductByBasicStockProductId(Long basicStockProductId) {
        return basicStockProductRepository.findBasicStockProductByBasicStockProductId(basicStockProductId)
                .map(basicStockProductDTOMapper)
                .orElseThrow(() -> new BasicStockProductNotFoundException("BasicStockProduct with id: " +
                        basicStockProductId + " was not found!"));
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
}
