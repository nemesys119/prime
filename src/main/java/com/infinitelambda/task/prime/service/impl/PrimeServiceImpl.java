package com.infinitelambda.task.prime.service.impl;

import com.infinitelambda.task.prime.service.PrimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.text.MessageFormat;

@Service
public class PrimeServiceImpl implements PrimeService {

    private static final Logger logger = LoggerFactory.getLogger(PrimeServiceImpl.class);

    @Value("${prime.service.certainty}")
    private int certainty = 50;

    @Override
    public boolean isPrime(BigInteger number) {
        logger.debug(MessageFormat.format("Primality test with number {0} and certainty: {1}", number, certainty));
        validateNumber(number);

        return number.isProbablePrime(certainty);
    }

    @Override
    public BigInteger nextPrime(BigInteger number) {
        logger.debug(MessageFormat.format("Search the first prime number which higher than {0}", number));
        validateNumber(number);

        return number.nextProbablePrime();
    }

    protected void validateNumber(BigInteger number) {
        if(number == null || number.compareTo(BigInteger.TWO) < 0) {
            throw new IllegalArgumentException("Number parameter cannot be null and must be greater than 1!");
        }
    }
}
