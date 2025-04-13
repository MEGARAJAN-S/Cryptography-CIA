import java.util.*;

public class MyszkowskiCipher {

    // Convert key to number pattern
    public static int[] getKeyPattern(String key) {
        int len = key.length();
        int[] pattern = new int[len];
        Character[] keyChars = new Character[len];

        for (int i = 0; i < len; i++) keyChars[i] = key.charAt(i);

        Map<Character, List<Integer>> positions = new TreeMap<>();
        for (int i = 0; i < len; i++) {
            positions.putIfAbsent(keyChars[i], new ArrayList<>());
            positions.get(keyChars[i]).add(i);
        }

        int index = 0;
        for (Map.Entry<Character, List<Integer>> entry : positions.entrySet()) {
            for (int pos : entry.getValue()) {
                pattern[pos] = index;
            }
            index++;
        }

        return pattern;
    }

    // Encrypt using Myszkowski Transposition Cipher
    public static String encrypt(String plaintext, String key) {
        plaintext = plaintext.replaceAll("\\s+", "").toUpperCase();
        key = key.toUpperCase();

        int[] pattern = getKeyPattern(key);
        int cols = key.length();
        int rows = (int) Math.ceil((double) plaintext.length() / cols);
        char[][] matrix = new char[rows][cols];

        int k = 0;
        for (int i = 0; i < rows && k < plaintext.length(); i++) {
            for (int j = 0; j < cols && k < plaintext.length(); j++) {
                matrix[i][j] = plaintext.charAt(k++);
            }
        }

        // Build ciphertext by traversing columns based on key pattern
        StringBuilder ciphertext = new StringBuilder();
        Set<Integer> used = new TreeSet<>();
        for (int val : pattern) used.add(val);

        for (int val : used) {
            for (int j = 0; j < cols; j++) {
                if (pattern[j] == val) {
                    for (int i = 0; i < rows; i++) {
                        if (matrix[i][j] != 0)
                            ciphertext.append(matrix[i][j]);
                    }
                }
            }
        }

        return ciphertext.toString();
    }

    // Decrypt using Myszkowski Transposition Cipher
    public static String decrypt(String ciphertext, String key) {
        ciphertext = ciphertext.replaceAll("\\s+", "").toUpperCase();
        key = key.toUpperCase();

        int[] pattern = getKeyPattern(key);
        int cols = key.length();
        int rows = (int) Math.ceil((double) ciphertext.length() / cols);
        char[][] matrix = new char[rows][cols];

        int[] colLengths = new int[cols];
        int[] patternCount = new int[Arrays.stream(pattern).max().getAsInt() + 1];

        for (int p : pattern) patternCount[p]++;

        int cipherIndex = 0;
        for (int val = 0; val < patternCount.length; val++) {
            for (int j = 0; j < cols; j++) {
                if (pattern[j] == val) {
                    for (int i = 0; i < rows; i++) {
                        if (cipherIndex < ciphertext.length()) {
                            matrix[i][j] = ciphertext.charAt(cipherIndex++);
                        }
                    }
                }
            }
        }

        // Read matrix row-wise
        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] != 0)
                    plaintext.append(matrix[i][j]);
            }
        }

        return plaintext.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter plaintext: ");
        String plaintext = sc.nextLine();

        System.out.print("Enter key (with repeating letters allowed): ");
        String key = sc.nextLine();

        String encrypted = encrypt(plaintext, key);
        String decrypted = decrypt(encrypted, key);

        System.out.println("\nEncrypted Text: " + encrypted);
        System.out.println("Decrypted Text: " + decrypted);
    }
}
