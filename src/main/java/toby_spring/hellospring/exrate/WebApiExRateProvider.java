package toby_spring.hellospring.exrate;

import toby_spring.hellospring.api.ApiTemplate;
import toby_spring.hellospring.api.ErApiExRateExtractor;
import toby_spring.hellospring.api.HttpClientApiExecutor;
import toby_spring.hellospring.payment.ExRateProvider;

import java.math.BigDecimal;

public class WebApiExRateProvider implements ExRateProvider {

    private final ApiTemplate apiTemplate;

    public WebApiExRateProvider(ApiTemplate apiTemplate) {
        this.apiTemplate = apiTemplate;
    }

    @Override
    public BigDecimal getExRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;
        return apiTemplate.runApiForExRate(url, new HttpClientApiExecutor(), new ErApiExRateExtractor());
    }

}
