package com.infinitelambda.task.prime.service;

import java.math.BigInteger;

/**
 * This service handles prime number oriented invocations.
 */
public interface PrimeService {

    /**
     * Decide whether the given number prime or not
     * @param number number has to be checked
     * @return a flag which indicates the primality result
     */
    boolean isPrime(BigInteger number);

    /**
     * Give back the first prime number which is bigger than the number given as a parameter.
     */
    BigInteger nextPrime(BigInteger number);
}
