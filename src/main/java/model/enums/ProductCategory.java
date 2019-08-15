package model.enums;

import java.util.HashMap;
import java.util.Map;

public enum ProductCategory {
    TEA,
    COFFEE,
    FOOD,
    ACCESSORY;

    private static Map<ProductCategory, Integer> productCategoryIntegerMap = new HashMap<>();
    private static Map<Integer, ProductCategory> integerProductCategoryMap = new HashMap<>();

    public static int getProductCategoryIdByEnum(ProductCategory productCategory) {
        productCategoryIntegerMap.put(ProductCategory.TEA, 1);
        productCategoryIntegerMap.put(ProductCategory.COFFEE, 2);
        productCategoryIntegerMap.put(ProductCategory.FOOD, 3);
        productCategoryIntegerMap.put(ProductCategory.ACCESSORY, 4);

        return productCategoryIntegerMap.get(productCategory);
    }

    public static ProductCategory getProductCategoryEnumById(Integer productCategoryId) {
        integerProductCategoryMap.put(1, ProductCategory.TEA);
        integerProductCategoryMap.put(2, ProductCategory.COFFEE);
        integerProductCategoryMap.put(3, ProductCategory.FOOD);
        integerProductCategoryMap.put(4, ProductCategory.ACCESSORY);

        return integerProductCategoryMap.get(productCategoryId);
    }
}