package file_manipulation;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class DataGetter {

    //zpracování dat ze souboru
    public ArrayList<Integer> getDataFromFile(String path) throws IOException {
        File file = new File(path);

        FileInputStream fileInputStream = new FileInputStream(file);
        Workbook workbook;

        //podle formátu souboru se zvolí metoda čtení
        if (Objects.equals(getExtension(path), ".xls")) {
            workbook = new HSSFWorkbook(fileInputStream);
        } else {
            workbook = new XSSFWorkbook(fileInputStream);
        }

        Sheet sheet = workbook.getSheetAt(0);
        int dataColumnIndex = -1;


        //Vyhledání sloupce s názvem "Data"
        for (int i = 1; i < sheet.getRow(0).getLastCellNum(); i++) {
            String name = sheet.getRow(0).getCell(i).getStringCellValue();
            if (Objects.equals(name, "Data")) {
                dataColumnIndex = i;
            }
        }

        //Jestliže nebyl nalezen sloupec "Data", list primes bude obsahovat -1
        if (dataColumnIndex == -1) {
            System.out.println("Nebyl nalezen sloupec \"Data\"");
            return null;
        }

        ArrayList<Integer> data = getCellValues(sheet, dataColumnIndex);
        return data;
    }



    private ArrayList<Integer> getCellValues(Sheet sheet, int dataColumnIndex){
        ArrayList<Integer> data = new ArrayList<>();
        int numberOfRows = sheet.getPhysicalNumberOfRows();

        int cellValue = 0;
        //Proměnná pro kontrolu zda se jedná o číslo
        String stringValue = null;
        double value;

        for (int i = 1; i < numberOfRows; i++) {
            Cell cell = sheet
                    .getRow(i)
                    .getCell(dataColumnIndex);
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
                data.add(cellValue);
            }
        }
        return data;
    }

    //Funkce získává příponu souboru
    private String getExtension(String path){
        String extension = null;
        int i = path.lastIndexOf('.');
        if (i > 0) {
            extension = path.substring(i);
        }
        return extension;
    }

    //Funkce zjišťuje zda je String číslo, např ("2" je číslo)
    private boolean isNumeric(String str) {
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
