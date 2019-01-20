package com.tristanchanson.petshop.inventory;

public class Product {

    private String name;
    private Integer count;
    private Double cost;
    private Double price;

    public Product(String name, Integer count, Double cost, Double price) {
        this.name = name;
        this.count = count;
        this.cost = cost;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Integer getCount() {
        return count;
    }

    public Double getCost() {
        return cost;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", count=" + count +
                ", cost=" + cost +
                ", price=" + price +
                '}';
    }
}
