package reporting;

import model.Client;
import model.Product;
import model.enums.MeasureName;
import model.enums.ProductCategory;
import org.apache.poi.ss.usermodel.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

class ClassExtractor {

    static Client readClientFromExcelRow(Row row) {
        Integer id = extractInteger(row.getCell(0));
        String firstName = extractString(row.getCell(1));
        String lastName = extractString(row.getCell(2));
        LocalDateTime creationTime = extractDateTime(row.getCell(3));
        LocalDateTime deletionTime = extractDateTime(row.getCell(4));

        return new Client(id, firstName, lastName, creationTime, deletionTime);
    }

    static Product readProductFromExcelRow(Row row) {
        Integer id = extractInteger(row.getCell(0));
        String name = extractString(row.getCell(1));
        BigDecimal price = extractBigDecimal(row.getCell(2));
        MeasureName measureName = extractMeasureName(row.getCell(3));
        ProductCategory productCategory = extractProductCategory(row.getCell(4));

        return new Product(id, name, price, measureName, productCategory);
    }

    static void writeClientToExcelRow(List<Client> clients, Workbook workbook) {
        Sheet sheet = workbook.getSheetAt(0);
        CreationHelper createHelper = workbook.getCreationHelper();
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
        for (int i = 0; i < clients.size(); i++) {
            Client client = clients.get(i);
            int rowNumber = i + 1;
            Row row = sheet.createRow(rowNumber);
            setCellValue(row.createCell(0), client.getId());
            setCellValue(row.createCell(1), client.getFirstName());
            setCellValue(row.createCell(2), client.getLastName());
            setCellValue(row.createCell(3), convertToDate(client.getCreationTimestamp()));
            row.getCell(3).setCellStyle(cellStyle);
            setCellValue(row.createCell(4), convertToDate(client.getDeletionTimestamp()));
            row.getCell(4).setCellStyle(cellStyle);
        }
    }

    static void writeProductToExcelRow(List<Product> products, Workbook workbook) {
        Sheet sheet = workbook.getSheetAt(0);
        CreationHelper createHelper = workbook.getCreationHelper();
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            int rowNumber = i + 1;
            Row row = sheet.createRow(rowNumber);
            setCellValue(row.createCell(0), product.getId());
            setCellValue(row.createCell(1), product.getName());
            setCellValue(row.createCell(2), product.getPrice());
            setCellValue(row.createCell(3), product.getMeasureName());
            setCellValue(row.createCell(4), product.getProductCategory());
        }
    }

    private static String extractString(Cell cell) {
        if (cell != null) {
            try {
                return cell.toString();
            } catch (NullPointerException e) {
                return null;
            }
        }
        return null;
    }

    private static MeasureName extractMeasureName(Cell cell) { //TODO: Robert to advise how to make generic method to extract any enum if possible
        if (cell != null) {
            try {
                return MeasureName.valueOf(cell.toString().toUpperCase());
            } catch (NullPointerException | IllegalArgumentException e) {
                return null;
            }
        }
        return null;
    }

    private static ProductCategory extractProductCategory(Cell cell) { //TODO: Robert to advise how to make generic method to extract any enum if possible
        if (cell != null) {
            try {
                return ProductCategory.valueOf(cell.toString().toUpperCase());
            } catch (NullPointerException | IllegalArgumentException e) {
                return null;
            }
        }
        return null;
    }

    private static Integer extractInteger(Cell cell) {
        if (cell != null) {
            try {
                return (int) Double.parseDouble(cell.toString());
            } catch (NullPointerException | NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    private static BigDecimal extractBigDecimal(Cell cell) {
        if (cell != null) {
            try {
                return BigDecimal.valueOf(Double.parseDouble(cell.toString()));
            } catch (NullPointerException | NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    private static LocalDateTime extractDateTime(Cell cell) {
        if (cell != null) {
            try {
                Date date = cell.getDateCellValue();
                return Instant.ofEpochMilli(date.getTime())
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime();
            } catch (NullPointerException e) {
                return null;
            }
        }
        return null;
    }

    private static Date convertToDate(LocalDateTime date) {
        if (date != null) {
            try {
                return Date.from(date.toLocalDate()
                        .atStartOfDay(ZoneId.systemDefault())
                        .toInstant());
            } catch (NullPointerException ignored) {
            }
        }
        return null;
    }

    private static void setCellValue(Cell cell, String value) {
        try {
            cell.setCellValue(value);
        } catch (Exception e) {
            cell.setCellValue("");
        }
    }

    private static void setCellValue(Cell cell, Enum<?> value) {
        try {
            String convertedEnum = value.toString();
            cell.setCellValue(convertedEnum);
        } catch (Exception e) {
            cell.setCellValue("");
        }
    }

    private static void setCellValue(Cell cell, Integer value) {
        try {
            cell.setCellValue(value);
        } catch (Exception e) {
            cell.setCellValue("");
        }
    }

    private static void setCellValue(Cell cell, BigDecimal value) {
        try {
            cell.setCellValue(value.doubleValue());
        } catch (Exception e) {
            cell.setCellValue("");
        }
    }

    private static void setCellValue(Cell cell, Date value) {
        try {
            cell.setCellValue(value);
        } catch (Exception e) {
            cell.setCellValue("");
        }
    }

    /*
    public static Basket buildBasketFromExcelRow(Row row) { //TODO: finalize

        Client client = null;
        return new Basket(client);

        return null;
    }
     */

     /*
        public static void writeBasketToExcelRow(List<Basket> baskets, Workbook workbook) { //TODO: finalize
            Sheet sheet = workbook.getSheetAt(0);
            CreationHelper createHelper = workbook.getCreationHelper();
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
            for (int i = 0; i < baskets.size(); i++) {
                Basket basket = baskets.get(i);
                int rowNumber = i + 1;
                Row row = sheet.createRow(rowNumber);
                row.createCell(0).setCellValue(basket.getId());

                row.createCell(1).setCellValue(basket.getClient());
                row.createCell(1).setCellValue(basket.getProducts());
            }

        }
    */
}