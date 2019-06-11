package lotto.domain.lotto;

import lotto.exception.InvalidInputException;
import lotto.exception.InvalidLottoNumbersException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LottoFactoryTest {
    @Test
    void 자동_로또_입력한_갯수만큼_로또가_생성되는지_확인() {
        assertThat(LottoFactory.createAutoLottos(3).size()).isEqualTo(3);
    }

    @Test
    void 수동_로또_입력값에_숫자가_아닌_값이_포함된_경우_예외_반환() {
        List<String> inputs = Arrays.asList("1, 2, 3, 4, 5, 6", "11, 12, 13, 14, 15, a");
        assertThrows(InvalidInputException.class,
                () -> LottoFactory.createManualLottos(2, inputs));
    }

    @Test
    void 수동_로또_입력한_갯수만큼_로또가_생성되는지_확인() {
        List<String> inputs = Arrays.asList("1, 2, 3, 4, 5, 6", "11, 12, 13, 14, 15, 16");
        assertThat(LottoFactory.createManualLottos(2, inputs).size()).isEqualTo(2);
    }

    @Test
    void 수동_로또_입력한_갯수와_입력한_로또_번호_수가_다를_경우_예외_반환() {
        List<String> inputs = Arrays.asList("1, 2, 3, 4, 5, 6", "11, 12, 13, 14, 15, 16");
        assertThrows(InvalidLottoNumbersException.class,
                () -> LottoFactory.createManualLottos(3, inputs));
    }
}
