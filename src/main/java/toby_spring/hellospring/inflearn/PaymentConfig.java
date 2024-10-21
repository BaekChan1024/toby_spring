package toby_spring.hellospring.inflearn;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import toby_spring.hellospring.inflearn.api.ApiTemplate;
import toby_spring.hellospring.inflearn.api.ErApiExRateExtractor;
import toby_spring.hellospring.inflearn.api.SimpleApiExecutor;
import toby_spring.hellospring.inflearn.exrate.CachedExRateProvider;
import toby_spring.hellospring.inflearn.exrate.RestTemplateExRateProvider;
import toby_spring.hellospring.inflearn.payment.ExRateProvider;
import toby_spring.hellospring.inflearn.payment.PaymentService;

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
