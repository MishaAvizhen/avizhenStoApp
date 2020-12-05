package com.avizhen.avizhenSto.entity;

import javax.persistence.*;

@Entity
@Table(name = "detail_catalog", schema = "avizhen_sto")
public class DetailCatalog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private String price;

    public DetailCatalog(String name, String description, String price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public DetailCatalog(){
    }

    public Integer getId() {
        return id;
    }

    public DetailCatalog setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public DetailCatalog setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DetailCatalog setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public DetailCatalog setPrice(String price) {
        this.price = price;
        return this;
    }

    @Override
    public String toString() {
        return "DetailCatalog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
