
import java.util.ArrayList;
import java.util.List;

public class PrimeNumbers {

    public static List<Integer> findPrimeNumbers(List<Integer> numbers) {
        List<Integer> primeNumbers = new ArrayList<>();

        for (int num : numbers) {
            if (isPrime(num)) {
                primeNumbers.add(num);
            }
        }

        return primeNumbers;
    }

    public static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        
        return true;
    }

    public static void main(String[] args) {
        List<Integer> numbers = List.of(2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> primeNumbers = findPrimeNumbers(numbers);

        System.out.println("Prime numbers from the list: " + primeNumbers);
    }
}
