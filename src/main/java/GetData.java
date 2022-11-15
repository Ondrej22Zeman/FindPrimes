import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class GetData {

    //zpracování dat ze souboru
    public ArrayList<Integer> processData(OpenFile openFile) throws IOException {
        File file = openFile.getFile();
        FindPrimes findPrimes = new FindPrimes();
        ArrayList<Integer> primes = new ArrayList<Integer>();

        //Při vypnutí okna bez výběru souboru se vrátí prázdný soubor a nic se nevypíše
        if (file == null) {
            primes.add(0);
            return primes;
        }
        ;

        FileInputStream fileInputStream = new FileInputStream(file);
        Workbook workbook;

        //podle formátu souboru se zvolí metoda čtení
        if (Objects.equals(openFile.getExtension(), ".xls")) {
            workbook = new HSSFWorkbook(fileInputStream);
        } else {
            workbook = new XSSFWorkbook(fileInputStream);
        }

        Sheet sheet = workbook.getSheetAt(0);
        int columnIndex = -1;
        int rows = sheet.getPhysicalNumberOfRows();

        //Vyhledání sloupce s názvem "Data"
        for (int i = 1; i < sheet.getRow(0).getLastCellNum(); i++) {
            String name = sheet.getRow(0).getCell(i).getStringCellValue();
            if (Objects.equals(name, "Data")) {
                columnIndex = i;
            }
        }

        //Jestliže nebyl nalezen sloupec "Data", list primes bude obsahovat -1
        if (columnIndex == -1) {
            primes.add(-1);
            return primes;
        }

        int cellValue = 0;
        //Proměnná pro kontrolu zda se jedná o číslo
        String stringValue = null;
        double value;

        for (int i = 1; i < rows; i++) {
            Cell cell = sheet
                    .getRow(i)
                    .getCell(columnIndex);
            if (cell != null) {
                //Switch metoda z důvodu rozdílného čtení různých typů buněk
                switch (cell.getCellType()) {
                    case STRING -> {
                        stringValue = cell.getStringCellValue();
                        value = Double.parseDouble(stringValue);
                        //kontrola celého čísla
                        if (isNumeric(stringValue)) {
                            if (isWhole(value)) cellValue = (int) value;
                        }else continue;
                    }
                    case NUMERIC -> {
                        value = cell.getNumericCellValue();
                        //kontrola celého čísla
                        if (isWhole(value)) {
                            cellValue = (int) value;
                        }else continue;
                    }
                }
                if (findPrimes.findPrime(cellValue)) {
                    primes.add(cellValue);
                }

            }
        }
        //Jestli nebudou nalezeny žádné prvočíslo, list primes bude obsahovat pouze -2
        if (primes.size() == 0) primes.add(-2);
        return primes;
    }

    //Funkce zjišťuje zda je String číslo, např ("2" je číslo)
    public boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //Kontrola zda se jedná o celé číslo
    private boolean isWhole(double number){
        return number % 1 == 0;
    }
}
