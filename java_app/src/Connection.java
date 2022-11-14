import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connection {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String name;

    public Connection(String ip, int port, String name) throws IOException {
        this.name = name;
        System.out.println("[Conectando socket...]");
        this.socket = new Socket(ip, port);

        in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        out = new PrintWriter(this.socket.getOutputStream(), true);
        System.out.println("[Conectado com sucesso]");
    }

    /**
     * Método que escreve e recebe mensagem no socket (String)
     */
    public String sendMessage(String message) {
        try {
            // escreve no socket e lê a mensagem de resposta
            out.println(name + ": " + message);
            return in.readLine();
        } catch (IOException e) {
            System.out.println("-> Houve um erro de conexão com o servidor");
        }

        return Utils.SAIR;
    }
}
