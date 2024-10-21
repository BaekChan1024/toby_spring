package toby_spring.hellospring.inflearn.exrate;

import toby_spring.hellospring.inflearn.payment.ExRateProvider;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CachedExRateProvider implements ExRateProvider {

    private final ExRateProvider exRateProvider;
    private BigDecimal rate;
    private LocalDateTime cachedExpirationTime;

    public CachedExRateProvider(ExRateProvider exRateProvider) {
        this.exRateProvider = exRateProvider;
    }

    @Override
    public BigDecimal getExRate(String currency) {
        if (rate==null || cachedExpirationTime.isBefore(LocalDateTime.now())) {
            rate = exRateProvider.getExRate(currency);
            cachedExpirationTime = LocalDateTime.now().plusSeconds(3);
            System.out.println("Cached Update");
        }
        return rate;
    }
}
