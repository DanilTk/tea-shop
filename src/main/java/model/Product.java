package model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import model.enums.MeasureName;
import model.enums.ProductCategory;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
public class Product {

    private Long id;
    private String name;
    private BigDecimal price;
    private MeasureName measureName;
    private ProductCategory productCategory;

    public Product(Long id,
                   String name,
                   BigDecimal price,
                   MeasureName measureName,
                   ProductCategory productCategory) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.measureName = measureName;
        this.productCategory = productCategory;
    }

    public Product(String name,
                   BigDecimal price,
                   MeasureName measureName,
                   ProductCategory productCategory) {

        this.name = name;
        this.price = price;
        this.measureName = measureName;
        this.productCategory = productCategory;
    }
}