package toby_spring.hellospring.exrate;

import org.springframework.web.client.RestTemplate;
import toby_spring.hellospring.payment.ExRateProvider;

import java.math.BigDecimal;

public class RestTemplateExRateProvider implements ExRateProvider {

    private final RestTemplate restTemplate;

    public RestTemplateExRateProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public BigDecimal getExRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;
        return restTemplate.getForObject(url, ExChangeData.class).rates().get("KRW");
    }
}
