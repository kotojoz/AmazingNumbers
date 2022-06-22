package numbers;

import java.util.HashMap;
import java.util.Map;

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
    JUMPING(new JumpingNumber()),
    HAPPY(new HappyNumber()),
    SAD(new SadNumber());

    private final Property property;
    private static final Map<String, String[]> exclusiveProperties = setExclusiveProperties();

    Properties(Property property) {
        this.property = property;
    }

    public static Map<String, String[]> setExclusiveProperties() {
        Map<String, String[]> stringMap = new HashMap<>();
        stringMap.put("EVEN", new String[]{"ODD", "-EVEN"});
        stringMap.put("DUCK", new String[]{"SPY", "-DUCK"});
        stringMap.put("SUNNY", new String[]{"SQUARE", "-SUNNY"});
        stringMap.put("HAPPY", new String[]{"SAD", "-HAPPY"});
        stringMap.put("-EVEN", new String[]{"EVEN", "-ODD"});
        stringMap.put("-DUCK", new String[]{"DUCK", "-SPY"});
        stringMap.put("-SUNNY", new String[]{"SUNNY", "-SQUARE"});
        stringMap.put("-HAPPY", new String[]{"HAPPY", "-SAD"});
        stringMap.put("ODD", new String[]{"EVEN", "-ODD"});
        stringMap.put("SPY", new String[]{"DUCK", "-SPY"});
        stringMap.put("SQUARE", new String[]{"SUNNY", "-SQUARE"});
        stringMap.put("SAD", new String[]{"HAPPY", "-SAD"});
        stringMap.put("-ODD", new String[]{"ODD", "-EVEN"});
        stringMap.put("-SPY", new String[]{"SPY", "-DUCK"});
        stringMap.put("-SQUARE", new String[]{"SQUARE", "-SUNNY"});
        stringMap.put("-SAD", new String[]{"SAD", "-HAPPY"});
        return stringMap;
    }

    public boolean isTrue(long number) {
        return property.isTrue(number);
    }

    public static boolean isExclusive(String property1, String property2) {
        if (property1.contains("-") || property2.contains("-")) {
            if (property1.replace("-", "").equals(property2.replace("-", ""))) {
                return true;
            }
        }
        String[] exclProperties = exclusiveProperties.get(property1);
        if (exclProperties != null) {
            for (String prop : exclProperties) {
                if (prop.equals(property2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean noSuchProperty(String property) {
        for (Properties properties : Properties.values()) {
            if (properties.name().equals(property.replace("-", ""))) {
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
