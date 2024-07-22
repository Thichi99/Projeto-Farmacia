package com.generation.projetoFarmacia.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "This field is required!")
    private String productName;

    @NotBlank(message = "This field is required!")
    private String productDescription;

    @NotNull(message = "This field is required!")
    private double productPrice;

    @ManyToOne
    @JsonIgnoreProperties("category")
    private Category categories;

    public Category getCategories() {
        return categories;
    }

    public void setCategories(Category categories) {
        this.categories = categories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

//    public LocalDate getDataEntrada() {
//        return dataEntrada;
//    }
//
//    public void setDataEntrada(LocalDate dataEntrada) {
//        this.dataEntrada = dataEntrada;
//    }
//
//    public LocalDate getDataValidade() {
//        return dataValidade;
//    }
//
//    public void setDataValidade(LocalDate dataValidade) {
//        this.dataValidade = dataValidade;
//    }

}
