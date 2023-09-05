package com.aizatgaz;

import com.aizatgaz.dao.*;
import com.aizatgaz.entities.*;
import com.aizatgaz.entities.enums.Rating;
import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.*;


@Getter
public class MovieManager {

    private final ActorDAO actorDAO;
    private final AddressDAO addressDAO;
    private final CategoryDAO categoryDAO;
    private final CityDAO cityDAO;
    private final CountryDAO countryDAO;
    private final CustomerDAO customerDAO;
    private final FilmDAO filmDAO;
    private final FilmTextDAO filmTextDAO;
    private final InventoryDAO inventoryDAO;
    private final LanguageDAO languageDAO;
    private final PaymentDAO paymentDAO;
    private final RentalDAO rentalDAO;
    private final StaffDAO staffDAO;
    private final StoreDAO storeDAO;

    private final SessionFactory sessionFactory;

    {
        Properties properties = new Properties();
        properties.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
        properties.put(Environment.URL, "jdbc:mysql://localhost:3306/hibernate_javarush");
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        properties.put(Environment.USER, "root");
        properties.put(Environment.PASS, "root");
        properties.put(Environment.HBM2DDL_AUTO, "validate");
        properties.put(Environment.SHOW_SQL, "true");
        properties.put(Environment.HIGHLIGHT_SQL, "true");

        sessionFactory = new Configuration()
                .addProperties(properties)
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Address.class)
                .addAnnotatedClass(Category.class)
                .addAnnotatedClass(City.class)
                .addAnnotatedClass(Country.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Film.class)
                .addAnnotatedClass(FilmText.class)
                .addAnnotatedClass(Inventory.class)
                .addAnnotatedClass(Language.class)
                .addAnnotatedClass(Payment.class)
                .addAnnotatedClass(Rental.class)
                .addAnnotatedClass(Staff.class)
                .addAnnotatedClass(Store.class)
                .buildSessionFactory();

        actorDAO = new ActorDAO(sessionFactory);
        addressDAO = new AddressDAO(sessionFactory);
        categoryDAO = new CategoryDAO(sessionFactory);
        cityDAO = new CityDAO(sessionFactory);
        countryDAO = new CountryDAO(sessionFactory);
        customerDAO = new CustomerDAO(sessionFactory);
        filmDAO = new FilmDAO(sessionFactory);
        filmTextDAO = new FilmTextDAO(sessionFactory);
        inventoryDAO = new InventoryDAO(sessionFactory);
        languageDAO = new LanguageDAO(sessionFactory);
        paymentDAO = new PaymentDAO(sessionFactory);
        rentalDAO = new RentalDAO(sessionFactory);
        staffDAO = new StaffDAO(sessionFactory);
        storeDAO = new StoreDAO(sessionFactory);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    //creating a random customer from current customers
    public Customer createCustomer() {
        Customer customer = new Customer();

        List<Customer> all = customerDAO.getAll();

        int name = (int) (Math.random() * all.size());
        int lastname = (int) (Math.random() * all.size());
        int address = (int) (Math.random() * all.size());
        List<Store> stores = storeDAO.getAll();
        int store = (int) (Math.random() * stores.size());

        customer.setActive(true);
        customer.setCreateDate(LocalDateTime.now());
        customer.setFirstName(all.get(name).getFirstName());
        customer.setLastName(all.get(lastname).getLastName());
        customer.setAddress(all.get(address).getAddress());
        customer.setStore(stores.get(store));

        customerDAO.save(customer);

        return customer;
    }

    //Returning back to inventory first received from query rented film
    public void returnFilm() {
        rentalDAO.returnFilm();
    }

    //Buying first available film from DB for customer
    public Film buyFilm(Customer customer) {

        Store store = customer.getStore();
        Staff staff = store.getStaff();

        List<Inventory> availableFilms = inventoryDAO.getAvailableFilms(staff);
        if (availableFilms.isEmpty()) throw new NullPointerException("No available films");

        Rental rental = new Rental();

        Inventory inventory = availableFilms.get(0);

        rental.setCreateDate(LocalDateTime.now());
        rental.setInventory(inventory);
        rental.setCustomer(customer);
        rental.setStaff(staff);

        Payment payment = new Payment();
        payment.setAmount(BigDecimal.valueOf(6.99));
        payment.setCustomer(customer);
        payment.setStaff(staff);
        payment.setRental(rental);
        payment.setPaymentDate(rental.getCreateDate());

        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(rental);
            session.persist(payment);
            transaction.commit();
        }
        return inventory.getFilm();
    }

    //adding new film in DB
    public Film newFilm(String title, String description, Rating rating, Set<Actor> actors, Set<Category> categories) {
        Film film = new Film();
        film.setTitle(title);
        film.setReleaseYear(Year.now());
        film.setDescription(description);
        film.setLanguage(languageDAO.getById(1));
        Random random = new Random();
        film.setRentalDuration((byte) random.nextInt(3, 8));
        film.setRentalRate(BigDecimal.valueOf(random.nextDouble(0.99, 5)));
        film.setReplacementCost(BigDecimal.valueOf(random.nextDouble(9.99, 30)));
        film.setRating(rating);
        film.setActors(actors);
        film.setCategories(categories);

        filmDAO.save(film);

        FilmText filmText = new FilmText();
        filmText.setDescription(description);
        filmText.setFilm(film);
        filmText.setTitle(title);
        filmText.setId(film.getId());

        filmTextDAO.save(filmText);

        return film;
    }

    //for testing
    public static void main(String[] args) {

        MovieManager manager = new MovieManager();
//
//        Actor actor = new Actor();
//        actor.setId(205);
//        actor.setLastName("a1234123sd");
//        actor.setFirstName("as4134dasd12341234");
//
//        manager.actorDAO.update(actor);

        manager.actorDAO.removeById(205);

    }
}