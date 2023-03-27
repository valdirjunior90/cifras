public class CifraVigenere {
    public static String cifrar(String mensagem, String chave) {
        StringBuilder cifra = new StringBuilder();
        int j = 0;
        for (int i = 0; i < mensagem.length(); i++) {
            char c = mensagem.charAt(i);
            if (c >= 'a' && c <= 'z') {
                c = (char) ((c + chave.charAt(j) - 2 * 'a') % 26 + 'a');
                j = ++j % chave.length();
            } else if (c >= 'A' && c <= 'Z') {
                c = (char) ((c + chave.charAt(j) - 2 * 'A') % 26 + 'A');
                j = ++j % chave.length();
            }
            cifra.append(c);
        }
        System.out.println("Mensagem cifrada: " + cifra.toString());
        return cifra.toString();
    }

    public static String decifrar(String cifra, String chave) {
        StringBuilder mensagem = new StringBuilder();
        int j = 0;
        for (int i = 0; i < cifra.length(); i++) {
            char c = cifra.charAt(i);
            if (c >= 'a' && c <= 'z') {
                c = (char) ((c - chave.charAt(j) + 26) % 26 + 'a');
                j = ++j % chave.length();
            } else if (c >= 'A' && c <= 'Z') {
                c = (char) ((c - chave.charAt(j) + 26) % 26 + 'A');
                j = ++j % chave.length();
            }
            mensagem.append(c);
        }
        return mensagem.toString();
    }
}