package ch.sbb.mongodb.readers;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CustomCSVReader {

  @Value("${gtfs.directory}")
  protected String directory;

  protected List<String[]> getCSVRows(String filename) throws CsvValidationException, IOException {
    List<String[]> rows = new ArrayList<>();
    Path pathToFile = Paths.get(this.directory, filename);
    try (CSVReader csvReader = new CSVReader(new FileReader(pathToFile.toString()))) {
      String[] values = null;
      boolean isFirstRow = true;
      while ((values = csvReader.readNext()) != null) {
        if (isFirstRow) {
          isFirstRow = false;
        } else {
          rows.add(values);
        }
      }
    }
    return rows;
  }
    
    
    
    
  protected List<String[]> getCSVRows1(String filename) throws IOException {
    List<String[]> rows = new ArrayList<>();
    Path pathToFile = Paths.get(this.directory, filename);
    BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8);
    String line = br.readLine(); // headerline
    line = br.readLine(); // loop until all lines are read 
    while (line != null) {
      line = line.replace("\"", "");
      String[] attributes = line.split(",");
      rows.add(attributes);
      line = br.readLine();
    }
    return rows;
  }
}
