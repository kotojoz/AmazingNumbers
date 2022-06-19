package numbers;

public enum Text {
    FIRST_PARAMETER_ERROR("The first parameter should be a natural number or zero.\n"),

    SECOND_PARAMETER_ERROR("The second parameter should be a natural number.\n"),

    ENTER("\nEnter a request: "),

    GREETING("Welcome to Amazing Numbers!\n"),

    REQUESTS("""
            Supported requests:
            - enter a natural number to know its properties;
            - enter two natural numbers to obtain the properties of the list:
              * the first parameter represents a starting number;
              * the second parameters show how many consecutive numbers are to be processed;
            - two natural numbers and two properties to search for;
            - separate the parameters with one space;
            - enter 0 to exit."""),

    GOODBYE("\nGoodbye!"),

    DIGITS_ONLY("You can enter only digits.\n"),

    UNKNOWN_COMMAND("Unknown command");


    final String text;

    Text(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
