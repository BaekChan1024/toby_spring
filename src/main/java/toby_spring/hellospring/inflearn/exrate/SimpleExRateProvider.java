package toby_spring.hellospring.inflearn.exrate;

import toby_spring.hellospring.inflearn.payment.ExRateProvider;

import java.math.BigDecimal;

public class SimpleExRateProvider implements ExRateProvider {

    public BigDecimal getExRate(String currency) {
        if (currency.equals("USD")) {
            return new BigDecimal(1000);
        }
        throw new IllegalArgumentException("Unsupported currency: " + currency);
    }
}
