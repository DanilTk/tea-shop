package service.validation;

import model.Product;
import service.exceptions.ProductException;

import java.math.BigDecimal;

public class ProductValidator {

    public static boolean validateProduct(Product product) throws ProductException {

        return validateProductIsNotNull(product)
                && validateProductNameIsNotNull(product)
                && validateProductPriceIsNotNull(product)
                && validateMeasureNameIsNotNull(product)
                && validateProductCategoryIsNotNull(product)
                && validateProductPriceIsNaturalNumber(product);
    }

    private static boolean validateProductIsNotNull(Product product) {

        return product != null;
    }

    private static boolean validateProductNameIsNotNull(Product product) {

        return product.getName() != null;
    }

    private static boolean validateProductPriceIsNotNull(Product product) {

        return product.getPrice() != null;
    }

    private static boolean validateMeasureNameIsNotNull(Product product) {

        return product.getMeasureName() != null;
    }

    private static boolean validateProductCategoryIsNotNull(Product product) {

        return product.getProductCategory() != null;
    }

    //TODO: check why/if <=0 comparison is needed?
    private static boolean validateProductPriceIsNaturalNumber(Product product) {

        return product.getPrice().compareTo(BigDecimal.ZERO) <= 0;
    }
}