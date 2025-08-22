package edu.ewubd.projectbookstoreapp;


import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private Book book;
    private static CartManager instance;
    private List<Book> cartItems;

    private CartManager() {
        cartItems = new ArrayList<>();
    }

    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public void addToCart(Book book) {
        cartItems.add(book);
    }

    public void removeFromCart(Book book) {
        cartItems.remove(book);
    }

    public void clearCart() {
        cartItems.clear();
    }

    public List<Book> getCartItems() {
        return cartItems;
    }
}
