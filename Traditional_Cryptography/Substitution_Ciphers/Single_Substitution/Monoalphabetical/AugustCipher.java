import java.util.*;

public class AugustCipher {

    // Simple monoalphabetic cipher map (custom mapping)
    static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static String substitution = "QWERTYUIOPASDFGHJKLZXCVBNM"; // example mapping

    public static String encrypt(String plaintext) {
        plaintext = plaintext.toUpperCase();
        StringBuilder cipher = new StringBuilder();

        for (char ch : plaintext.toCharArray()) {
            if (Character.isLetter(ch)) {
                int index = alphabet.indexOf(ch);
                cipher.append(substitution.charAt(index));
            } else {
                cipher.append(ch); // non-letters unchanged
            }
        }
        return cipher.toString();
    }

    public static String decrypt(String ciphertext) {
        ciphertext = ciphertext.toUpperCase();
        StringBuilder plain = new StringBuilder();

        for (char ch : ciphertext.toCharArray()) {
            if (Character.isLetter(ch)) {
                int index = substitution.indexOf(ch);
                plain.append(alphabet.charAt(index));
            } else {
                plain.append(ch);
            }
        }
        return plain.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter plaintext: ");
        String plaintext = sc.nextLine();

        String encrypted = encrypt(plaintext);
        String decrypted = decrypt(encrypted);

        System.out.println("\nEncrypted Text: " + encrypted);
        System.out.println("Decrypted Text: " + decrypted);

        sc.close();
    }
}
