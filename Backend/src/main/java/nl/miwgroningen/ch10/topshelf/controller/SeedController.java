package nl.miwgroningen.ch10.topshelf.controller;

import nl.miwgroningen.ch10.topshelf.model.Pantry;
import nl.miwgroningen.ch10.topshelf.model.ProductDefinition;
import nl.miwgroningen.ch10.topshelf.repository.PantryRepository;
import nl.miwgroningen.ch10.topshelf.repository.ProductDefinitionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Inge Dikland
 * <p>
 * Adds data to our DB
 */

@Controller
public class SeedController {

    private final PantryRepository pantryRepository;

    private final ProductDefinitionRepository productDefinitionRepository;

    public SeedController(PantryRepository pantryRepository, ProductDefinitionRepository productDefinitionRepository) {
        this.pantryRepository = pantryRepository;
        this.productDefinitionRepository = productDefinitionRepository;
    }

    @GetMapping("/seed")
    protected String seedDatabase() {
        Pantry pantry1 = new Pantry();
        pantry1.setName("Vliegtuigbar");
        pantryRepository.save(pantry1);

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

        Set<ProductDefinition> stock1 = new HashSet<>();
        Collections.addAll(stock1, rijst, spaghetti, oudeKaas, parmezaan, pesto);
        productDefinitionRepository.saveAll(stock1);

        pantry1.setStock(stock1);
        pantryRepository.save(pantry1);

        return "redirect:/pantry/all";
    }
}
