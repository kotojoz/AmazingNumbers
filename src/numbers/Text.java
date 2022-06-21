package numbers;

public enum Text {
    FIRST_PARAMETER_ERROR("The first parameter should be a natural number or zero."),

    SECOND_PARAMETER_ERROR("The second parameter should be a natural number."),

    ENTER("\nEnter a request: "),

    GREETING("Welcome to Amazing Numbers!\n"),

    REQUESTS("Supported requests:\n" +
            "- enter a natural number to know its properties;\n" +
            "- enter two natural numbers to obtain the properties of the list:\n" +
            "  * the first parameter represents a starting number;\n" +
            "  * the second parameter shows how many consecutive numbers are to be printed;\n" +
            "- two natural numbers and properties to search for;\n" +
            "- separate the parameters with one space;\n" +
            "- enter 0 to exit."),

    GOODBYE("\nGoodbye!"),

    DIGITS_ONLY("You can enter only digits."),

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
