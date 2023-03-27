import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Emissor {
    // private static final String ALGORITMO = "VIGENERE"; // Define o algoritmo a ser utilizado
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        System.out.println("Digite o algoritmo a ser utilizado: VIGENERE ou VERNAM");
        String algoritmo = scanner.nextLine();
        // Verifica se o algoritmo escolhido é válido
        switch(algoritmo) {
            case "VIGENERE":
                System.out.println("Algoritmo de Vigenère");
                break;
            case "VERNAM":
                System.out.println("Algoritmo de Vernam");
                break;
            default:
                System.out.println("Algoritmo inválido");
                return;
        }
        try {
            // Conexão com o receptor
            Socket socket = new Socket("localhost", 12345);
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            // Chave para cifra de Vigenère
            String chave = "chave";

            while (true) {
                // Leitura da mensagem a ser enviada
                System.out.print("Mensagem: ");
                String mensagem = scanner.nextLine();

                // Cifra da mensagem com o algoritmo escolhido
                String mensagemCifrada = "";
                if (algoritmo.equals("VIGENERE")) {
                    mensagemCifrada = CifraVigenere.cifrar(mensagem, chave);
                } else if (algoritmo.equals("VERNAM")) {
                    // mensagemCifrada = CifraVernam.cifrar(mensagem, chave);
                }

                // Envio da mensagem cifrada
                output.writeObject(mensagemCifrada);
                output.flush();

                // Recebimento da resposta do receptor
                String resposta = (String) input.readObject();

                // Decifração da mensagem recebida
                String respostaDecifrada = "";
                if (algoritmo.equals("VIGENERE")) {
                    respostaDecifrada = CifraVigenere.decifrar(resposta, chave);
                } else if (algoritmo.equals("VERNAM")) {
                    // respostaDecifrada = CifraVernam.decifrar(resposta, chave);
                }

                // Exibição da resposta decifrada
                System.out.println("Resposta: " + respostaDecifrada);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

