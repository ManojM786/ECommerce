package com.e_commerce.e_commerce.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;

import java.util.Date;

@Entity
@Scope("prototype")
@Table(name="payments_details")
public class PaymentData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    @JsonProperty("payment_id")
    private int paymentId;

    @ManyToOne
    @JsonProperty("order_id")
    @JoinColumn(name="order_id")
    private OrderDetails orderDetails;

    private double amount;

    @Enumerated(EnumType.STRING)
    @Column(name="payment_status")
    @JsonProperty("payment_status")
    private PaymentStatus paymentStatus;


    @JsonProperty("payment_date")
    @Column(name = "payment_date")
    @Temporal(TemporalType.DATE)
    private Date paymentDate;

    @PrePersist
    protected void onCreate() {
        if (paymentDate == null) {
            paymentDate = new Date();
        }
        if(paymentStatus == null){
            paymentStatus = PaymentStatus.PENDING;
        }
    }

    public int getPaymentId() {
        return paymentId;
    }

    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}