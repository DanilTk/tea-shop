package model.enums;

import java.util.HashMap;
import java.util.Map;

public enum MeasureName {
    GRAM,
    PIECE;

    private static Map<MeasureName, Integer> measureNameIntegerMap = new HashMap<>();
    private static Map<Integer, MeasureName> integerMeasureNameMap = new HashMap<>();

    public static int getMeasureNameIdByEnum(MeasureName measureName) {
        measureNameIntegerMap.put(MeasureName.GRAM, 1);
        measureNameIntegerMap.put(MeasureName.PIECE, 2);

        return measureNameIntegerMap.get(measureName);
    }

    public static MeasureName getMeasureNameEnumById(Integer measureNameId) {
        integerMeasureNameMap.put(1, MeasureName.GRAM);
        integerMeasureNameMap.put(2, MeasureName.PIECE);

        return integerMeasureNameMap.get(measureNameId);
    }
}