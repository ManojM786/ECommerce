package com.e_commerce.e_commerce.model;

import jakarta.persistence.*;


@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartId;
    @ManyToOne
    @JoinColumn(name="userId")
    private UserData user;
    @ManyToOne
    @JoinColumn(name="productId")
    private Product product;
    private Integer quantity;

    public Cart() {
    }

    public Cart(Integer cartId, UserData user, Product product, Integer quantity) {
        this.cartId = cartId;
        this.user = user;
        this.product = product;
        this.quantity = quantity;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public UserData getUser() {
        return user;
    }

    public void setUser(UserData user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", user=" + user +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
