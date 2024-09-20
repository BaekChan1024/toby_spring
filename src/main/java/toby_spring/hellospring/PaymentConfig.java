package toby_spring.hellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import toby_spring.hellospring.exrate.CachedExRateProvider;
import toby_spring.hellospring.payment.ExRateProvider;
import toby_spring.hellospring.exrate.SimpleExRateProvider;
import toby_spring.hellospring.payment.PaymentService;

import java.time.Clock;

@Configuration
public class PaymentConfig {

    @Bean
    public ExRateProvider exRateProvider() {
        return new SimpleExRateProvider();
    }

    @Bean
    public ExRateProvider cachedRateProvider() {
        return new CachedExRateProvider(exRateProvider());
    }

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(cachedRateProvider(), clock());
    }

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }
}
