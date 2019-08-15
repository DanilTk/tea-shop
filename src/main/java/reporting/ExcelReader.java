package reporting;

import model.Basket;
import model.Client;
import model.Product;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReader implements ReportingOptions {


    @Override
    //TODO: Robert to advise how to retrieve ? sign as a parameter to check if ? is x then y...
    public List<?> importFromFile(String filePath, DataSetToReport dataReported) throws IOException {
        Workbook workbook = WorkbookFactory.create(new File(filePath));
        List<?> importedData = new ArrayList<>();
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.rowIterator();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            if (row.getRowNum() == 0) continue;

            if (dataReported.equals(DataSetToReport.CLIENT)) {
                Client extractedObj = ClassExtractor.extractClient(row);
                //    importedData.add(extractedObj);
            } else if (dataReported.equals(DataSetToReport.PRODUCT)) {
                Product extractedObj = ClassExtractor.extractProduct(row);
                //    importedData.add(extractedObj);
            } else if (dataReported.equals(DataSetToReport.BASKET)) {
                Basket extractedObj = ClassExtractor.extractBasket(row);
                //    importedData.add(extractedObj);
            } else {
                throw new NullPointerException(); //TODO: adjust error thrown
            }
            workbook.close();
        }
        return importedData;
    }

    @Override
    public Long exportToFile(String filePath, List<?> arrayToExport) throws IOException {

        return null;
    }
}