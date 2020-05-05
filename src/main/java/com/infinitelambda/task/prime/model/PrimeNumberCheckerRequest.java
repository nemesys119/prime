package com.infinitelambda.task.prime.model;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Objects;

/**
 * Prime checker request object which contains the given number to test primality.
 */
@ApiModel(description = "Prime checker request object which contains the given number to test primality.")
public class PrimeNumberCheckerRequest {

    @Min(value = 2,message = "must be greater than or equal to 2")
    @NotNull(message = "must not be null")
    private BigInteger possiblePrime;

    public PrimeNumberCheckerRequest() {
    }

    public PrimeNumberCheckerRequest(BigInteger possiblePrime) {
        this.possiblePrime = possiblePrime;
    }

    public BigInteger getPossiblePrime() {
        return possiblePrime;
    }

    public void setPossiblePrime(BigInteger possiblePrime) {
        this.possiblePrime = possiblePrime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrimeNumberCheckerRequest that = (PrimeNumberCheckerRequest) o;
        return getPossiblePrime().equals(that.getPossiblePrime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPossiblePrime());
    }

    @Override
    public String toString() {
        return "PrimeNumberCheckerRequest{" +
                "possiblePrime=" + possiblePrime +
                '}';
    }
}
