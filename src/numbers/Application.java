package numbers;

import java.util.Scanner;

import static numbers.Text.*;

public class Application {

    public static Scanner scanner = new Scanner(System.in);

    public String[] enterCommand() {
        while (true) {
            System.out.print(ENTER);
            String[] input = scanner.nextLine().toUpperCase().split(" ");
            try {
                checkInput(input);
                return input;
            } catch (NumberFormatException e) {
                System.out.println(DIGITS_ONLY);
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void checkInput(String[] input) {
        try {
            if (input.length > 12) {
                throw new RuntimeException(UNKNOWN_COMMAND.text);
            }
            checkNumbers(input);
            if (input.length == 3) {
                checkProperties(input[2]);
            } else {
                checkProperties(input);
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
    }

    private void checkNumbers(String[] input) {
        long number = Long.parseLong(input[0]);
        if (number < 0) {
            throw new RuntimeException(FIRST_PARAMETER_ERROR.text);
        }
        long count = Long.parseLong(input[1]);
        if (count < 0) {
            throw new RuntimeException(SECOND_PARAMETER_ERROR.text);
        }
    }

    private boolean noSuchProperty(String property) {
        for (Properties properties : Properties.values()) {
            if (properties.name().equals(property)) {
                return false;
            }
        }
        return true;
    }

    private void checkProperties(String property) {
        if (noSuchProperty(property)) {
            throw new RuntimeException("\nThe property [" + property + "] is wrong.\n" +
                    "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING]");
        }
    }

    private void checkProperties(String[] input) {
        availableProperties(input);
        isExclusiveProperties(input);
    }

    private void availableProperties(String[] input) {
        StringBuilder wrongProperties = new StringBuilder();
        for (int i = 2; i < input.length; i++) {
            if (noSuchProperty(input[i])) {
                wrongProperties.append(input[i]).append(", ");
            }
        }
        if (wrongProperties.length() > 0) {
            wrongProperties.delete(wrongProperties.length() - 2, wrongProperties.length());
            throw new RuntimeException("The properties [" + wrongProperties + "] are wrong\n" +
                    "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING]");
        }
    }

    private void isExclusiveProperties(String[] input) {
        for (int i = 2; i < input.length - 1; i++) {
            for (int j = i + 1; j < input.length; j++) {
                if (Properties.isExclusive(input[i], input[j])) {
                    throw new RuntimeException("The request contains mutually exclusive properties: ["
                            + input[i] + ", " + input[j] + "]\n" +
                            "There are no numbers with these properties.");
                }
            }
        }

    }

    public void getNumbers(long number) {
        AmazingNumber amazingNumber = new AmazingNumber(number);
        System.out.println(amazingNumber);
    }

    public void getNumbers(String[] numbers) {
        System.out.print("\n");
        long number = Long.parseLong(numbers[0]);
        int count = Integer.parseInt(numbers[1]);
        for (int i = 0; i < count; i++) {
            AmazingNumber amazingNumber = new AmazingNumber(number + i);
            System.out.println(amazingNumber.getPropertiesAsString());
        }
    }

    public void getNumbersWithProperties(String[] input) {
        System.out.print("\n");
        long number = Long.parseLong(input[0]);
        int count = Integer.parseInt(input[1]);
        String[] prop = new String[input.length - 2];
        System.arraycopy(input, 2, prop, 0, input.length - 2);
        while (count > 0) {
            AmazingNumber amazingNumber = new AmazingNumber(number++);
            if (amazingNumber.haveProperty(prop)) {
                System.out.println(amazingNumber.getPropertiesAsString());
                count--;
            }
        }
    }


    public void start() {
        System.out.println(Text.GREETING);
        System.out.println(REQUESTS);

        while (true) {
            String[] input = enterCommand();
            long request = Long.parseLong(input[0]);

            if (request > 0 && input.length == 1) {
                getNumbers(request);
            } else if (request > 0 && input.length == 2) {
                getNumbers(input);
            } else if (request > 0) {
                getNumbersWithProperties(input);
            } else if (request == 0) {
                break;
            }
        }
        System.out.println(GOODBYE);
    }
}
