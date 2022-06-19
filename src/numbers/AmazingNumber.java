package numbers;

import java.util.ArrayList;
import java.util.List;

public class AmazingNumber {
    private final long number;

    private List<Property> properties;

    public AmazingNumber(long number) {
        this.number = number;
        setProperties(number);
    }

    private void setProperties(long number) {
        properties = new ArrayList<>();
        properties.add(new BuzzNumber(number));
        properties.add(new DuckNumber(number));
        properties.add(new PalindromicNumber(number));
        properties.add(new GapFulNumber(number));
        properties.add(new SpyNumber(number));
        properties.add(new SquareNumber(number));
        properties.add(new SunnyNumber(number));
        properties.add(new EvenNumber(number));
        properties.add(new OddNumber(number));
    }

    public List<Property> getProperties() {
        return properties;
    }

    public String getPropertiesStringLine() {
        StringBuilder properties = new StringBuilder();
        properties.append(number).append(" is ");
        for (Property property : this.properties) {
            if (property.isProperty) {
                properties.append(property.name.toLowerCase()).append(", ");
            }
        }
        return properties.deleteCharAt(properties.length() - 2).toString();
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (Property property : properties) {
            output.append("\n").append(property);
        }
        return "\nProperties of " + number + output;
    }
}
