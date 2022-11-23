package prime_logic;

public class PrimeFinder {
    public boolean findPrime(int number) {

        //2 je prvočíslo
        if (number == 2) return true;

        //Prvočísla musí být větší, než 1 a nesmí být sudá
        if (number <= 1 || number % 2 == 0) {
            return false;
        }

        //kontrola zda se jedná o prvočíslo celočíselným dělením
        boolean isPrime = true;
        for (int i = 3; i < Math.round(number/2.0); i = i + 2) {
            if (number % i == 0) {
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }
}
