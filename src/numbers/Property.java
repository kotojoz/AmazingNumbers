package numbers;

import java.util.Arrays;

public abstract class Property {
    public abstract boolean isTrue(long number);
}

class EvenNumber extends Property {

    @Override
    public boolean isTrue(long number) {
        return number % 2 == 0;
    }
}

class OddNumber extends Property {

    @Override
    public boolean isTrue(long number) {
        return number % 2 != 0;
    }
}

class BuzzNumber extends Property {

    @Override
    public boolean isTrue(long number) {
        String str = String.valueOf(number);
        return str.endsWith("7") || number % 7 == 0;
    }
}

class DuckNumber extends Property {

    @Override
    public boolean isTrue(long number) {
        return String.valueOf(number).substring(1).contains("0");
    }
}

class PalindromicNumber extends Property {

    @Override
    public boolean isTrue(long number) {
        String str = String.valueOf(number);
        String strReverse = new StringBuilder(str).reverse().toString();
        return str.equals(strReverse);
    }
}

class GapfulNumber extends Property {

    @Override
    public boolean isTrue(long number) {
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

    @Override
    public boolean isTrue(long number) {
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

    @Override
    public boolean isTrue(long number) {
        long num = (long) Math.sqrt(number + 1);
        return num * num == number + 1;
    }
}

class SquareNumber extends Property {

    @Override
    public boolean isTrue(long number) {
        long num = (long) Math.sqrt(number);
        return num * num == number;
    }
}

class JumpingNumber extends Property {

    @Override
    public boolean isTrue(long number) {
        int[] digits = Arrays.stream(String.valueOf(number).split("")).mapToInt(Integer::parseInt).toArray();
        if (digits.length == 1) {
            return true;
        }
        for (int i = 0; i < digits.length - 1; i++) {
            if (Math.abs(digits[i] - digits[i + 1]) != 1) {
                return false;
            }
        }
        return true;
    }
}