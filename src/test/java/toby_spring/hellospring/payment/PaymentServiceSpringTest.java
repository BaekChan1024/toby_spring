package toby_spring.hellospring.payment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import toby_spring.hellospring.TestPaymentConfig;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestPaymentConfig.class)
class PaymentServiceSpringTest {

    @Autowired
    PaymentService paymentService;
    @Autowired
    ExRateProviderStub exRateProviderStub;
    @Autowired
    Clock clock;


    @Test
    void prepareByMock() throws IOException {
        //given
        testAmount(BigDecimal.valueOf(1_000), BigDecimal.valueOf(10_000));



        // 원화환산금액의 유효시간을 계산
//        assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
//        assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));
    }

    private void testAmount(BigDecimal exRate, BigDecimal expectedAmount) throws IOException {
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);
        //when & then
        // 환율 정보를 가져온다
        assertThat(payment.getExRate()).isEqualByComparingTo(exRate);

        // 원화환산금액 계산
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(expectedAmount);

        Payment payment2 = paymentService.prepare(1L, "USD", BigDecimal.TEN);
        exRateProviderStub.setExRate(BigDecimal.valueOf(500));
        //when & then
        // 환율 정보를 가져온다
        assertThat(payment2.getExRate()).isEqualByComparingTo(exRate);

        // 원화환산금액 계산
        assertThat(payment2.getConvertedAmount()).isEqualByComparingTo(expectedAmount);
    }
}