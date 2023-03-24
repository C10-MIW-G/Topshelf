package nl.miwgroningen.ch10.topshelf.controller;
import nl.miwgroningen.ch10.topshelf.model.*;
import nl.miwgroningen.ch10.topshelf.repository.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.plaf.basic.BasicEditorPaneUI;
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


        Pantry vlinder = new Pantry();
        vlinder.setName("Department Butterfly");

        Pantry survival = new Pantry();
        survival.setName("The Swinf Over");

        Pantry veganbar = new Pantry();
        veganbar.setName("Veganbar Leafs");

        Pantry gezin = new Pantry();
        gezin.setName("Beta Delta Pi");

        Pantry vliegtuigbar = new Pantry();
        vliegtuigbar.setName("IlionX");

        pantryRepository.save(vliegtuigbar);
        pantryRepository.save(vlinder);
        pantryRepository.save(survival);
        pantryRepository.save(veganbar);
        pantryRepository.save(gezin);

        ProductDefinition rijst = new ProductDefinition();
        rijst.setName("Rice 500gr");

        ProductDefinition spaghetti = new ProductDefinition();
        spaghetti.setName("Spaghetti 500gr");

        ProductDefinition oudeKaas = new ProductDefinition();
        oudeKaas.setName("Old cheese slices 150gr");

        ProductDefinition parmezaan = new ProductDefinition();
        parmezaan.setName("Parmesan 125gr");

        ProductDefinition pesto = new ProductDefinition();
        pesto.setName("Pesto jar 100gr");

        ProductDefinition veganCheese = new ProductDefinition();
        veganCheese.setName("Vegan Cheese 250gr");

        ProductDefinition almondMilk = new ProductDefinition();
        almondMilk.setName("Almond Milk bottle 1L");

        ProductDefinition tofu = new ProductDefinition();
        tofu.setName("Tofu 500gr");

        ProductDefinition seitan = new ProductDefinition();
        seitan.setName("Seitan 100 gr");

        ProductDefinition veganYogurt = new ProductDefinition();
        veganYogurt.setName("Vegan Yogurt 1L");

        ProductDefinition tempeh = new ProductDefinition();
        tempeh.setName("Tempeh 400gr");

        ProductDefinition veganProteinPowder = new ProductDefinition();
        veganProteinPowder.setName("Vegan Protein Powder 500gr");

        ProductDefinition veganButter = new ProductDefinition();
        veganButter.setName("Vegan Butter 250gr");

        ProductDefinition nutritionalYeast = new ProductDefinition();
        nutritionalYeast.setName("Nutritional Yeast 1kg");

        ProductDefinition veggieBurger = new ProductDefinition();
        veggieBurger.setName("Veggie Burger 4pack");


        List<ProductDefinition> productDefinitions = new ArrayList<>();

        ProductDefinition pasta = new ProductDefinition();
        pasta.setName("Pasta 500gr");
        productDefinitions.add(pasta);

        ProductDefinition melk = new ProductDefinition();
        melk.setName("Milk 1L");
        productDefinitions.add(melk);

        ProductDefinition yoghurt = new ProductDefinition();
        yoghurt.setName("Yoghurt 500gr");
        productDefinitions.add(yoghurt);

        ProductDefinition kaas = new ProductDefinition();
        kaas.setName("Cheese 250gr");
        productDefinitions.add(kaas);

        ProductDefinition brood = new ProductDefinition();
        brood.setName("Bread loaf");
        productDefinitions.add(brood);

        ProductDefinition appels = new ProductDefinition();
        appels.setName("Apples 1kg");
        productDefinitions.add(appels);

        ProductDefinition bananen = new ProductDefinition();
        bananen.setName("Bananas 1kg");
        productDefinitions.add(bananen);

        ProductDefinition tomaten = new ProductDefinition();
        tomaten.setName("Tomatoes 500gr");
        productDefinitions.add(tomaten);

        ProductDefinition kipfilet = new ProductDefinition();
        kipfilet.setName("Chicken breast 500gr");
        productDefinitions.add(kipfilet);

        ProductDefinition gehakt = new ProductDefinition();
        gehakt.setName("Minced meat 500gr");
        productDefinitions.add(gehakt);

        ProductDefinition limonade = new ProductDefinition();
        limonade.setName("Lemonade 1L");
        productDefinitions.add(limonade);
   ProductDefinition bitterbal = new ProductDefinition();
        bitterbal.setName("Bitterballs 10pc");
        productDefinitions.add(bitterbal);
   ProductDefinition beer = new ProductDefinition();
        beer.setName("Beer 33cl");
        productDefinitions.add(beer);
        ProductDefinition fristi = new ProductDefinition();
        fristi.setName("Fristi 200cl");
        productDefinitions.add(fristi);

        Collections.addAll(productDefinitions,
                veganButter,
                veganCheese,
                almondMilk,
                tofu,
                seitan,
                veganYogurt,
                tempeh,
                veganProteinPowder,
                nutritionalYeast,
                veggieBurger,
                rijst,
                spaghetti,
                oudeKaas,
                parmezaan,
                pesto,
                gehakt,
                limonade,
                appels,
                bananen,
                melk,
                yoghurt,
                kaas,
                tomaten,
                kipfilet,
                brood,
                bitterbal,
                beer,
                fristi);

        productDefinitionRepository.saveAll(productDefinitions);

      stockProductRepository.save(
        StockProduct.builder()
                .productDefinition(pasta)
                .expirationDate(LocalDate.of(2023,3,24))
                .pantry(gezin)
                .build());
                stockProductRepository.save(
        StockProduct.builder()
                .productDefinition(tomaten)
                .expirationDate(LocalDate.of(2023,3,25))
                .pantry(gezin)
                .build());
                stockProductRepository.save(
                StockProduct.builder()
                .productDefinition(melk)
                .expirationDate(LocalDate.of(2024,3,26))
                .pantry(gezin)
                .build());
                stockProductRepository.save(
                StockProduct.builder()
                .productDefinition(limonade)
                .expirationDate(LocalDate.of(2023,9,26))
                .pantry(gezin)
                .build());

                basicStockProductRepository.save(
                        BasicStockProduct.builder()
                                .amount(2).productDefinition(melk)
                                .pantry(gezin)
                                .build());
                basicStockProductRepository.save(
                        BasicStockProduct.builder()
                                .amount(2).productDefinition(pasta)
                                .pantry(gezin)
                                .build());





        StockProduct rijst1 = new StockProduct();
        rijst1.setProductDefinition(rijst);
        rijst1.setExpirationDate(LocalDate.of(2022, 4, 3));
        rijst1.setPantry(vliegtuigbar);

        StockProduct spaghetti1 = new StockProduct();
        spaghetti1.setProductDefinition(spaghetti);
        spaghetti1.setExpirationDate(LocalDate.of(2026, 7, 6));
        spaghetti1.setPantry(vliegtuigbar);

        StockProduct oudeKaas1 = new StockProduct();
        oudeKaas1.setProductDefinition(oudeKaas);
        oudeKaas1.setExpirationDate(LocalDate.of(2022, 12, 6));
        oudeKaas1.setPantry(vliegtuigbar);

        StockProduct pesto1 = new StockProduct();
        pesto1.setProductDefinition(pesto);
        pesto1.setExpirationDate(LocalDate.of(2023, 11, 4));
        pesto1.setPantry(vlinder);

        StockProduct parmezaan1 = new StockProduct();
        parmezaan1.setProductDefinition(parmezaan);
        parmezaan1.setExpirationDate(LocalDate.of(2025, 6, 2));
        parmezaan1.setPantry(vlinder);

        List<StockProduct> stockProducts = new ArrayList<>();

// Vegan Cheese
        StockProduct veganCheese1 = new StockProduct();
        veganCheese1.setProductDefinition(veganCheese);
        veganCheese1.setExpirationDate(LocalDate.of(2022, 11, 15));
        veganCheese1.setPantry(veganbar);
        stockProducts.add(veganCheese1);

// Almond Milk
        StockProduct almondMilk1 = new StockProduct();
        almondMilk1.setProductDefinition(almondMilk);
        almondMilk1.setExpirationDate(LocalDate.of(2022, 8, 21));
        almondMilk1.setPantry(veganbar);
        stockProducts.add(almondMilk1);

// Tofu
        StockProduct tofu1 = new StockProduct();
        tofu1.setProductDefinition(tofu);
        tofu1.setExpirationDate(LocalDate.of(2022, 10, 5));
        tofu1.setPantry(veganbar);
        stockProducts.add(tofu1);

// Seitan
        StockProduct seitan1 = new StockProduct();
        seitan1.setProductDefinition(seitan);
        seitan1.setExpirationDate(LocalDate.of(2022, 9, 13));
        seitan1.setPantry(veganbar);
        stockProducts.add(seitan1);

// Vegan Yogurt
        StockProduct veganYogurt1 = new StockProduct();
        veganYogurt1.setProductDefinition(veganYogurt);
        veganYogurt1.setExpirationDate(LocalDate.of(2022, 12, 28));
        veganYogurt1.setPantry(veganbar);
        stockProducts.add(veganYogurt1);

// Tempeh
        StockProduct tempeh1 = new StockProduct();
        tempeh1.setProductDefinition(tempeh);
        tempeh1.setExpirationDate(LocalDate.of(2022, 6, 17));
        tempeh1.setPantry(veganbar);
        stockProducts.add(tempeh1);

// Vegan Protein Powder
        StockProduct proteinPowder1 = new StockProduct();
        proteinPowder1.setProductDefinition(veganProteinPowder);
        proteinPowder1.setExpirationDate(LocalDate.of(2023, 1, 9));
        proteinPowder1.setPantry(veganbar);
        stockProducts.add(proteinPowder1);

// Vegan Butter
        StockProduct veganButter1 = new StockProduct();
        veganButter1.setProductDefinition(veganButter);
        veganButter1.setExpirationDate(LocalDate.of(2022, 7, 31));
        veganButter1.setPantry(veganbar);
        stockProducts.add(veganButter1);

// Nutritional Yeast
        StockProduct nutritionalYeast1 = new StockProduct();
        nutritionalYeast1.setProductDefinition(nutritionalYeast);
        nutritionalYeast1.setExpirationDate(LocalDate.of(2022, 11, 3));
        nutritionalYeast1.setPantry(veganbar);
        stockProducts.add(nutritionalYeast1);

// Veggie Burger
        StockProduct veggieBurger1 = new StockProduct();
        veggieBurger1.setProductDefinition(veggieBurger);
        veggieBurger1.setExpirationDate(LocalDate.of(2022, 8, 7));
        veggieBurger1.setPantry(veganbar);
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
        veggieBurger2.setPantry(veganbar);
        veggieBurger2.setAmount(4);
        veggieBurger2.setProductDefinition(veggieBurger);
        basicStockProductRepository.save(veggieBurger2);

        BasicStockProduct veganCheese2 = new BasicStockProduct();
        veganCheese2.setPantry(veganbar);
        veganCheese2.setAmount(2);
        veganCheese2.setProductDefinition(veganCheese);
        basicStockProductRepository.save(veganCheese2);

        BasicStockProduct almondMilk2 = new BasicStockProduct();
        almondMilk2.setPantry(veganbar);
        almondMilk2.setAmount(1);
        almondMilk2.setProductDefinition(almondMilk);
        basicStockProductRepository.save(almondMilk2);

        BasicStockProduct tofu2 = new BasicStockProduct();
        tofu2.setPantry(veganbar);
        tofu2.setAmount(3);
        tofu2.setProductDefinition(tofu);
        basicStockProductRepository.save(tofu2);

        BasicStockProduct seitan2 = new BasicStockProduct();
        seitan2.setPantry(veganbar);
        seitan2.setAmount(2);
        seitan2.setProductDefinition(seitan);
        basicStockProductRepository.save(seitan2);

        BasicStockProduct veganYogurt2 = new BasicStockProduct();
        veganYogurt2.setPantry(veganbar);
        veganYogurt2.setAmount(1);
        veganYogurt2.setProductDefinition(veganYogurt);
        basicStockProductRepository.save(veganYogurt2);


        ArrayList<Pantry> pantryArrayList = new ArrayList<>();
        pantryArrayList.add(veganbar);

        User admin = userRepository.findByUsername("Ingmar").get();
        User klaas = new User();
        klaas.setUsername("Klaas");
        klaas.setEmail("klaas@mit.nl");
        klaas.setPassword(passwordEncoder.encode("klaas123"));
        klaas.setRole(Role.USER);
        klaas.setUserPantries(pantryArrayList);
        userRepository.save(klaas);

        User bushra = new User();
        bushra.setUsername("Bushra van der Wal");
        bushra.setEmail("bvdwal@mit.com");
        bushra.setPassword(passwordEncoder.encode("bushra123"));
        bushra.setRole(Role.USER);
        bushra.setUserPantries(pantryArrayList);
        userRepository.save(bushra);

        User erik = new User();
        erik.setUsername("Erik");
        erik.setEmail("erik@mit.com");
        erik.setPassword(passwordEncoder.encode("erik123"));
        erik.setRole(Role.USER);
        erik.setUserPantries(pantryArrayList);
        userRepository.save(erik);

        User lisa = new User();
        lisa.setUsername("Lisa");
        lisa.setEmail("lisa@mit.com");
        lisa.setPassword(passwordEncoder.encode("lisa123"));
        lisa.setRole(Role.USER);
        lisa.setUserPantries(pantryArrayList);
        userRepository.save(lisa);

        User tom = new User();
        tom.setUsername("Tom Waterval");
        tom.setEmail("tom@mit.com");
        tom.setPassword(passwordEncoder.encode("tom123"));
        tom.setRole(Role.USER);
        tom.setUserPantries(pantryArrayList);
        userRepository.save(lisa);


        //vliegtuigbar
        ArrayList<User> adminPantry1 = new ArrayList<>();
        ArrayList<User> usersPantry1 = new ArrayList<>();
        adminPantry1.add(klaas);
        adminPantry1.add(bushra);
        usersPantry1.add(admin);
        usersPantry1.add(klaas);
        usersPantry1.add(bushra);

        vliegtuigbar.setUsers(usersPantry1);
        vliegtuigbar.setAdmins(adminPantry1);
        pantryRepository.save(vliegtuigbar);

        admin.getUserPantries().add(vliegtuigbar);
        klaas.getUserPantries().add(vliegtuigbar);
        bushra.getUserPantries().add(vliegtuigbar);



        //veganbar
        ArrayList<User> admins = new ArrayList<>();
        ArrayList<User> users = new ArrayList<>();
        users.add(admin);
        users.add(klaas);
        users.add(bushra);
        users.add(lisa);
        admins.add(admin);
        veganbar.setAdmins(admins);
        veganbar.setUsers(users);
        pantryRepository.save(veganbar);

        for (User user : users) {
            user.getUserPantries().add(veganbar);
            userRepository.save(user);

        }


        //survival
        ArrayList<Pantry> survivalList = new ArrayList<>();
        survivalList.add(survival);
        survival.setUsers(new ArrayList<>());
        ArrayList<User> adminSurvival = new ArrayList<>();
        ArrayList<User> userSurvival = new ArrayList<>();
        adminSurvival.add(bushra);
        adminSurvival.add(admin);
        userSurvival.add(bushra);
        userSurvival.add(erik);
        userSurvival.add(admin);
        userSurvival.add(lisa);


        for (User user : survival.getUsers()) {
            user.getUserPantries().add(survival);
            userRepository.save(user);
        }

        survival.setAdmins(adminSurvival);
        survival.setUsers(userSurvival);
        pantryRepository.save(survival);

        stockProductRepository.save(
                StockProduct.builder()
                        .productDefinition(beer)
                        .expirationDate(LocalDate.of(2024,3,24))
                        .pantry(survival)
                        .build());
        stockProductRepository.save(
                StockProduct.builder()
                        .productDefinition(fristi)
                        .expirationDate(LocalDate.of(2023,12,24))
                        .pantry(survival)
                        .build());
        stockProductRepository.save(
                StockProduct.builder()
                        .productDefinition(bitterbal)
                        .expirationDate(LocalDate.of(2023,7,12))
                        .pantry(survival)
                        .build());

        //gezin---------------------------------------------------------------
        ArrayList<User> adminGezin = new ArrayList<>();
        ArrayList<User> userGezin = new ArrayList<>();

        Collections.addAll(adminGezin, admin, tom);
        Collections.addAll(userGezin, lisa, admin, tom);

        for (User user : userGezin) {
            user.getUserPantries().add(gezin);
            userRepository.save(user);
        }
        gezin.setAdmins(adminGezin);
        gezin.setUsers(userGezin);

//        Collections.addAll(gezin.getStock(), veganButter1, rijst1, parmezaan1);

        pantryRepository.save(gezin);



        //woongroep vlinder

        vlinder.setUsers(new ArrayList<>());
        vlinder.setAdmins(new ArrayList<>());
        Collections.addAll(vlinder.getAdmins(), klaas, admin);
        Collections.addAll(vlinder.getUsers(), klaas, admin, lisa, tom);
        for (User user : vlinder.getUsers()) {
            user.getUserPantries().add(vlinder);
            userRepository.save(user);
        }
        pantryRepository.save(vlinder);



        return "redirect:/pantry/all";
    }
}
