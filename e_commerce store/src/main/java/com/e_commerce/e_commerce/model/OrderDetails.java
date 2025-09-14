package com.e_commerce.e_commerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders_details")
@Scope("prototype")
public class OrderDetails {

    @Id
    @JsonProperty("order_id")
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonProperty("user_id")
    private UserData userData;

    @OneToMany(mappedBy = "orderDetails", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToMany(mappedBy = "orderDetails", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PaymentData> paymentData = new ArrayList<>();


    @JsonProperty("total_amount")
    @Column(name="total_amount")
    private double totalAmount;

    @JsonProperty("order_date")
    @Column(name="order_date")
    private Date orderDate;


    @JsonProperty("order_status")
    @Column(name= "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;


    @PrePersist
    protected  void onCreate(){
        if(orderStatus == null){
            orderStatus = OrderStatus.PENDING;
        }
        if (orderDate == null) {
            orderDate = new Date();
        }
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public List<PaymentData> getPaymentData() {
        return paymentData;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void setPaymentData(List<PaymentData> paymentData) {
        this.paymentData = paymentData;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
