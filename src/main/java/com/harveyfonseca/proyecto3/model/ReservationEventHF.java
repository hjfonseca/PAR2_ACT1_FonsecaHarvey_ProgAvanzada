package com.harveyfonseca.proyecto3.model;

import java.util.ArrayList;
import java.util.List;

public final class ReservationEventHF {

    private final String id;
    private final String passengerName;
    private final Double price;
    private final List<String> emails;
    //constructor con copia defensiva
    public ReservationEventHF(String id, String passengerName, Double price, List<String> emails) {
        this.id = id;
        this.passengerName = passengerName;
        this.price = price;
        //copia defensivaa
        this.emails = (emails != null) ? new ArrayList<>(emails) : new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public Double getPrice() {
        return price;
    }
    //getter con la copia defensiva
    public List<String> getEmails() {
        return new ArrayList<>(this.emails);
    }

    @Override
    public String toString() {
        return "ReservationEventHF{" +
                "id='" + id + '\'' +
                ", passengerName='" + passengerName + '\'' +
                ", price=" + price +
                ", emails=" + emails +
                '}';
    }
}
