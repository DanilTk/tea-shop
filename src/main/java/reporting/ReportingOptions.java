package reporting;

import java.io.IOException;
import java.util.List;

public interface ReportingOptions {

    List<?> importFromFile(String filePath, DataSetToReport dataReported) throws IOException;

    Long exportToFile(String filePath, List<?> arrayToExport) throws IOException;
}