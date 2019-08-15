package reporting;

import model.Basket;
import model.Client;
import model.Product;
import model.enums.MeasureName;
import model.enums.ProductCategory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.math.BigDecimal;

public class ClassExtractor {

    public static Client extractClient(Row row) {
        Integer id = convertCellToDouble(row.getCell(0)).intValue();
        String firstName = convertCellToString(row.getCell(1));
        String lastName = convertCellToString(row.getCell(2));

        return new Client(id, firstName, lastName);
    }

    public static Basket extractBasket(Row row) {
        Client client = null;

        return new Basket(client);
    }

    public static Product extractProduct(Row row) {
        Integer id = convertCellToDouble(row.getCell(0)).intValue();
        String name = convertCellToString(row.getCell(1));
        BigDecimal price = BigDecimal.valueOf(convertCellToDouble(row.getCell(2)));
        MeasureName measureName = MeasureName.valueOf(convertCellToString(row.getCell(1)).toUpperCase());
        ProductCategory productCategory = ProductCategory.valueOf(convertCellToString(row.getCell(1)).toUpperCase());

        return new Product(id, name, price, measureName, productCategory);
    }

    private static String convertCellToString(Cell cell) {
        String stringCell = "";

        if (cell != null) {
            try {
                stringCell = cell.toString();
            } catch (Exception e) {
                return null;
            }
        }
        return stringCell;
    }

    private static Double convertCellToDouble(Cell cell) {
        Double doubleCell = null;

        if (cell != null) {
            try {
                doubleCell = Double.parseDouble(cell.toString());
            } catch (Exception e) {
                return null;
            }
        }
        return doubleCell;
    }
}