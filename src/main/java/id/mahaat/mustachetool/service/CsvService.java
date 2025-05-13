package id.mahaat.mustachetool.service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface CsvService {

    void writeCsv(Path outputFile, List<List<String>> data) throws IOException;
}
