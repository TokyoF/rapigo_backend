package com.rapigo.backend_rapigo.model;

import jakarta.persistence.*;

@Entity
public class Coupon {

    @Id
    private String code;

    private int percent;

    public Coupon() {}

    public Coupon(String code, int percent) {
        this.code = code;
        this.percent = percent;
    }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public int getPercent() { return percent; }
    public void setPercent(int percent) { this.percent = percent; }
}
