package nl.miwgroningen.ch10.topshelf.controller;

import nl.miwgroningen.ch10.topshelf.model.Pantry;
import nl.miwgroningen.ch10.topshelf.model.ProductDefinition;
import nl.miwgroningen.ch10.topshelf.model.StockProduct;
import nl.miwgroningen.ch10.topshelf.repository.PantryRepository;
import nl.miwgroningen.ch10.topshelf.repository.ProductDefinitionRepository;
import nl.miwgroningen.ch10.topshelf.repository.StockProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.*;

/**
 * @author Inge Dikland
 * <p>
 * Adds data to our DB
 */

@RestController
public class SeedController {

    private final PantryRepository pantryRepository;
    private final StockProductRepository stockProductRepository;
    private final ProductDefinitionRepository productDefinitionRepository;

    public SeedController(PantryRepository pantryRepository, StockProductRepository stockProductRepository, ProductDefinitionRepository productDefinitionRepository) {
        this.pantryRepository = pantryRepository;
        this.stockProductRepository = stockProductRepository;
        this.productDefinitionRepository = productDefinitionRepository;
    }

    @GetMapping("/seed")
    protected String seedDatabase() {
        Pantry pantry1 = new Pantry();
        pantry1.setName("Vliegtuigbar");

        Pantry pantry2 = new Pantry();
        pantry2.setName("The Jockey's");

        Pantry pantry3 = new Pantry();
        pantry3.setName("Muziekband, de triangel");

        pantryRepository.save(pantry1);
        pantryRepository.save(pantry2);
        pantryRepository.save(pantry3);

        ProductDefinition rijst = new ProductDefinition();
        rijst.setName("Rijst");

        ProductDefinition spaghetti = new ProductDefinition();
        spaghetti.setName("Spaghetti");

        ProductDefinition oudeKaas = new ProductDefinition();
        oudeKaas.setName("Oude kaas");

        ProductDefinition parmezaan = new ProductDefinition();
        parmezaan.setName("Parmezaan");

        ProductDefinition pesto = new ProductDefinition();
        pesto.setName("Pesto");

        List<ProductDefinition> productDefinitions = new ArrayList<>();
        productDefinitions.add(rijst);
        productDefinitions.add(spaghetti);
        productDefinitions.add(oudeKaas);
        productDefinitions.add(parmezaan);
        productDefinitions.add(pesto);
        productDefinitionRepository.saveAll(productDefinitions);

        StockProduct rijst1 = new StockProduct();
        rijst1.setProductDefinition(rijst);
        rijst1.setExpirationDate(LocalDate.of(2022, 4, 3));
        rijst1.setPantry(pantry1);

        StockProduct spaghetti1 = new StockProduct();
        spaghetti1.setProductDefinition(spaghetti);
        spaghetti1.setExpirationDate(LocalDate.of(2026, 7, 6));
        spaghetti1.setPantry(pantry1);

        StockProduct oudeKaas1 = new StockProduct();
        oudeKaas1.setProductDefinition(oudeKaas);
        oudeKaas1.setExpirationDate(LocalDate.of(2022, 12, 6));
        oudeKaas1.setPantry(pantry1);

        StockProduct pesto1 = new StockProduct();
        pesto1.setProductDefinition(pesto);
        pesto1.setExpirationDate(LocalDate.of(2023, 11, 4));
        pesto1.setPantry(pantry2);

        StockProduct parmezaan1 = new StockProduct();
        parmezaan1.setProductDefinition(parmezaan);
        parmezaan1.setExpirationDate(LocalDate.of(2025, 6, 2));
        parmezaan1.setPantry(pantry2);

        List<StockProduct> stock1 = new ArrayList<>();
        stock1.add(rijst1);
        stock1.add(spaghetti1);
        stock1.add(oudeKaas1);
        stock1.add(parmezaan1);
        stock1.add(pesto1);
        stockProductRepository.saveAll(stock1);

        return "redirect:/pantry/all";
    }
}
