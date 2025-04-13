
# 🔐 Cryptography Algorithms Collection

This repository contains Java implementations of classical **cryptography algorithms**, categorized into **Substitution Ciphers** and **Transposition Ciphers**. These algorithms are fundamental in understanding the evolution of modern cryptography and are widely used for educational and research purposes.

---

## ✳️ Substitution Ciphers

Substitution ciphers work by replacing elements of the plaintext with corresponding elements of ciphertext based on a certain system.

### ✅ Single Substitution

➡️ In these ciphers, each letter of the plaintext is replaced with another letter consistently throughout the message.

- **Monoalphabetical**
  - 🔸 **Atbash Cipher**: Reverses the alphabet (A ↔ Z, B ↔ Y, etc.).
  - 🔸 **Caesar Cipher**: Shifts each letter by a fixed number of places in the alphabet.
  - 🔸 **Affine Cipher**: Uses mathematical functions `(ax + b) mod 26` to encrypt letters.
  - 🔸 **August Cipher**: A variation of Caesar/Monoalphabetic substitution (implementation included).

---

### ✅ Multiple Substitution

➡️ These are more secure than single substitution ciphers as they use multiple alphabets to encrypt the message.

#### 🔁 Polyalphabetical

- 🔸 **Vigenère Cipher**: Uses a repeated key to shift letters based on the Vigenère table.
- 🔸 **Gronsfeld Cipher**: A numeric variant of the Vigenère cipher, often using digits 0–9.
- 🔸 **Beaufort Cipher**: Similar to Vigenère but uses reversed table logic.
- 🔸 **Auto Key Cipher**: Key is extended using the plaintext itself.
- 🔸 **Running Key Cipher**: Uses a long key like a book or paragraph, making repetition harder.

#### 🔤 Polygraphic

- 🔸 **Hill Cipher**: Uses matrix multiplication and modular arithmetic to encrypt blocks of letters simultaneously.

---

## 🔄 Transpositional Ciphers

These ciphers **rearrange** the characters of the plaintext instead of substituting them.

- 🔸 **Rail Fence Cipher**: Characters are written in a zigzag pattern across multiple "rails" and then read row-wise.
- 🔸 **Route Cipher**: Writes text into a grid and reads it in a specific traversal pattern (e.g., spiral, zigzag).
- 🔸 **Myszkowski Cipher**: A columnar transposition cipher with repeated key characters that control the column reading order.

---

## 📁 Structure

Each algorithm is implemented in a separate Java file with:
- 📥 **User Input**
- 🔐 **Encryption and Decryption**
- 🧪 **Sample Demonstration**
- ✅ **Well-commented code for clarity**

---

## 🚀 How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/MEGARAJAN-S/Cryptography-CIA.git
   cd cryptography-algorithms

---

Megarajan S - IOT A - 22011102054
