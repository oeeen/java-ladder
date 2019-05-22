package ladder.view;

import ladder.domain.LadderGameData;
import ladder.domain.Person;
import ladder.validator.InputValidator;

import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String INPUT_NAME_MESSAGE = "참여할 사람 이름을 입력하세요. (이름은 쉼표(,)로 구분하세요)";
    private static final String INPUT_HEIGHT_MESSAGE = "최대 사다리 높이는 몇 개인가요?";
    private static final String INPUT_RESULT_MESSAGE = "실행 결과를 입력하세요. (결과는 쉼표(,)로 구분하세요)";
    private static final String FIND_RESULT_NAME_MESSAGE = "결과를 보고 싶은 사람은?";
    private static final Scanner SCANNER = new Scanner(System.in);

    public static List<String> inputNames() {
        try {
            System.out.println(INPUT_NAME_MESSAGE);
            String inputName = SCANNER.nextLine();
            return InputValidator.checkValidName(inputName);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputNames();
        }
    }

    public static int inputHeight() {
        try {
            System.out.println(INPUT_HEIGHT_MESSAGE);
            int height = Integer.parseInt(SCANNER.nextLine());
            return InputValidator.isLowerLimit(height);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputHeight();
        }

    }

    public static List<String> inputResultAll(int countOfPerson) {
        try {
            System.out.println(INPUT_RESULT_MESSAGE);
            String results = SCANNER.nextLine();
            return InputValidator.checkResult(countOfPerson, results);
        } catch (IllegalArgumentException e) {
            return inputResultAll(countOfPerson);
        }
    }

    public static String findResultName(LadderGameData ladderGameData) {
        try {
            System.out.println(FIND_RESULT_NAME_MESSAGE);
            String requestedName = SCANNER.nextLine();
            return InputValidator.isNotContainName(ladderGameData.getPerson(), requestedName);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return findResultName(ladderGameData);
        }
    }
}
