package nl.miwgroningen.ch10.topshelf.controller;
import nl.miwgroningen.ch10.topshelf.model.*;
import nl.miwgroningen.ch10.topshelf.repository.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.util.*;

/**
 * @author Inge Dikland
 * Adds data to our DB
 */

@RestController
public class SeedController {

    private final PantryRepository pantryRepository;
    private final StockProductRepository stockProductRepository;
    private final ProductDefinitionRepository productDefinitionRepository;
    private final BasicStockProductRepository basicStockProductRepository;
    private final GroceryProductRepository groceryProductRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public SeedController(PantryRepository pantryRepository, StockProductRepository stockProductRepository,
                          ProductDefinitionRepository productDefinitionRepository,
                          BasicStockProductRepository basicStockProductRepository,
                          GroceryProductRepository groceryProductRepository,
                          PasswordEncoder passwordEncoder,
                          UserRepository userRepository) {
        this.pantryRepository = pantryRepository;
        this.stockProductRepository = stockProductRepository;
        this.productDefinitionRepository = productDefinitionRepository;
        this.basicStockProductRepository = basicStockProductRepository;
        this.groceryProductRepository = groceryProductRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @GetMapping("/seed")
    protected String seedDatabase() {
        Pantry pantry1 = new Pantry();
        pantry1.setName("Vliegtuigbar");

        Pantry pantry2 = new Pantry();
        pantry2.setName("The Jockey's");

        Pantry pantry3 = new Pantry();
        pantry3.setName("Muziekband, de triangel");

        Pantry pantry4 = new Pantry();
        pantry4.setName("Veganbar Leafs");

        pantryRepository.save(pantry1);
        pantryRepository.save(pantry2);
        pantryRepository.save(pantry3);
        pantryRepository.save(pantry4);

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

        ProductDefinition veganCheese = new ProductDefinition();
        veganCheese.setName("Vegan Cheese");

        ProductDefinition almondMilk = new ProductDefinition();
        almondMilk.setName("Almond Milk");

        ProductDefinition tofu = new ProductDefinition();
        tofu.setName("Tofu");

        ProductDefinition seitan = new ProductDefinition();
        seitan.setName("Seitan");

        ProductDefinition veganYogurt = new ProductDefinition();
        veganYogurt.setName("Vegan Yogurt");

        ProductDefinition tempeh = new ProductDefinition();
        tempeh.setName("Tempeh");

        ProductDefinition veganProteinPowder = new ProductDefinition();
        veganProteinPowder.setName("Vegan Protein Powder");

        ProductDefinition veganButter = new ProductDefinition();
        veganButter.setName("Vegan Butter");

        ProductDefinition nutritionalYeast = new ProductDefinition();
        nutritionalYeast.setName("Nutritional Yeast");

        ProductDefinition veggieBurger = new ProductDefinition();
        veggieBurger.setName("Veggie Burger");


        List<ProductDefinition> productDefinitions = new ArrayList<>();

        productDefinitions.add(veganCheese);
        productDefinitions.add(almondMilk);
        productDefinitions.add(tofu);
        productDefinitions.add(seitan);
        productDefinitions.add(veganYogurt);
        productDefinitions.add(tempeh);
        productDefinitions.add(veganProteinPowder);
        productDefinitions.add(veganButter);
        productDefinitions.add(nutritionalYeast);
        productDefinitions.add(veggieBurger);

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

        List<StockProduct> stockProducts = new ArrayList<>();

// Vegan Cheese
        StockProduct veganCheese1 = new StockProduct();
        veganCheese1.setProductDefinition(veganCheese);
        veganCheese1.setExpirationDate(LocalDate.of(2022, 11, 15));
        veganCheese1.setPantry(pantry4);
        stockProducts.add(veganCheese1);

// Almond Milk
        StockProduct almondMilk1 = new StockProduct();
        almondMilk1.setProductDefinition(almondMilk);
        almondMilk1.setExpirationDate(LocalDate.of(2022, 8, 21));
        almondMilk1.setPantry(pantry4);
        stockProducts.add(almondMilk1);

// Tofu
        StockProduct tofu1 = new StockProduct();
        tofu1.setProductDefinition(tofu);
        tofu1.setExpirationDate(LocalDate.of(2022, 10, 5));
        tofu1.setPantry(pantry4);
        stockProducts.add(tofu1);

// Seitan
        StockProduct seitan1 = new StockProduct();
        seitan1.setProductDefinition(seitan);
        seitan1.setExpirationDate(LocalDate.of(2022, 9, 13));
        seitan1.setPantry(pantry4);
        stockProducts.add(seitan1);

// Vegan Yogurt
        StockProduct veganYogurt1 = new StockProduct();
        veganYogurt1.setProductDefinition(veganYogurt);
        veganYogurt1.setExpirationDate(LocalDate.of(2022, 12, 28));
        veganYogurt1.setPantry(pantry4);
        stockProducts.add(veganYogurt1);

// Tempeh
        StockProduct tempeh1 = new StockProduct();
        tempeh1.setProductDefinition(tempeh);
        tempeh1.setExpirationDate(LocalDate.of(2022, 6, 17));
        tempeh1.setPantry(pantry4);
        stockProducts.add(tempeh1);

// Vegan Protein Powder
        StockProduct proteinPowder1 = new StockProduct();
        proteinPowder1.setProductDefinition(veganProteinPowder);
        proteinPowder1.setExpirationDate(LocalDate.of(2023, 1, 9));
        proteinPowder1.setPantry(pantry4);
        stockProducts.add(proteinPowder1);

// Vegan Butter
        StockProduct veganButter1 = new StockProduct();
        veganButter1.setProductDefinition(veganButter);
        veganButter1.setExpirationDate(LocalDate.of(2022, 7, 31));
        veganButter1.setPantry(pantry4);
        stockProducts.add(veganButter1);

// Nutritional Yeast
        StockProduct nutritionalYeast1 = new StockProduct();
        nutritionalYeast1.setProductDefinition(nutritionalYeast);
        nutritionalYeast1.setExpirationDate(LocalDate.of(2022, 11, 3));
        nutritionalYeast1.setPantry(pantry4);
        stockProducts.add(nutritionalYeast1);

// Veggie Burger
        StockProduct veggieBurger1 = new StockProduct();
        veggieBurger1.setProductDefinition(veggieBurger);
        veggieBurger1.setExpirationDate(LocalDate.of(2022, 8, 7));
        veggieBurger1.setPantry(pantry4);
        stockProducts.add(veggieBurger1);

        List<StockProduct> stock1 = new ArrayList<>();

        stock1.add(rijst1);
        stock1.add(spaghetti1);
        stock1.add(oudeKaas1);
        stock1.add(parmezaan1);
        stock1.add(pesto1);
        stockProductRepository.saveAll(stock1);
        stockProductRepository.saveAll(stockProducts);


        BasicStockProduct veggieBurger2 = new BasicStockProduct();
        veggieBurger2.setPantry(pantry4);
        veggieBurger2.setAmount(4);
        veggieBurger2.setProductDefinition(veggieBurger);
        basicStockProductRepository.save(veggieBurger2);

        BasicStockProduct veganCheese2 = new BasicStockProduct();
        veganCheese2.setPantry(pantry4);
        veganCheese2.setAmount(2);
        veganCheese2.setProductDefinition(veganCheese);
        basicStockProductRepository.save(veganCheese2);

        BasicStockProduct almondMilk2 = new BasicStockProduct();
        almondMilk2.setPantry(pantry4);
        almondMilk2.setAmount(1);
        almondMilk2.setProductDefinition(almondMilk);
        basicStockProductRepository.save(almondMilk2);

        BasicStockProduct tofu2 = new BasicStockProduct();
        tofu2.setPantry(pantry4);
        tofu2.setAmount(3);
        tofu2.setProductDefinition(tofu);
        basicStockProductRepository.save(tofu2);

        BasicStockProduct seitan2 = new BasicStockProduct();
        seitan2.setPantry(pantry4);
        seitan2.setAmount(2);
        seitan2.setProductDefinition(seitan);
        basicStockProductRepository.save(seitan2);

        BasicStockProduct veganYogurt2 = new BasicStockProduct();
        veganYogurt2.setPantry(pantry4);
        veganYogurt2.setAmount(1);
        veganYogurt2.setProductDefinition(veganYogurt);
        basicStockProductRepository.save(veganYogurt2);


        ArrayList<Pantry> pantryArrayList = new ArrayList<>();
        pantryArrayList.add(pantry4);

        User user1 = userRepository.findByUsername("admin").get();
        User user2 = new User();
        user2.setUsername("Klaas");
        user2.setEmail("klaas@mit.nl");
        user2.setPassword(passwordEncoder.encode("passwordKlaas"));
        user2.setRole(Role.USER);
        user2.setUserPantries(pantryArrayList);
        userRepository.save(user2);

        User user3 = new User();
        user3.setUsername("Anna");
        user3.setEmail("anna@gmail.com");
        user3.setPassword(passwordEncoder.encode("anna123"));
        user3.setRole(Role.USER);
        user3.setUserPantries(pantryArrayList);
        userRepository.save(user3);

        User user4 = new User();
        user4.setUsername("John");
        user4.setEmail("john@yahoo.com");
        user4.setPassword(passwordEncoder.encode("john456"));
        user4.setRole(Role.USER);
        user4.setUserPantries(pantryArrayList);
        userRepository.save(user4);

        User user5 = new User();
        user5.setUsername("Lena");
        user5.setEmail("lena@hotmail.com");
        user5.setPassword(passwordEncoder.encode("lena789"));
        user5.setRole(Role.USER);
        user5.setUserPantries(pantryArrayList);
        userRepository.save(user5);
        //pantry 1
        ArrayList<User> adminPantry1 = new ArrayList<>();
        ArrayList<User> usersPantry1 = new ArrayList<>();
        adminPantry1.add(user2);
        adminPantry1.add(user3);
        usersPantry1.add(user1);
        usersPantry1.add(user2);
        usersPantry1.add(user3);

        pantry1.setUsers(usersPantry1);
        pantry1.setAdmins(adminPantry1);
        pantryRepository.save(pantry1);

        user1.getUserPantries().add(pantry1);
        user2.getUserPantries().add(pantry1);
        user3.getUserPantries().add(pantry1);

        //



        // pantry 4
        ArrayList<User> admins = new ArrayList<>();
        ArrayList<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        admins.add(user1);
        pantry4.setAdmins(admins);
        pantry4.setUsers(users);
        pantryRepository.save(pantry4);

        user1.setUserPantries(pantryArrayList);
        user2.setUserPantries(pantryArrayList);
        user3.setUserPantries(pantryArrayList);
        user4.setUserPantries(pantryArrayList);
        user5.setUserPantries(pantryArrayList);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);


        return "redirect:/pantry/all";
    }
}
