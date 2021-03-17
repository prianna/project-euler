import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Problem118 {
    public static boolean isPrime(int n, List<Integer> knownPrimes) {
        for (Integer prime:knownPrimes) {            
            if (n % prime == 0) {
                return false;
            }

            if (prime * prime > n){
                return true;
            }
        }
        return true;
    }

   public static void main(String[] args){

       System.out.println(digitsBitmask(213));

       int max = 31623;
       List<Integer> primes = new ArrayList<>();

       //primes.add(2);
       //primes.add(3);
       primes.add(5);

       int three = 0;
       for (int i = 5; i < max; i+=2) {
           three++;

           if (three == 3) {
               three = 0;
               continue;
           }

           if (isPrime(i, primes)) {
               primes.add(i);
               System.out.println(i);
           }
       }


   }

   public static int digitsBitmask(int prime) {
       int mask = 0;
       while (prime > 0) {
           int digit = prime % 10;
           prime = prime / 10;

           mask = mask | (1 << digit - 1);
       }
       return mask;
   }
}
