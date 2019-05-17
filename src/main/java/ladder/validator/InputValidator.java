package ladder.validator;

import ladder.domain.Person;
import ladder.util.InputHelper;

import java.util.HashSet;
import java.util.List;

public class InputValidator {
    private static final int MIN_HEIGHT = 1;
    private static final String EXCEPTION_MESSAGE = "양식에 맞게 입력해 주세요.";
    private static final String DUPLICATED_MESSAGE = "중복된 이름은 허용하지 않습니다.";
    private static final String OVER_HEIGHT_MESSAGE = "높이는 1 이상이어야 합니다.";
    private static final String NOT_EQUAL_COUNT_MESSAGE = "결과의 개수는 %d개가 필요합니다.";
    private static final String NOT_ALLOW_ALL_MESSAGE = "이름 all은 허용하지 않습니다.";
    private static final String NOT_CONTAIN_NAME_MESSAGE = "없는 이름입니다.";
    private static final String ALL = "all";

    public static List<String> checkValidName(String inputs) {
        isEmpty(inputs);
        hasSpace(inputs);
        checkLastIndex(inputs);
        List<String> names = InputHelper.splitNames(inputs);
        for (String name : names) {
            isOverMaxInputLimit(name);
            nameEqualAll(name);
        }
        isDuplicate(names);
        return names;
    }

    private static void isEmpty(String inputs) {
        if (inputs == null || inputs.length() == 0) {
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }
    }

    private static void checkLastIndex(String inputs) {
        if (inputs.lastIndexOf(",") == inputs.length() - 1) {
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }
    }

    private static void hasSpace(String inputs) {
        if (inputs.contains(" ")) {
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }
    }

    private static void isOverMaxInputLimit(String input) {
        if (input.length() > 5) {
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }
    }

    private static void nameEqualAll(String name) {
        if (name.equals(ALL)) {
            throw new IllegalArgumentException(NOT_ALLOW_ALL_MESSAGE);
        }
    }

    private static void isDuplicate(List<String> name) {
        HashSet<String> names = new HashSet<>(name);
        if (names.size() != name.size()) {
            throw new IllegalArgumentException(DUPLICATED_MESSAGE);
        }
    }

    public static List<String> checkResult(int countOfPerson, String inputs) {
        isEmpty(inputs);
        hasSpace(inputs);
        checkLastIndex(inputs);
        List<String> results = InputHelper.splitNames(inputs);
        isSameLength(countOfPerson, results);
        for (String result : results) {
            isOverMaxInputLimit(result);
        }
        return results;
    }

    private static void isSameLength(int countOfPerson, List<String> results) {
        if (countOfPerson != results.size()) {
            throw new IllegalArgumentException(String.format(NOT_EQUAL_COUNT_MESSAGE, countOfPerson));
        }
    }

    public static String isNotContainName(Person person, String requestedName) {
        if (!requestedName.equals(ALL) && !person.findName(requestedName)) {
            throw new IllegalArgumentException(NOT_CONTAIN_NAME_MESSAGE);
        }
        return requestedName;
    }

    public static int isLowerLimit(int height) {
        if (height < MIN_HEIGHT) {
            throw new IllegalArgumentException(OVER_HEIGHT_MESSAGE);
        }
        return height;
    }
}



