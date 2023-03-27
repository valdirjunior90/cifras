import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Receptor {
    private static final String ALGORITMO = "VIGENERE"; // Define o algoritmo a ser utilizado

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);

            // Aguardando conexão do emissor
            System.out.println("Aguardando conexão...");
            Socket socket = serverSocket.accept();
            System.out.println("Conexão estabelecida!");

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
                if (ALGORITMO.equals("VIGENERE")) {
                    mensagemDecifrada = CifraVigenere.decifrar(mensagemCifrada, chave);
                } else if (ALGORITMO.equals("VERNAM")) {
                    // implementação da cifra de Vernam
                }

                // Exibição da mensagem decifrada
                System.out.println("Mensagem recebida: " + mensagemDecifrada);

                // Envio da resposta cifrada
                System.out.print("Resposta: ");
                Scanner scanner = new Scanner(System.in);
                String resposta = scanner.nextLine();

                // Cifra da resposta com o algoritmo escolhido
                String respostaCifrada = "";
                if (ALGORITMO.equals("VIGENERE")) {
                    respostaCifrada = CifraVigenere.cifrar(resposta, chave);
                } else if (ALGORITMO.equals("VERNAM")) {
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