package bench.cpu;

public class Hashmanager {
    private final String alphabet = "abcdefghijklmnopqrstuvwxyz";

    public String hash(String text) {
        return Integer.toString(text.hashCode());
    }

    public String getNextString(String current) {
        char[] chars = current.toCharArray();
        int length = chars.length;
        int base = alphabet.length();

        for (int i = length - 1; i >= 0; i--) {
            int index = alphabet.indexOf(chars[i]);
            if (index < base - 1) {
                chars[i] = alphabet.charAt(index + 1);
                return new String(chars);
            } else {
                chars[i] = alphabet.charAt(0);
            }
        }

        char[] newChars = new char[length + 1];
        for (int j = 0; j < newChars.length; j++) {
            newChars[j] = alphabet.charAt(0);
        }
        return new String(newChars);
    }
}
