package org.example;

import java.time.LocalDate;

/**
 * Represents a clothing item with details such as name, type, production city, production date, and price.
 */
public class Clothes {
    private String name;
    private String clothType;
    private String cityName;
    private String productionDate;
    private double price;

    /**
     * Constructs a new Clothes object.
     *
     * @param name           the name of the clothing item
     * @param clothType      the type of cloth (e.g., fabric type)
     * @param cityName       the name of the city where the item was produced
     * @param productionDate the date when the item was produced
     * @param price          the price of the clothing item
     */
    public Clothes(String name, String clothType, String cityName, LocalDate productionDate, double price) {
        this.name = name;
        this.clothType = clothType;
        this.cityName = cityName;
        this.productionDate = String.valueOf(productionDate);
        this.price = price;
    }

    /**
     * Returns the name of the clothing item.
     *
     * @return the name of the clothing item
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the type of cloth.
     *
     * @return the cloth type
     */
    public String getClothType() {
        return clothType;
    }

    /**
     * Returns the name of the production city.
     *
     * @return the city name
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * Returns the production date as a string.
     *
     * @return the production date
     */
    public CharSequence getProductionDate() {
        return productionDate;
    }

    /**
     * Returns the price of the clothing item.
     *
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns a string representation of the Clothes object.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "Clothes{" +
                "name = " + name +
                ", clothType = " + clothType +
                ", cityName = " + cityName +
                ", productionDate = " + productionDate +
                ", price = " + price + " UAH" +
                '}';
    }
}


