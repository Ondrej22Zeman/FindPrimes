public class FindPrimes {
    public boolean findPrime(int number) {
        // 1 a 2 jsou prvočísla
        switch (number){
            case 1:
            case 2:
            case 3:
            case 5:
            case 7:return true;
        }
        if (number == 1 || number == 2) {
            return true;
        }
        //Prvočísla musí být větší, než 0 a nesmí být sudá
        if (number <= 0 || number % 2 == 0) {
            return false;
        }
        //kontrola zda se jedná o prvočíslo celočíselným dělením
        for (int i = 3; i < number/2; i += 2) {
            if (number % i == 0) {
                return false;
            }
            return true;
        }
        return false;
    }
}
