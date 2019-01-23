package com.tristanchanson.petshop.inventory;

public class Product {

    private Integer id;
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

    public Product(Integer id, String name, Integer count, Double cost, Double price) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.cost = cost;
        this.price = price;
    }

    public Integer getId() {
        return id;
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
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", cost=" + cost +
                ", price=" + price +
                '}';
    }
}
