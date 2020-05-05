package com.infinitelambda.task.prime.controller;

import com.infinitelambda.task.prime.model.PrimeNumberCheckerRequest;
import com.infinitelambda.task.prime.model.PrimeNumberCheckerResponse;
import com.infinitelambda.task.prime.service.PrimeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Prime number checker rest controller
 */
@Api(value = "Prime number checker", description = "Prime number operations are here")
@RestController
public class PrimeController {

    private static final Logger logger = LoggerFactory.getLogger(PrimeController.class);

    @Autowired
    private PrimeService primeService;

    /**
     * Make a primality test ob the given parameter
     * @param request contains the number to test.
     * @return the result of the test as a boolean value and the next bigger prime number
     */
    @ApiOperation(value = "View the result of the prime number check", response = PrimeNumberCheckerResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully check and the response is sent back"),
            @ApiResponse(code = 400, message = "Request validation failed")
    })
    @RequestMapping(value = "/prime", method = RequestMethod.POST)
    public PrimeNumberCheckerResponse checkPrimeNumber(@Valid @RequestBody PrimeNumberCheckerRequest request) {
        logger.info("Request arrived to check primality. Request: " + request);

        boolean isPrime = primeService.isPrime(request.getPossiblePrime());
        BigInteger nextPrime = primeService.nextPrime(request.getPossiblePrime());

        logger.info(MessageFormat.format("current number: {0}. is it prime? {1}. next prime is: {2}", request.getPossiblePrime(), isPrime, nextPrime));
        return new PrimeNumberCheckerResponse(isPrime, nextPrime.toString());
    }

    /**
     * This method responsible for handling validation errors using spring's @{@link ExceptionHandler} mechanism.
     * @param ex {@link MethodArgumentNotValidException} instance which contains the field and the error message created during validation
     * @return fieldname and errormessage pairs
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        logger.error("Validation Failed!", ex);

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
