package numbers;

import static numbers.Properties.*;

public abstract class Property {

    protected boolean isProperty;

    protected String name;

    public Property(long number) {
        isProperty = setProperty(number);
    }

    abstract boolean setProperty(long number);

    protected boolean sameProperty(String name) {
        return this.name.equals(name) && isProperty;
    }

    @Override
    public String toString() {
        return name.toLowerCase() + ": " + isProperty;
    }

}

class EvenNumber extends Property {

    public EvenNumber(long number) {
        super(number);
        name = EVEN.name();
    }

    @Override
    protected boolean setProperty(long number) {
        return number % 2 == 0;
    }
}

class OddNumber extends Property {

    public OddNumber(long number) {
        super(number);
        name = ODD.name();
    }

    @Override
    protected boolean setProperty(long number) {
        return number % 2 != 0;
    }
}

class BuzzNumber extends Property {
    public BuzzNumber(long number) {
        super(number);
        name = BUZZ.name();
    }

    @Override
    protected boolean setProperty(long number) {
        String str = String.valueOf(number);
        return str.endsWith("7") || number % 7 == 0;
    }
}

class DuckNumber extends Property {

    public DuckNumber(long number) {
        super(number);
        name = DUCK.name();
    }

    @Override
    protected boolean setProperty(long number) {
        return String.valueOf(number).substring(1).contains("0");
    }
}

class PalindromicNumber extends Property {

    public PalindromicNumber(long number) {
        super(number);
        name = PALINDROMIC.name();
    }

    @Override
    protected boolean setProperty(long number) {
        String str = String.valueOf(number);
        String strReverse = new StringBuilder(str).reverse().toString();
        return str.equals(strReverse);
    }

}

class GapFulNumber extends Property {

    public GapFulNumber(long number) {
        super(number);
        name = GAPFUL.name();
    }

    @Override
    protected boolean setProperty(long number) {
        StringBuilder numbers = new StringBuilder(String.valueOf(number));
        if (numbers.length() > 2) {
            numbers.delete(1, numbers.length() - 1);
            long divider = Long.parseLong(numbers.toString());
            return number % divider == 0;
        }
        return false;
    }
}

class SpyNumber extends Property {

    public SpyNumber(long number) {
        super(number);
        name = SPY.name();
    }

    @Override
    protected boolean setProperty(long number) {
        String[] numbers = String.valueOf(number).split("");
        long sum = 0;
        long prod = 1;
        for (String s : numbers) {
            sum += Integer.parseInt(s);
            prod *= Integer.parseInt(s);
        }
        return sum == prod;
    }
}

class SunnyNumber extends Property {
    public SunnyNumber(long number) {
        super(number);
        name = SUNNY.name();
    }

    @Override
    boolean setProperty(long number) {
        long num = (long) Math.sqrt(number + 1);
        return num * num == number + 1;
    }
}

class SquareNumber extends Property {
    public SquareNumber(long number) {
        super(number);
        name = SQUARE.name();
    }

    @Override
    boolean setProperty(long number) {
        long num = (long) Math.sqrt(number);
        return num * num == number;
    }
}

