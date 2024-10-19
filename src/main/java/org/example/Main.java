package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // using the Euclidean algorithm
    public static int findGCD(int num1, int num2){
        // the algorithm says to write num1 as num2(q) + remainder
        // should the remainder not equal 0, the process is repeated with
        // num2 in the place of num1 and remainder in the place of num2
        // when the remainder is 0, the gcd is the previous remainder
        int remainder = num1%num2;
        if(remainder != 0){
            return findGCD(num2, remainder);
        }
        return num2;
    }

    public static int findLCM(int num1, int num2){
        int gcd = findGCD(num1, num2);
        return (num1 * num2) / gcd; // equation for lcm given gcd
    }

    // using the Sieve of Eratosthenes algorithm
    public static ArrayList<Integer> allPrimes(int upperRange){
        // the sieve finds the primes below a value v by first listing the values [2,v]
        // it then finds the smallest prime p (starts with 2), adds to the list of primes, and
        // crosses out all numbers that are a multiple of that prime by generating a sequence starting
        // at p with the constant difference between each element equal to p
        // this is repeated with the next largest prime until p^2 > v, in which case all uncrossed nums
        // are prime

        ArrayList<Integer> nums = new ArrayList<>();
        ArrayList<Boolean> isPrime = new ArrayList<>();
        ArrayList<Integer> primes = new ArrayList<>();
        for(int i = 2; i < upperRange+1; i++){
            nums.add(i);
            isPrime.add(true);
        }

        if(nums.isEmpty()){
            return primes;
        }

        int index = 0;
        while(nums.get(index)*nums.get(index) <= upperRange){
            int smallestPrime = nums.get(index);
            primes.add(smallestPrime);

            int notPrimes = index;
            while(notPrimes < upperRange-1){
                isPrime.set(notPrimes, false);
                notPrimes = notPrimes + nums.get(index);
            }

            index++;
            while(!isPrime.get(index)){
                index++;
            }
        }

        while(index < upperRange-1){
            if(isPrime.get(index)){
                primes.add(nums.get(index));
            }
            index += 2;
        }

        return primes;
    }

    public static int enterValidInt(String message){
        Scanner input = new Scanner(System.in);
        int z;
        while(true) {
            try {
                System.out.println(message);
                z = input.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Please enter an integer");
                input.next();
            }
        }
        return z;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("type the number of what you want to calculate:" +
                "\n1. GCD" + "\n2. LCM" + "\n3. Primes below a value");
        String choice = input.next();

        if(choice.equals("1")){
            int a, b;
            a = enterValidInt("Enter first integer: ");
            b = enterValidInt("Enter second integer");
            int gcd = findGCD(a, b);
            System.out.println("gcd(" + a + ", " + b + ") = " + gcd);
        } else if(choice.equals("2")){
            int a, b;
            a = enterValidInt("Enter first integer: ");
            b = enterValidInt("Enter second integer");
            int lcm = findLCM(a, b);
            System.out.println("gcd(" + a + ", " + b + ") = " + lcm);
        } else if(choice.equals("3")){
            int a;
            do{
                a = enterValidInt("Enter an integer less than 1000");
                if(a > 1000) {
                    System.out.println("please be less than 1000");
                }
            }while(a > 1000);

            ArrayList<Integer> primes = allPrimes(a);
            String primeList = "";
            for(int i = 0; i < primes.size(); i++){
                primeList = primeList + primes.get(i) + ", ";
                if(i%26 == 25){
                    primeList = primeList + "\n";
                }
            }
            System.out.println("Primes below " + a + ": " + primeList);
        }
    }
}