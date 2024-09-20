package toby_spring.hellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import toby_spring.hellospring.payment.ExRateProvider;
import toby_spring.hellospring.payment.ExRateProviderStub;
import toby_spring.hellospring.payment.PaymentService;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

@Configuration
public class TestPaymentConfig {

    @Bean
    public ExRateProvider exRateProvider() {
        return new ExRateProviderStub(BigDecimal.valueOf(1000));
    }

    @Bean
    public PaymentService paymentService(ExRateProvider exRateProvider, Clock clock) {
        return new PaymentService(exRateProvider, clock);
    }

    @Bean
    public Clock clock() {
        return Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }
}
