import java.util.Scanner;

public class AffineCipher {

    // Function to calculate modular inverse of a under modulo m
    public static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) return x;
        }
        throw new IllegalArgumentException("Inverse doesn't exist; 'a' and 26 must be coprime.");
    }

    // Function to encrypt plaintext
    public static String encrypt(String plaintext, int a, int b) {
        StringBuilder cipher = new StringBuilder();

        for (char ch : plaintext.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                char encryptedChar = (char) (((a * (ch - base) + b) % 26) + base);
                cipher.append(encryptedChar);
            } else {
                cipher.append(ch); // Keep non-alphabet characters as is
            }
        }

        return cipher.toString();
    }

    // Function to decrypt ciphertext
    public static String decrypt(String ciphertext, int a, int b) {
        int a_inv = modInverse(a, 26);
        StringBuilder plain = new StringBuilder();

        for (char ch : ciphertext.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                char decryptedChar = (char) (((a_inv * ((ch - base - b + 26)) % 26) + base));
                plain.append(decryptedChar);
            } else {
                plain.append(ch);
            }
        }

        return plain.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the text: ");
        String input = scanner.nextLine();

        System.out.print("Enter key 'a' (must be coprime with 26): ");
        int a = scanner.nextInt();

        System.out.print("Enter key 'b': ");
        int b = scanner.nextInt();

        // Check if 'a' is coprime with 26
        if (gcd(a, 26) != 1) {
            System.out.println("Error: 'a' must be coprime with 26.");
            return;
        }

        String encrypted = encrypt(input, a, b);
        String decrypted = decrypt(encrypted, a, b);

        System.out.println("\nEncrypted Text: " + encrypted);
        System.out.println("Decrypted Text: " + decrypted);

        scanner.close();
    }

    // Helper function to calculate gcd
    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
