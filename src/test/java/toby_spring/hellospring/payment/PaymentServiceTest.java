package toby_spring.hellospring.payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import toby_spring.hellospring.api.ApiTemplate;
import toby_spring.hellospring.exrate.WebApiExRateProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock
    private ExRateProvider exRateProvider;

    @Mock
    private ApiTemplate apiTemplate;

    @InjectMocks
    private PaymentService paymentService;

    private Clock clock;

    @BeforeEach
    void setUp() {
        clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }

    @Test
    void prepare() {
        //given
        PaymentService paymentService = new PaymentService(new WebApiExRateProvider(apiTemplate), clock);
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        //when & then
        // 환율 정보를 가져온다
        assertThat(payment.getExRate()).isNotNull();

        // 원화환산금액 계산
        assertThat(payment.getConvertedAmount()).isEqualTo(payment.getExRate().multiply(payment.getForeignCurrencyAmount()));

        // 원화환산금액의 유효시간을 계산
        assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
        assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));
    }

    @Test
    void prepareByMock() throws IOException {
        //given
        testAmount(BigDecimal.valueOf(500), BigDecimal.valueOf(5_000));
        testAmount(BigDecimal.valueOf(300), BigDecimal.valueOf(3_000));
        testAmount(BigDecimal.valueOf(3000), BigDecimal.valueOf(30_000));



        // 원화환산금액의 유효시간을 계산
//        assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
//        assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));
    }

    @Test
    void validUtil() throws IOException {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(BigDecimal.valueOf(1_000)), clock);
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        LocalDateTime time = LocalDateTime.now(clock).plusMinutes(30);

        Assertions.assertThat(payment.getValidUntil()).isEqualTo(time);
    }

    private void testAmount(BigDecimal exRate, BigDecimal expectedAmount) throws IOException {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(exRate), clock);
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);
        //when & then
        // 환율 정보를 가져온다
        assertThat(payment.getExRate()).isEqualByComparingTo(exRate);

        // 원화환산금액 계산
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(expectedAmount);
    }

    //TODO Junit mock 상황에서 beforeEach를 주입할 수 있는 방법 없나?
//    @Test
//    void prepareByJunitMock() throws IOException {
//        //given
//        when(clock.instant()).thenReturn(Instant.now());
//        when(exRateProvider.getExRate("USD")).thenReturn(BigDecimal.valueOf(1400));
//        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);
//
//
//        //when & then
//        // 환율 정보를 가져온다
//        assertThat(payment.getExRate()).isEqualTo(BigDecimal.valueOf(1400));
//
//        // 원화환산금액 계산
//        assertThat(payment.getConvertedAmount()).isEqualTo(payment.getExRate().multiply(payment.getForeignCurrencyAmount()));
//
//        // 원화환산금액의 유효시간을 계산
//        assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
//        assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));
//    }
}