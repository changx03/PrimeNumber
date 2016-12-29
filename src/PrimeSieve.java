import java.util.ArrayList;
import java.util.List;

/**
 * Created by gungr on 6/11/2016.
 */
public class PrimeSieve{

    /**
     * Implementation 1: not efficient enough
     *
     * @param n
     * @return
     */
    public List<Integer> primeSieve(int n){    // from 2 to n
        boolean[] b = new boolean[++n];
        int sqrt_n = (int) Math.sqrt(n);
        for(int i = 2; i <= sqrt_n; i++){
            if(!b[i]){
                int i2 = i * i;
                for(int j = 0, k = i2; k < n; j++, k = i2 + j * i)
                    b[k] = true;
            }
        }
        List<Integer> prime = new ArrayList<>();
        for(int i = 2; i < n; i++){
            if(!b[i])
                prime.add(i);
        }
        return prime;
    }

    /**
     * List of prime # from 0 to n (include n)
     *
     * @param n
     * @return
     */
    public List<Integer> primeSieve2(int n){
        boolean[] notP = new boolean[++n];
        List<Integer> p = new ArrayList<>();
        for(int i = 2; i < n; i++){
            if(!notP[i]){
                p.add(i);
                for(int j = 2; i * j < n; j++)
                    notP[i * j] = true;
            }
        }
        return p;
    }

    /**
     * Is n a prime #?
     *
     * @param n
     * @return
     */
    public boolean isPrime(int n){
        if(n == 2) return true;
        if(n <2 || n % 2 == 0) return false;
        for(int i = 3; i * i <= n; i += 2){
            if(n % i == 0) return false;
        }
        return true;
    }

    /**
     * # of primes less than n (not include n!)
     *
     * @param n
     * @return
     */
    public int countPrime(int n){
        int c = 0;
        boolean[] notP = new boolean[n];
        for(int i = 2; i < n; i++){
            if(!notP[i]){
                c++;
                for(int j = 2; i * j < n; j++)
                    notP[i * j] = true;
            }
        }
        return c;
    }

    public static void main(String[] argv){
        PrimeSieve ps = new PrimeSieve();

        List<Integer> prime = new ArrayList<>();
        long t = System.nanoTime();
        for(int i = 0; i < 100; i++){
            prime = ps.primeSieve(49);
        }
        t = System.nanoTime() - t;
        System.out.println(prime);
        System.out.println("Time: " + t);
        prime.clear();

        // list of prime number from 0 to n
        t = System.nanoTime();
        for(int i = 0; i < 100; i++){
            prime = ps.primeSieve2(49);
        }
        t = System.nanoTime() - t;
        System.out.println(prime);
        System.out.println("Time: " + t);

        // is prime number
        int n = 131071;
        System.out.format("%s is prime: %s\n", n, ps.isPrime(n));
        n = 2;
        System.out.format("%s is prime: %s\n", n, ps.isPrime(n));

        // count prime
        n = 49970;
        System.out.format("# of prime less than %s: %s\n", n, ps.countPrime(n));
    }
}
