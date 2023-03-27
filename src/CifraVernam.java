public class CifraVernam {

    // Método para criptografar a mensagem
    public static String cifrar(String message, String key) {
        String encrypted = "";
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            char k = key.charAt(i);
            encrypted += (char) (c ^ k); // operação XOR
        }
        return encrypted;
    }

    // Método para descriptografar a mensagem
    public static String decifrar(String encrypted, String key) {
        String decrypted = "";
        for (int i = 0; i < encrypted.length(); i++) {
            char c = encrypted.charAt(i);
            char k = key.charAt(i);
            decrypted += (char) (c ^ k); // operação XOR
        }
        return decrypted;
    }

}