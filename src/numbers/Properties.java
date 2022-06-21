package numbers;

public enum Properties {
    EVEN(new EvenNumber()),
    ODD(new OddNumber()),
    BUZZ(new BuzzNumber()),
    DUCK(new DuckNumber()),
    PALINDROMIC(new PalindromicNumber()),
    GAPFUL(new GapfulNumber()),
    SPY(new SpyNumber()),
    SUNNY(new SunnyNumber()),
    SQUARE(new SquareNumber()),
    JUMPING(new JumpingNumber());

    private final Property property;

    Properties(Property property) {
        this.property = property;
    }

    public boolean isTrue(long number) {
        return property.isTrue(number);
    }

    public static boolean isExclusive(String property1, String property2) {
        return property1.equals("EVEN") && property2.equals("ODD")
                || property2.equals("EVEN") && property1.equals("ODD")
                || property1.equals("DUCK") && property2.equals("SPY")
                || property2.equals("DUCK") && property1.equals("SPY")
                || property1.equals("SUNNY") && property2.equals("SQUARE")
                || property2.equals("SUNNY") && property1.equals("SQUARE");
    }

    public static boolean noSuchProperty(String property) {
        for (Properties properties : Properties.values()) {
            if (properties.name().equals(property)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return name();
    }
}
