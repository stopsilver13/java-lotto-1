package lotto.domain;

import lotto.exception.InvalidLottoNumbersException;

import java.util.*;

import static lotto.domain.Lotto.NUMBER_OF_NUMBERS_IN_LOTTO;

public class LottoNumber {
    private static final int MIN_LOTTO_NUMBER = 1;
    private static final int MAX_LOTTO_NUMBER = 45;

    private static final Map<Integer, LottoNumber> numbers = new HashMap<>();

    private final int number;

    static {
        for (int i = MIN_LOTTO_NUMBER; i <= MAX_LOTTO_NUMBER; i++) {
            numbers.put(i, new LottoNumber(i));
        }
    }

    private LottoNumber(int number) {
        this.number = number;
    }

    public static LottoNumber valueOf(int number) {
        return numbers.get(number);
    }

    public static List<LottoNumber> getLottoNumbers() {
        List<LottoNumber> numberList = new ArrayList<>(numbers.values());

        Collections.shuffle(numberList);
        return numberList.subList(0, NUMBER_OF_NUMBERS_IN_LOTTO);
    }

    public static List<LottoNumber> getLottoNumbers(List<Integer> inputNumbers) {
        checkOverlab(inputNumbers);
        List<LottoNumber> numberList = new ArrayList<>();

        for (int number : inputNumbers) {
            checkNumberIn(number);
            numberList.add(numbers.get(number));
        }
        return numberList;
    }

    private static void checkOverlab(List<Integer> inputNumbers) {
        if (inputNumbers.size() != new HashSet<>(inputNumbers).size()) {
            throw new InvalidLottoNumbersException("한 로또 숫자들 내 중복된 숫자가 있습니다.");
        }
    }

    private static void checkNumberIn(int number) {
        if (numbers.get(number) == null) {
            throw new InvalidLottoNumbersException("로또에 사용되는 숫자가 아닌 값이 포함되어 있습니다.");
        }
    }
}
