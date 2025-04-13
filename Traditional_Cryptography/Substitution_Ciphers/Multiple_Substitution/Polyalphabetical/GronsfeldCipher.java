import java.util.Scanner;

public class GronsfeldCipher {

    public static String encrypt(String plaintext, String key) {
        StringBuilder result = new StringBuilder();
        int keyLength = key.length();

        for (int i = 0, j = 0; i < plaintext.length(); i++) {
            char ch = plaintext.charAt(i);
            if (Character.isLetter(ch)) {
                int shift = key.charAt(j % keyLength) - '0';
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                char encrypted = (char) ((ch - base + shift) % 26 + base);
                result.append(encrypted);
                j++;
            } else {
                result.append(ch);
            }
        }

        return result.toString();
    }

    public static String decrypt(String ciphertext, String key) {
        StringBuilder result = new StringBuilder();
        int keyLength = key.length();

        for (int i = 0, j = 0; i < ciphertext.length(); i++) {
            char ch = ciphertext.charAt(i);
            if (Character.isLetter(ch)) {
                int shift = key.charAt(j % keyLength) - '0';
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                char decrypted = (char) ((ch - base - shift + 26) % 26 + base);
                result.append(decrypted);
                j++;
            } else {
                result.append(ch);
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter text: ");
        String text = scanner.nextLine();

        System.out.print("Enter numeric key (digits only): ");
        String key = scanner.nextLine();

        if (!key.matches("\\d+")) {
            System.out.println("Invalid key! Must be digits only.");
            return;
        }

        String encrypted = encrypt(text, key);
        String decrypted = decrypt(encrypted, key);

        System.out.println("\nEncrypted Text: " + encrypted);
        System.out.println("Decrypted Text: " + decrypted);

        scanner.close();
    }
}
