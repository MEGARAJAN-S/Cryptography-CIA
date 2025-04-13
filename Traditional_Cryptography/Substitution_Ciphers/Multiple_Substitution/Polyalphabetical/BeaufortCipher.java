import java.util.Scanner;

public class BeaufortCipher {

    // Generate repeating key
    public static String generateKey(String text, String key) {
        StringBuilder fullKey = new StringBuilder();
        key = key.toLowerCase();
        int keyIndex = 0;

        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                fullKey.append(key.charAt(keyIndex % key.length()));
                keyIndex++;
            } else {
                fullKey.append(ch);
            }
        }

        return fullKey.toString();
    }

    // Beaufort Cipher (encryption and decryption are same)
    public static String beaufortCipher(String text, String key) {
        StringBuilder result = new StringBuilder();
        key = generateKey(text, key);

        for (int i = 0; i < text.length(); i++) {
            char t = text.charAt(i);
            char k = key.charAt(i);

            if (Character.isUpperCase(t)) {
                char ch = (char) (((k - 'a' - (t - 'A') + 26) % 26) + 'A');
                result.append(ch);
            } else if (Character.isLowerCase(t)) {
                char ch = (char) (((k - 'a' - (t - 'a') + 26) % 26) + 'a');
                result.append(ch);
            } else {
                result.append(t); // Keep symbols unchanged
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the text: ");
        String text = scanner.nextLine();

        System.out.print("Enter the key: ");
        String key = scanner.nextLine();

        String encrypted = beaufortCipher(text, key);
        String decrypted = beaufortCipher(encrypted, key); // Same function

        System.out.println("\nEncrypted Text: " + encrypted);
        System.out.println("Decrypted Text: " + decrypted);

        scanner.close();
    }
}
