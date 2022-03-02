package com.ylab;

import java.math.BigInteger;
import java.util.Scanner;

public class fib {
    //на 47 числе фибоначчи при возвращаемом значении int происходит переполнение памяти int - max = 2^31-1 = 2147483647
    //тип данных long переполняется на 93 числе
    static long fibRec(int n) {
        if (n < 0) return -1;//ошибка
        else if (n <= 1) return n;
        return fibRec(n - 1) + fibRec(n - 2);
    }

    //реализация с массивом - при n=1000000 - Java heap space
    static BigInteger fibCycleWithArray(int n) {
        if (n < 0) return BigInteger.valueOf(-1);
        else if (n <= 1) return BigInteger.valueOf(n);
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
        if (n < 0) return BigInteger.valueOf(-1);
        else if (n <= 1) return BigInteger.valueOf(n);

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

    public static void main(String[] args) {
        int n = 0;
        while (n != -1) {
            n = new Scanner(System.in).nextInt();
            //System.out.println(fibRec(n));
            //System.out.println(fibCycleWithArray(n));
            System.out.println(fibCycleWithThreeVars(n));
        }
    }
}
