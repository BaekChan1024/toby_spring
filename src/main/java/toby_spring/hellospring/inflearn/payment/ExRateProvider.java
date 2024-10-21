package toby_spring.hellospring.inflearn.payment;

import java.math.BigDecimal;

public interface ExRateProvider {

    BigDecimal getExRate(String currency);
}
