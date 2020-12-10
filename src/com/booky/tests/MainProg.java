/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booky.tests;

import com.booky.entities.Author;
import com.booky.entities.Blog;
import com.booky.entities.Book;
import com.booky.entities.Cart;
import com.booky.entities.CartItem;
import com.booky.entities.Category;
import com.booky.entities.Charity;
import com.booky.entities.Comment;
import com.booky.entities.Customer;
import com.booky.entities.Donation;
import com.booky.entities.Event;
import com.booky.entities.Language;
import com.booky.entities.Library;
import com.booky.entities.Order;
import com.booky.entities.Pack;
import com.booky.entities.Role;
import com.booky.entities.ShippingAddress;
import com.booky.services.AuthorService;
import com.booky.services.BlogService;
import com.booky.services.BookService;
import com.booky.services.CartItemService;
import com.booky.services.CartService;
import com.booky.services.CategoryService;
import com.booky.services.CharityService;
import com.booky.services.CommentService;
import com.booky.services.CustomerService;
import com.booky.services.DonationService;
import com.booky.services.EventService;
import com.booky.services.LanguageService;
import com.booky.services.LibraryService;
import com.booky.services.OrderService;
import com.booky.services.PackService;
import com.booky.services.ShippingAddressService;
import java.util.ArrayList;
import java.sql.Date;

/**
 *
 * @author gharbimedaziz
 */
public class MainProg {

    public static void main(String[] args) {
        // DECLARING A ROLE 
        Role admin = new Role("Admin");

        // DECLARING THE AUTHOR
        String author_description = "This is J.K Rowling's description.";
        String author_description2 = "This is JOHN GREEN's description.";
        Author jkRowling = new Author(1, "J.K", "Rowling", 50, "jkrowling@gmail.com", author_description);
        Author johnGreen = new Author(2, "John", "Green", 34, "johngreen@gmail.com", author_description2);
        // TESTING THE AUTHOR SERVICE
        AuthorService as = new AuthorService();
        //as.createAuthor(jkRowling);
        //as.deleteAuthor(1);
        //as.updateAuthor(johnGreen);
        //as.readAuthor(2);

        // DECLARING THE CATEGORIES 
        Category fantasy = new Category(1, "Fantasy");
        Category romance = new Category(2, "Sci-Fi");
        Category adventure = new Category(3, "Adventure");
        ArrayList<Category> categories = new ArrayList<>();
        categories.add(fantasy);
        categories.add(romance);
        categories.add(adventure);
        ArrayList<Category> categories2 = new ArrayList<>();
        categories2.add(fantasy);
        categories2.add(romance);
        // TESTING THE CATEGORY SERVICE 
        CategoryService categoryServ = new CategoryService();
        //categoryServ.createCategory(romance);
        //categoryServ.deleteCategory(4);
        //categoryServ.updateCategory(romance);
        //categoryServ.readCategory(2);

        // DECLARING THE LANGUAGE
        Language english = new Language(1, "English");
        Language french = new Language(2, "French");
        Language arabe = new Language(3, "Arabic");
        // TESTING THE LANGUAGE SERVICE
        LanguageService langService = new LanguageService();
        //langService.createLanguage(arabe);
        //langService.updateLanguage(port);
        //langService.readLanguage(2);

        // TESTING THE PACK SERVICE
        Pack p1 = new Pack("Harry Potter Series", 120, "This is harry potter's collection", "pack_harry.jpeg");
        Pack p2 = new Pack(1, "Harry Potter 2 Series", 200, "This is harry potter 2's collection", "pack_harry2.jpeg");
        PackService ps = new PackService();
        //ps.createPack(p1);
        //ps.updatePack(p2);
        //ps.readPack(1);

        // TESTING THE EVENT SERVICE + CUSTOMER SERVICE
        Date date = new Date(622790105000L);
        CustomerService custService = new CustomerService();
        Customer C1 = new Customer(8, "Mounir", "Fridi", 22, "fridi@gmail.com", "10 rue lafayette", "+216 26-64-99-70", null, admin);
        Customer C2 = new Customer(11, "Sami", "Dridi", 22, "dridi@gmail.com", "8 rue carthage", "+216 98-62-20-40", null, admin);
        Customer C3 = new Customer(15, "Rami", "Kalel", 22, "kalel@gmail.com", "4 rue slimen", "+216 23-55-41-70", null, admin);
        Customer C4 = new Customer(17, "Maha", "Bacha", 22, "maha@gmail.com", "4 rue slimen", "+216 23-55-41-70", "maha.jpeg", admin);
        // custService.addCustomer(C1);
        // custService.addCustomer(C2);
        //custService.addCustomer(C3);
        //custService.deleteCustomer(18);
        //custService.readCustomer(16);
        //custService.updateCustomer(C4);
        ArrayList<Customer> customers = new ArrayList<>();
        customers.add(C1);
        customers.add(C2);
        ArrayList<Customer> customers2 = new ArrayList<>();
        customers2.add(C3);
//        Event e = new Event("Father's day", "father's day description", date, date, null, customers);
//        Event e1 = new Event(6, "Mother's day", "mother's day description", date, date, null, customers2);
        EventService es = new EventService();
        //es.createEvent(e);
        //es.updateEvent(e1);
        //es.readEvent(6);

        // TESTING THE CHARITY SERVICE
        Charity c1 = new Charity("SOS Gamarth", "SOS Gamarth's description", null);
        Charity c2 = new Charity(2, "SOS Gamarth 2", "SOS Gamarth 2's description", "sosgamarth.jpg");
        CharityService cs = new CharityService();
        //cs.createCharity(c1);
        //cs.deleteCharity(1);
        //cs.updateCharity(c2);
        //cs.readCharity(2);

        // TESTING THE DONATION SERVICE
        Donation d1 = new Donation("i love sos gamarth", 50.3, C2, c2);
        Donation d2 = new Donation(2, "i like it", 12.6, C2, c2);
        DonationService ds = new DonationService();
        //ds.createDonation(d1);
        //ds.deleteDonation(1);
        //ds.updateDonation(d2);
        //ds.readDonation(4);

        // DECLARING THE BOOK
        String description1 = "This is harry potter's book description";
        String description2 = "This is harry potter's book description2";
        Book b1 = new Book("Harry Potter and the Philosopher's Stone", 30, description1, 1, null, jkRowling, categories, english, 5);
        Book b2 = new Book(8, "Harry Potter and The Goblet Of Fire", 52.6, description2, 0, "harrypotter4.jpg", jkRowling, categories, english, 4, c2);

        // TESTING THE BOOK SERVICE
        BookService bs = new BookService();
        //bs.createBook(b2);
        //bs.deleteBook(9);
        //bs.updateBook(b2);
        //bs.readBook(8);

        // TESTING THE LIBRARY SERVICE 
        Library l1 = new Library("booky.tn", "Charguia", "+216 55-36-57-79", "booky.tn@gmail.com");
        Library l2 = new Library(1, "booky.tn", "ESPRIT Charguia", "+216 55-36-57-79", "booky.tn@gmail.com");
        LibraryService ls = new LibraryService();
        //ls.createLibrary(l1);
        //ls.updateLibrary(l2);
        //ls.readLibrary(1);

        // TESTING THE ORDER SERVIVCE
        CartItem item1 = new CartItem(b2, 2);
        CartItem item2 = new CartItem(b1, 3);
        ArrayList<CartItem> items = new ArrayList<>();
        Cart cart1 = new Cart(1, C2, items, 150);
        Cart cart2 = new Cart(2, C1, null, 60);
        //TESTING THE CART SERVICE
        CartService cartServ = new CartService();
        //cartServ.createCart(cart2);
        cartServ.readCart(2);
        Order o1 = new Order(cart1, 25, "Par carte bancaire", date, null, null, 0);
        Order o2 = new Order(2, cart1, 25, "A la livraison", date, null, null, 1);
        OrderService os = new OrderService();
        //os.createOrder(o1);
        //os.updateOrder(o2);
        //os.readOrder(2);
        
        // TESTING THE SHIPPING ADDRESS SERVICE 
        ShippingAddress sh1 = new ShippingAddress("12 rue slimen", "Ariana", 2060, C4, o2);
        ShippingAddressService shipServ = new ShippingAddressService();
        //shipServ.createShippingAddress(sh1);
        //shipServ.deleteShippingAddress(1);

        // TESTING THE CART ITEM SERVICE
        CartItem item3 = new CartItem(b1, 3, cart1);
        CartItem item4 = new CartItem(1, b2, 6, cart1);
        CartItemService cartitemServ = new CartItemService();
        //cartitemServ.createCartItem(item3);
        //cartitemServ.updateCartItem(item4);
        //cartitemServ.readCartItem(1);

        // TESTING BLOG + COMMENT SERVICES 
        Blog blog1 = new Blog("My blog", "This is my blog description", johnGreen);
        Blog blog2 = new Blog(1, "My blog 2", "This is my blog 2 description", johnGreen);
        Comment comment1 = new Comment("nice blog !", C1, blog1);
        Comment comment2 = new Comment(1, "this is cool !", null, null);
        CommentService commentServ = new CommentService();
        //commentServ.createComment(comment1);
        //commentServ.updateComment(comment2);
        //commentServ.readComment(1);
        BlogService blogServ = new BlogService();
        //blogServ.createBlog(blog1);
        //blogServ.updateBlog(blog2);
        //blogServ.readBlog(1);
    }
}
