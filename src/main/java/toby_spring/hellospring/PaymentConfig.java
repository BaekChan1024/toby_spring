package toby_spring.hellospring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import toby_spring.hellospring.api.ApiTemplate;
import toby_spring.hellospring.api.ErApiExRateExtractor;
import toby_spring.hellospring.api.SimpleApiExecutor;
import toby_spring.hellospring.exrate.CachedExRateProvider;
import toby_spring.hellospring.exrate.RestTemplateExRateProvider;
import toby_spring.hellospring.exrate.WebApiExRateProvider;
import toby_spring.hellospring.payment.ExRateProvider;
import toby_spring.hellospring.exrate.SimpleExRateProvider;
import toby_spring.hellospring.payment.PaymentService;

import java.time.Clock;

@Configuration
public class PaymentConfig {

    @Bean
    public ExRateProvider exRateProvider() {
        return new RestTemplateExRateProvider(restTemplate());
    }

    @Bean
    public ExRateProvider cachedRateProvider() {
        return new CachedExRateProvider(exRateProvider());
    }

    @Bean
    public ApiTemplate apiTemplate() {
        return new ApiTemplate(new SimpleApiExecutor(), new ErApiExRateExtractor());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
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
