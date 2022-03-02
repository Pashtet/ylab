package com.ylab;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class fib {
    //на 47 числе фибоначчи при возвращаемом значении int происходит переполнение памяти int - max = 2^31-1 = 2147483647
    //тип данных long переполняется на 93 числе
    static long fibRec(int n) {

        if (n <= 1) return n;
        return fibRec(n - 1) + fibRec(n - 2);
    }

    //реализация с массивом - при n=1000000 - Java heap space
    static BigInteger fibCycleWithArray(int n) {

        if (n <= 1) return BigInteger.valueOf(n);
        BigInteger[] biArray = new BigInteger[n + 1];
        int i = 2;
        biArray[0] = BigInteger.valueOf(0);
        biArray[1] = BigInteger.valueOf(1);

        while (i <= n) {
            biArray[i] = biArray[i - 2].add(biArray[i - 1]);
            i++;
        }
        return biArray[n];
    }

    //реализация с BigInteger без массива с 3 переменными
    static BigInteger fibCycleWithThreeVars(int n) {

        if (n <= 1) return BigInteger.valueOf(n);

        int i = 1;
        BigInteger result = BigInteger.valueOf(0);
        BigInteger temp1 = BigInteger.valueOf(0);
        BigInteger temp2 = BigInteger.valueOf(1);

        while (i != n) {
            result = temp2.add(temp1);
            temp1 = temp2;
            temp2 = result;
            i++;
        }
        return result;
    }

    //реализация через класс с кэшем и рекурсией
    static class fibWithCache {

        static BigInteger[] cache;

        public static void init(int n) {
            cache = new BigInteger[n + 1];
        }

        public static BigInteger fib(int n) {
            if (cache[n] != null) {
                return cache[n];
            }

            if (n <= 1) {
                return BigInteger.valueOf(n);
            }
            BigInteger temp = fib(n - 1).add(fib(n - 2));
            cache[n] = temp;
            return temp;
        }
    }

    public static void main(String[] args) {
        int n = new Scanner(System.in).nextInt();
        if (n < 0) System.out.println("Числа Фибоначчи считаются с 0!");
        else {
            //System.out.println(fibRec(n));//рекурсивная функция
            //System.out.println(fibCycleWithArray(n));//цикл с массивом промежуточных значений
            //System.out.println(fibCycleWithThreeVars(n));//цикл с тремя промежуточными переменными

            //класс с кэшем и рекурсивной функцией
            fibWithCache.init(n);
            System.out.println(fibWithCache.fib(n));
        }

    }

}
