package toby_spring.hellospring.inflearn.order;

import java.math.BigDecimal;

public record OrderReq(String no,
                       BigDecimal total) {
}
