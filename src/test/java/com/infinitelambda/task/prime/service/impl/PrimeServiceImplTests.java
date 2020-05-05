package com.infinitelambda.task.prime.service.impl;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

public class PrimeServiceImplTests {

    private PrimeServiceImpl service;

    @BeforeEach
    public void init() {
        service = new PrimeServiceImpl();
    }

    @Test
    public void isPrimeWithSmallPrimeNumberTest() {
        BigInteger number = new BigInteger("5");

        boolean result = service.isPrime(number);
        Assertions.assertEquals(true, result);
    }

    @Test
    public void isPrimeWithBigPrimeNumberTest() {
        BigInteger number = new BigInteger("6864797660130609714981900799081393217269435300143305409394463459185543183397656052122559640661454554977296311391480858037121987999716643812574028291115057151");

        boolean result = service.isPrime(number);
        Assertions.assertEquals(true, result);
    }

    @Test
    public void isPrimeWithNotPrimeNumberTest() {
        BigInteger number = new BigInteger("13428");

        boolean result = service.isPrime(number);
        Assertions.assertEquals(false, result);
    }

    @Test
    public void isPrimeWithNegativeNumberTest() {
        BigInteger number = new BigInteger("-13");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.isPrime(number);
        });
    }

    @Test
    public void isPrimeWithLessThan2NumberTest() {
        BigInteger number = new BigInteger("0");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.isPrime(number);
        });
    }

    @Test
    public void nextPrimeTest() {
        BigInteger number = new BigInteger("11");
        BigInteger expected = new BigInteger("13");

        BigInteger result=service.nextPrime(number);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void nextPrimeWithNegativeNumberTest() {
        BigInteger number = new BigInteger("-13");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.nextPrime(number);
        });
    }

    @Test
    public void nextPrimeWithLessThan2NumberTest() {
        BigInteger number = new BigInteger("0");

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.nextPrime(number);
        });
    }
}
