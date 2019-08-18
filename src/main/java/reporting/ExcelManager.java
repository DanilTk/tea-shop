package reporting;

import model.Client;
import model.Product;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelManager {
    private static final String[] CLIENT_COLUMNS = {"id", "first_name", "last_name", "creation_timestamp", "deletion_timestamp"};
    private static final String[] PRODUCT_COLUMNS = {"id", "name", "price", "measure_name", "product_category"};
    private static final String[] BASKET_COLUMNS = {"id", "client", "product"};
    private static Workbook workbook = new XSSFWorkbook();
    private static Sheet sheet;
    private static Row row;

    public static List<Client> importClientFromExcel(String filePath) { //TODO: add validator of columns
        workbook = openExcel(filePath);
        List<Client> importedClients = new ArrayList<>();
        sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.rowIterator();

        while (rowIterator.hasNext()) {
            row = rowIterator.next();
            if (row.getRowNum() == 0) continue;
            Client extractedClient = ClassExtractor.readClientFromExcelRow(row);
            importedClients.add(extractedClient);
        }
        closeExcel();
        return importedClients;
    }

    public static List<Product> importProductFromExcel(String filePath) { //TODO: add validator of columns
        workbook = openExcel(filePath);
        List<Product> importedProducts = new ArrayList<>();
        sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.rowIterator();

        while (rowIterator.hasNext()) {
            row = rowIterator.next();
            if (row.getRowNum() == 0) continue;
            Product extractProduct = ClassExtractor.readProductFromExcelRow(row);
            importedProducts.add(extractProduct);
        }
        closeExcel();
        return importedProducts;
    }

    public static void exportClientToExcel(String filePath, List<Client> clients) { //TODO: Fix path saving
        createWorkbook("Clients", CLIENT_COLUMNS);
        ClassExtractor.writeClientToExcelRow(clients, workbook);
        resizeColumns(CLIENT_COLUMNS);
        writeOutput(filePath, "Clients ");
        closeExcel();
    }

    public static void exportProductToExcel(String filePath, List<Product> products) { //TODO: Fix path saving
        createWorkbook("Products", PRODUCT_COLUMNS);
        ClassExtractor.writeProductToExcelRow(products, workbook);
        resizeColumns(PRODUCT_COLUMNS);
        writeOutput(filePath, "Products ");
        closeExcel();
    }

    private static Workbook openExcel(String filePath) {
        try {
            return WorkbookFactory.create(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            //TODO: add message
        }
        return null;
    }

    private static void closeExcel() {
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
            //TODO: add message
        }
    }

    private static void createWorkbook(String worksheetName, String[] headers) {
        sheet = workbook.createSheet(worksheetName);
        row = sheet.createRow(0);
        createHeaders(headers);
    }

    private static void writeOutput(String filePath, String fileName) {
        FileOutputStream savedFile = null;
        try {
            savedFile = new FileOutputStream(filePath.concat(fileName).concat(" ").concat(String.valueOf(LocalDate.now())).concat(".xlsx"));
            workbook.write(savedFile);
            savedFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createHeaders(String[] headers) {
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 11);
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(headerFont);

        for (int i = 0; i < headers.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(cellStyle);
        }
    }

    private static void resizeColumns(String[] columns) {
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    /*
    public static void exportBasketToExcel(String filePath, List<Basket> baskets) { //TODO: finalize method
        createWorkbook("Baskets", BASKET_COLUMNS);
        extractBasketParameters(baskets);
        writeOutput("Baskets " + LocalDate.now() + ".xlsx");
        closeExcel();
    }
*/

    /*
    public static List<Basket> importBasketFromExcel(String filePath) { //TODO: finalize method
        workbook = openExcel(filePath);
        List<Basket> importedData = new ArrayList<>();
        sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.rowIterator();

        while (rowIterator.hasNext()) {
            row = rowIterator.next();
            if (row.getRowNum() == 0) continue;
            Basket extractProduct = ClassExtractor.extractBasket(row);
            importedData.add(extractProduct);
        }
        closeExcel();
        return importedData;
    }
*/
}