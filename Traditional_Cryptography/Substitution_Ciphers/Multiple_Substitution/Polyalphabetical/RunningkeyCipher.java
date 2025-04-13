import java.util.Scanner;

public class RunningkeyCipher {

    // Encrypt function
    public static String encrypt(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder();
        int keyIndex = 0;

        for (char p : plaintext.toCharArray()) {
            if (Character.isLetter(p)) {
                char k = key.charAt(keyIndex);
                int shift = Character.toLowerCase(k) - 'a';

                if (Character.isUpperCase(p)) {
                    ciphertext.append((char) ((p - 'A' + shift) % 26 + 'A'));
                } else {
                    ciphertext.append((char) ((p - 'a' + shift) % 26 + 'a'));
                }

                keyIndex++;
            } else {
                ciphertext.append(p); // preserve spaces, punctuation
            }
        }

        return ciphertext.toString();
    }

    // Decrypt function
    public static String decrypt(String ciphertext, String key) {
        StringBuilder plaintext = new StringBuilder();
        int keyIndex = 0;

        for (char c : ciphertext.toCharArray()) {
            if (Character.isLetter(c)) {
                char k = key.charAt(keyIndex);
                int shift = Character.toLowerCase(k) - 'a';

                if (Character.isUpperCase(c)) {
                    plaintext.append((char) ((c - 'A' - shift + 26) % 26 + 'A'));
                } else {
                    plaintext.append((char) ((c - 'a' - shift + 26) % 26 + 'a'));
                }

                keyIndex++;
            } else {
                plaintext.append(c);
            }
        }

        return plaintext.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the plaintext: ");
        String plaintext = scanner.nextLine();

        System.out.print("Enter the running key (must be at least as long as plaintext letters): ");
        String key = scanner.nextLine().replaceAll("[^a-zA-Z]", "");

        // Count only letters in plaintext
        long letterCount = plaintext.chars().filter(Character::isLetter).count();

        if (key.length() < letterCount) {
            System.out.println("Error: Key must be at least as long as the number of letters in the plaintext.");
            return;
        }

        String encrypted = encrypt(plaintext, key);
        String decrypted = decrypt(encrypted, key);

        System.out.println("\nEncrypted Text: " + encrypted);
        System.out.println("Decrypted Text: " + decrypted);

        scanner.close();
    }
}
