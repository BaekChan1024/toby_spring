package toby_spring.hellospring.exrate;

import toby_spring.hellospring.payment.ExRateProvider;

import java.io.IOException;
import java.math.BigDecimal;

public class SimpleExRateProvider implements ExRateProvider {

    public BigDecimal getExRate(String currency) {
        if (currency.equals("USD")) {
            return new BigDecimal(1000);
        }
        throw new IllegalArgumentException("Unsupported currency: " + currency);
    }
}
