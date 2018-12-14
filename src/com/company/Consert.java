package com.company;

/**
 * En klass som representerar en konsert, innehåller namn och pris.
 * @author Andreas Sanz
 */
public class Consert {
    private String name;
    private int price;

    public Consert(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
