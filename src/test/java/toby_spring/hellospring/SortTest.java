package toby_spring.hellospring;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class SortTest {

    private Sort sort;

    @BeforeEach
    void setUp() {
        sort = new Sort();
    }

    @Test
    public void sort() {

        //when
        List<String> list = sort.sortByLength(Arrays.asList("bb", "a"));

        //then
        Assertions.assertThat(list).isEqualTo(List.of("a", "bb"));
    }

    @Test
    public void sort3Items() {
        //when
        List<String> list = sort.sortByLength(Arrays.asList("bb", "ccc", "a"));

        //then
        Assertions.assertThat(list).isEqualTo(List.of("a", "bb", "ccc"));
    }

    @Test
    public void sortAlreadySorted() {
        //when
        List<String> list = sort.sortByLength(Arrays.asList("a", "bb", "ccc"));

        //then
        Assertions.assertThat(list).isEqualTo(List.of("a", "bb", "ccc"));
    }


}