import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Receptor {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);

            // Aguardando conexão do emissor
            System.out.println("Aguardando conexão...");
            Socket socket = serverSocket.accept();
            System.out.println("Conexão estabelecida!");

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

            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());

            // Chave para cifra de Vigenère
            String chave = "chave";

            while (true) {
                // Recebimento da mensagem cifrada do emissor
                String mensagemCifrada = (String) input.readObject();
                System.out.println("Mensagem cifrada recebida: " + mensagemCifrada);
                // Decifração da mensagem recebida
                String mensagemDecifrada = "";
                if (algoritmo.equals("VIGENERE")) {
                    mensagemDecifrada = CifraVigenere.decifrar(mensagemCifrada, chave);
                } else if (algoritmo.equals("VERNAM")) {
                    mensagemDecifrada = CifraVernam.decifrar(mensagemCifrada, chave);
                }

                // Exibição da mensagem decifrada
                System.out.println("Mensagem recebida: " + mensagemDecifrada);

                // Envio da resposta cifrada
                System.out.print("Resposta: ");
                Scanner scanner = new Scanner(System.in);
                String resposta = scanner.nextLine();

                // Cifra da resposta com o algoritmo escolhido
                String respostaCifrada = "";
                if (algoritmo.equals("VIGENERE")) {
                    respostaCifrada = CifraVigenere.cifrar(resposta, chave);
                } else if (algoritmo.equals("VERNAM")) {
                    // implementação da cifra de Vernam
                }

                // Envio da resposta cifrada para o emissor
                output.writeObject(respostaCifrada);
                output.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}