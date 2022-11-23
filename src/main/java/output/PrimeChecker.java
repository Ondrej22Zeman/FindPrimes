package output;

import java.util.ArrayList;
import prime_logic.PrimeFinder;

public class PrimeChecker {
    public void checkPrimeValues(ArrayList<Integer> data){
        PrimeFinder pf = new PrimeFinder();
        boolean first = true;
        //Vypsání prvočísel
        for (int value : data) {
            if (pf.findPrime(value)) {
                if (first) {
                    System.out.print("Nalezené prvočísla: " + value);
                    first = false;
                }
                System.out.print(", " + value);
            }
        }
        if (first){
            System.out.println("Nebyly nalezeny žádné prvočísla.");
        }
    }
}
