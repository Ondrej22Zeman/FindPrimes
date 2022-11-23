import file_manipulation.DataGetter;
import output.PrimeChecker;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {

        String path = args[0];
        DataGetter dataGetter = new DataGetter();

        ArrayList<Integer> data = dataGetter.getDataFromFile(path);

        PrimeChecker pc = new PrimeChecker();
        pc.checkPrimeValues(data);

    }
}