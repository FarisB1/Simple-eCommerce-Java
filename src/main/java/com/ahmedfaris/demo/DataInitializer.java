package com.ahmedfaris.demo;

import com.ahmedfaris.demo.Models.AppUser;
import com.ahmedfaris.demo.Models.Product;
import com.ahmedfaris.demo.Models.Role;
import com.ahmedfaris.demo.Repositories.ProductRepository;
import com.ahmedfaris.demo.Repositories.RoleRepository;
import com.ahmedfaris.demo.Repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public DataInitializer(RoleRepository roleRepository,
                           UserRepository userRepository,
                           ProductRepository productRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() == 0) {
            addRoles();
        }

        if (userRepository.count() == 0) {
            addUser();
        }

        if (productRepository.count() == 0) {
            addProducts();
        }
    }

    private void addRoles() {
        Role userRole = new Role("USER");
        Role adminRole = new Role("ADMIN");
        roleRepository.save(adminRole);
        roleRepository.save(userRole);
    }

    private void addUser(){
        Role adminRole = roleRepository.findByName("ADMIN");
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        AppUser user = new AppUser();
        user.setUsername("admin");
        user.setPassword(encoder.encode("password"));
        user.setEmail("admin@gmail.com");
        user.setEnabled(true);
        user.setRole(adminRole);
        userRepository.save(user);
    }

    private void addProducts() {
        //1
        Product product1 = new Product();
        product1.setName("BATTERY WHEY GOLD ISOLATE, 1600g");
        product1.setDescription("Mješavina izolata i hidrolizata surutke\n" +
                "19,2 g BCAA i 16,2 g L-glutamina na 100 g proizvoda\n" +
                "Bez laktoze, glutena, šećera i aspartama\n" +
                "Dovoljno za 64 porcije");
        product1.setPrice(165);
        product1.setImage("/images/img-4.png");
        product1.setStock(100);
        productRepository.save(product1);

        //2

        Product product2 = new Product();
        product2.setName("PROTEINI.SI VEGAN PROTEIN");
        product2.setDescription("Ukusni veganski proteinski šejk u prahu\n" +
                "\n" +
                "Sa vitaminima B5 i B12 i gvožđem\n" +
                "\n" +
                "Bogata BCAA aminokiselinama\n" +
                "\n" +
                "Čokolada, čokoladni okus lješnjaka i bobičastog voća");
        product2.setPrice(31);
        product2.setImage("/images/img-5.png");
        product2.setStock(100);
        productRepository.save(product2);

        //3

        Product product3 = new Product();
        product3.setName("OPTIMUM WHEY PROTEIN GOLD STANDARD, 2270g");
        product3.setDescription("Visoko kvalitetne surutkine bjelančevine\n" +
                "24 g bjelančevina na obrok\n" +
                "Dodani enzimi za bolju probavu");
        product3.setPrice(189);
        product3.setImage("/images/img-6.png");
        product3.setStock(100);
        productRepository.save(product3);

        //4

        Product product4 = new Product();
        product4.setName("EXERCISE MAT");
        product4.setDescription("Mekana i otporna podloga za vježbanje\n" +
                "Omogućava sigurno i udobno vježbanje na podu\n" +
                "1,5 cm debljine\n" +
                "Priložena traka za jednostavno prenošenje");
        product4.setPrice(39);
        product4.setImage("/images/img-7.png");
        product4.setStock(100);
        productRepository.save(product4);

        //5

        Product product5 = new Product();
        product5.setName("PROTEINI.SI SHAKER TR BLUE-SILVER");
        product5.setDescription("Proteini.si šejker, 700 ml\n" +
                "S natpisom PROTEINI.SI\n" +
                "Jednostavan za uporabu, bez BPA");
        product5.setPrice(6);
        product5.setImage("/images/img-8.png");
        product5.setStock(100);
        productRepository.save(product5);

        //6

        Product product6 = new Product();
        product6.setName("PROTEINI.SI 100% PURE CREATINE, 200g");
        product6.setDescription("100% kreatin monohidrat\n" +
                "Povećava fizičku sposobnost\n" +
                "Posebno se preporučuje za fizičke aktivnosti visokog intenziteta");
        product6.setPrice(33);
        product6.setImage("/images/img-9.png");
        product6.setStock(100);
        productRepository.save(product6);


        //7

        Product product7 = new Product();
        product7.setName("NUTREND CREATINE MONOHYDRATE, 300g");
        product7.setDescription("Povećajte snagu i eksplozivnost\n" +
                "100% kreatin monohidrat u prahu\n" +
                "Poboljšava regeneraciju nakon napornih vježbi\n" +
                "Maleno povećanje volumena mišića");
        product7.setPrice(59);
        product7.setImage("/images/img-10.png");
        product7.setStock(100);
        productRepository.save(product7);

        //8

        Product product8 = new Product();
        product8.setName("PROTEINI.SI RUKAVICE ZA BOKS, pink, 8oz");
        product8.setDescription("Kožne rukavice za boks\n" +
                "Ergonomski oblik\n" +
                "Dobra apsorpcija udaraca\n" +
                "5 rupica za bolju prozračnost");
        product8.setPrice(79);
        product8.setImage("/images/img-11.png");
        product8.setStock(100);
        productRepository.save(product8);
    }
}
