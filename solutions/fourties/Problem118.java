import java.util.ArrayList;
import java.util.List;

public class Problem118 {
    public static boolean isPrime(int n, List<Integer> knownPrimes) {
        for (Integer prime : knownPrimes) {
            if (n % prime == 0) {
                return false;
            }

            if (prime * prime > n) {
                return true;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // sqrt(987654321)
        int max = 31623;
        List<Integer> primes = new ArrayList<>();
 
        for (int i = 3; i < max; i += 2) {
            if (isPrime(i, primes)) {
                primes.add(i);
            }
        }

        // check for uniqueness of digits
        // before adding bigger primes
        int min = max, nineDigits = 100_000_000, setCount = 0;
        max = 987654321;

        for (int i = min; i < max; i+=2) {
            if (repeatedDigits(i)) {
                continue;
            }

            if (isPrime(i, primes)) {
                if (i > nineDigits) {
                    setCount++;
                } else {
                    primes.add(i);
                }
            }
        }

        List<Integer> filteredPrimes = new ArrayList<Integer>();
        filteredPrimes.add(2);
        
        for (Integer prime : primes) {
            if (!repeatedDigits(prime)) {
                filteredPrimes.add(prime);
            }
        }

        System.out.println("Primes: " + primes.size() + ", filtered: " + filteredPrimes.size());
        primes = filteredPrimes;

        int[] masks = new int[primes.size()];
        int[] digits = new int[primes.size()];

        int digitsCounter = 1;
        int powerOfTen = 10;
        for (int i = 0; i < masks.length; i++ ) {
           int prime = primes.get(i);
           if (prime >= powerOfTen) {
               powerOfTen *= 10;
               digitsCounter++;
           } 
           
           masks[i] = primeBitmask(prime);
           digits[i] = digitsCounter;
        }
        
        // compute sets
        setCount += computeSets(0, 0, masks, digits, 0);

        System.out.println(setCount);
    }

    public static int computeSets(int bitmask, int digitCount, int[] masks, int[] digits, int index) {
        if (bitmask == primeBitmask(987654321)) {
            return 1;
        }

        int sets = 0;

        for (int i = index; i < masks.length; i++) {
            if (digitCount + digits[i] > 9) {
                break;
            }

            sets += computeSet(bitmask, digitCount, masks, digits, i);
        }

        return sets;
    }

    public static int computeSet(int bitmask, int digitCount, int[] masks, int[] digits, int index) {
        int thisMask = masks[index];
        digitCount += digits[index];

        if ((bitmask & thisMask) != 0) {
            return 0;
        } else {
            return computeSets(bitmask | thisMask, digitCount, masks, digits, index++);
        }
    }

    // Check digits within a prime for repetition
    public static boolean repeatedDigits(int prime) {
        int bits = 0;

        while (prime > 0) {
            int digit = prime % 10;
            if (digit == 0) {
                return true;
            }

            prime = prime / 10;
            int shiftedDigit = 1 << digit;

            if ((bits & (shiftedDigit)) != 0) {
                return true;
            }
            bits = bits | (shiftedDigit);
        }
        return false;
    }

    // Create a bitmask to check for uniqueness of digits between
    // any number of primes.
    public static int primeBitmask(int prime) {
        int mask = 0;
        while (prime > 0) {
            int digit = prime % 10;
            prime = prime / 10;

            mask = mask | (1 << digit);
        }
        return mask;
    }
}
