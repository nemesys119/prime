package com.infinitelambda.task.prime.model;

import io.swagger.annotations.ApiModel;

import java.util.Objects;

/**
 * Prime checker response object which contains the result of the primality test and the next prime number which is greater than the number given in the request.
 */
@ApiModel(description = "Prime checker response object which contains the result of the primality test and the next prime number which is greater than the number given in the request.")
public class PrimeNumberCheckerResponse {
    private boolean prime;
    private String nextPrime;

    public PrimeNumberCheckerResponse(boolean prime, String nextPrime) {
        this.prime = prime;
        this.nextPrime = nextPrime;
    }

    public boolean isPrime() {
        return prime;
    }

    public String getNextPrime() {
        return nextPrime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrimeNumberCheckerResponse that = (PrimeNumberCheckerResponse) o;
        return isPrime() == that.isPrime() &&
                getNextPrime().equals(that.getNextPrime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(isPrime(), getNextPrime());
    }

    @Override
    public String toString() {
        return "PrimeNumberCheckerResponse{" +
                "prime=" + prime +
                ", nextPrime=" + nextPrime +
                '}';
    }
}
