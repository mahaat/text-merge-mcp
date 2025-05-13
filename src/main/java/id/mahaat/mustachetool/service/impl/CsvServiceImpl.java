package id.mahaat.mustachetool.service.impl;

import de.siegmar.fastcsv.writer.CsvWriter;
import id.mahaat.mustachetool.service.CsvService;
import jakarta.inject.Singleton;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Singleton
public class CsvServiceImpl implements CsvService {
    @Override
    public void writeCsv(Path outputFile, List<List<String>> data) throws IOException {
        try (CsvWriter csv = CsvWriter.builder().build(outputFile)) {
            for (List<String> datum : data) {
                csv.writeRecord(datum);
            }
        }
    }
}
