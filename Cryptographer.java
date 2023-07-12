public class Cryptographer {
    private static String alphabet = "abcdefghijklmnopqrstuvwxyz";

    public static String encrypt(String string) {
        int[] numbers = getLetterNumbers(string);
        numbers[0] += 5;
        for (int i = 1; i < numbers.length; i++) {
            numbers[i] += numbers[i-1];
        }
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = numbers[i] < 25 ? numbers[i] : numbers[i] % 26;
        }
        return getStringByLetterNumbers(numbers);
    }

    public static String decrypt(String encryptedString) {
        int[] numbers = getLetterNumbers(encryptedString);
        for (int i = 1; i < numbers.length; i++) {
            numbers[i] = getNextNumberForDecrypting(numbers[i-1], numbers[i]);
        }
        for (int i = numbers.length-1; i > 0; i--) {
            numbers[i] -= numbers[i-1];
        }
        numbers[0] -= 5;
        return getStringByLetterNumbers(numbers);
    }

    private static int[] getLetterNumbers(String string) {
        string = string.toLowerCase();
        int[] numbers = new int[string.length()];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = alphabet.indexOf(string.charAt(i));
        }
        return numbers;
    }

    private static int getNextNumberForDecrypting(int previous, int next) {
        int addendum = 26 * (previous/26);
        return (next + addendum < previous) ? (next + 26 + addendum) : next + addendum;
    }

    private static String getStringByLetterNumbers(int[] numbers) {
        String string = "";
        for (int number : numbers) {
            string += alphabet.charAt(number);
        }
        return string;
    }
}
