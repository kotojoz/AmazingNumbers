package numbers;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
            if (input.length == 0 || input.length > 4) {
                throw new RuntimeException(UNKNOWN_COMMAND.text);
            }
            checkNumbers(input);
            if (input.length == 3) {
                checkProperties(input[2]);
            } else {
                checkProperties(input[2], input[3]);
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

    private void checkProperties(String property) {
        if (noSuchProperty(property)) {
            throw new RuntimeException("\nThe property [" + property + "] is wrong.\n" +
                    "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY]");
        }
    }

    private void checkProperties(String property1, String property2) {
        noSuchProperties(property1, property2);
        isExclusiveProperties(property1, property2);
        checkProperties(property1);
        checkProperties(property2);
    }

    private boolean noSuchProperty(String property) {
        return Arrays.stream(Properties.values()).
                noneMatch(properties -> properties.name().equals(property));
    }

    private void noSuchProperties(String property1, String property2) {
        boolean noProperty1 = noSuchProperty(property1);
        boolean noProperty2 = noSuchProperty(property2);
        if (noProperty1 && noProperty2) {
            throw new RuntimeException("The properties ... are wrong\n" +
                    "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY]");
        }
    }

    private void isExclusiveProperties(String property1, String property2) {
        boolean isExclusive = property1.equals("EVEN") && property2.equals("ODD")
                || property2.equals("EVEN") && property1.equals("ODD")
                || property1.equals("DUCK") && property2.equals("SPY")
                || property2.equals("DUCK") && property1.equals("SPY")
                || property1.equals("SUNNY") && property2.equals("SQUARE")
                || property2.equals("SUNNY") && property1.equals("SQUARE");
        if (isExclusive) {
            throw new RuntimeException("The request contains mutually exclusive properties: ["
                    + property1 + ", " + property2 + "]\n" +
                    "There are no numbers with these properties.");
        }
    }

    public void getNumbers(long number) {
        AmazingNumber amazingNumber = new AmazingNumber(number);
        System.out.println(amazingNumber);
    }

    public void getNumbers(String[] numbers) {
        System.out.print("\n");
        long number = Long.parseLong(numbers[0]);
        long count = Long.parseLong(numbers[1]);
        for (int i = 0; i < count; i++) {
            AmazingNumber amazingNumber = new AmazingNumber(number + i);
            System.out.println(amazingNumber.getPropertiesStringLine());
        }
        System.out.print("\n");
    }

    public void getNumbersWithProperty(String[] input) {
        System.out.print("\n");
        long number = Long.parseLong(input[0]);
        long count = Long.parseLong(input[1]);
        String property = input[2];
        while (count > 0) {
            AmazingNumber amazingNumber = new AmazingNumber(number);
            number++;
            List<Property> props = amazingNumber.getProperties().stream().
                    filter(prop -> prop.sameProperty(property)).collect(Collectors.toList());
            if (props.size() > 0) {
                System.out.println(amazingNumber.getPropertiesStringLine());
                count--;
            }
        }
    }

    public void getNumbersWithTwoProperty(String[] input) {
        System.out.print("\n");
        long number = Long.parseLong(input[0]);
        long count = Long.parseLong(input[1]);
        String property = input[2];
        String prop2 = input[3];
        while (count > 0) {
            AmazingNumber amazingNumber = new AmazingNumber(number);
            number++;
            List<Property> props1 = amazingNumber.getProperties().stream().
                    filter(prop -> prop.sameProperty(property)).collect(Collectors.toList());
            List<Property> props2 = amazingNumber.getProperties().stream().
                    filter(prop -> prop.sameProperty(prop2)).collect(Collectors.toList());
            if (props1.size() > 0 && props2.size() > 0) {
                System.out.println(amazingNumber.getPropertiesStringLine());
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
            } else if (request > 0 && input.length == 3) {
                getNumbersWithProperty(input);
            } else if (request > 0 && input.length == 4) {
                getNumbersWithTwoProperty(input);
            } else if (request == 0) {
                break;
            }
        }
        System.out.println(GOODBYE);
    }
}
