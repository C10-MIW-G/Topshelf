package nl.miwgroningen.ch10.topshelf.service;

import nl.miwgroningen.ch10.topshelf.mapper.StockProductDTOMapper;
import nl.miwgroningen.ch10.topshelf.repository.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StockProductServiceTest {
    private StockProductService stockProductService;

    @Mock
    private StockProductRepository stockProductRepository;
    @Mock
    private  StockProductDTOMapper stockProductDTOMapper;
    @Mock
    private  PantryService pantryService;
    @Mock
    private  ProductDefinitionService productDefinitionService;
    @Mock
    private BasicStockProductService basicStockProductService;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        stockProductService = new StockProductService(stockProductRepository, stockProductDTOMapper, pantryService, productDefinitionService, basicStockProductService );
    }

    @Test
    void stockStatusTestWhenStockIsLow(){
        int count = 3;
        int amount = 4;

        Assertions.assertTrue(stockProductService.settingStockStatus(count, amount));
    }
    @Test
    void stockStatusTestWhenStockIsEnough(){
        int count = 5;
        int amount = 4;

        Assertions.assertFalse(stockProductService.settingStockStatus(count, amount));
    }
    @Test
    void stockStatusTestWhenStockIsEqualToBasicStock(){
        int count = 4;
        int amount = 4;

        Assertions.assertFalse(stockProductService.settingStockStatus(count, amount));
    }
}