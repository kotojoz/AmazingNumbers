package numbers;

import java.util.HashMap;
import java.util.Map;

public class AmazingNumber {

    private final long number;

    private final Map<String, Boolean> properties;

    public AmazingNumber(long number) {
        this.number = number;
        properties = new HashMap<>();
        setProperties();
    }

    private void setProperties() {
        for (Properties property : Properties.values()) {
            properties.put(property.name().toLowerCase(), property.isTrue(number));
        }
    }


    public boolean haveProperty(String property) {
        return properties.get(property.toLowerCase());
    }

    public boolean haveProperty(String[] input) {
        for (String string : input) {
            if (!properties.get(string.toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    public String getPropertiesAsString() {
        StringBuilder result = new StringBuilder();
        result.append(number).append(" is ");
        for (Map.Entry<String, Boolean> entry : properties.entrySet()) {
            if (entry.getValue()) {
                result.append(entry.getKey().toLowerCase()).append(", ");
            }
        }
        return result.deleteCharAt(result.length() - 2).toString();
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (Map.Entry<String, Boolean> entry : properties.entrySet()) {
            output.append("\n").append(entry.getKey()).append(": ").append(entry.getValue());
        }
        return "\nProperties of " + number + output;
    }
}
