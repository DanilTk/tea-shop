import enums.MeasureName;
import enums.ProductCategory;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

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