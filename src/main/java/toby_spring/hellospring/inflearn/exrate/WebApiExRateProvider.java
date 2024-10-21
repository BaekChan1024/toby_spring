package toby_spring.hellospring.inflearn.exrate;

import toby_spring.hellospring.inflearn.api.ApiTemplate;
import toby_spring.hellospring.inflearn.api.ErApiExRateExtractor;
import toby_spring.hellospring.inflearn.api.HttpClientApiExecutor;
import toby_spring.hellospring.inflearn.payment.ExRateProvider;

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
