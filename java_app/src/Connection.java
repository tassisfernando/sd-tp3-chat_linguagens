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

    public Connection(String ip, int port) throws IOException {
        System.out.println("[Conectando socket...]");
        this.socket = new Socket(ip, port);

        in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        out = new PrintWriter(this.socket.getOutputStream(), true);
    }

    // escreve e recebe mensagem full no socket (String)
    public String sendMessage(String message) {
        try {
            // escreve str no socket e lêr
            out.println(message);
            return in.readLine();
        } catch (IOException e) {
            System.out.println("-> Houve um erro de conexão com o servidor");
        }

        return Utils.SAIR;
    }
}
