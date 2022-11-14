import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {

    public static void main(String[] args) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Informe seu nome: ");
            String name = bufferedReader.readLine();

            // instancia classe
            Connection client = new Connection(Utils.IP_SERVER, Utils.PORT_SERVER, name);

            // escreve e recebe mensagem
            System.out.print("> Envie a sua mensagem: ");
            String message = bufferedReader.readLine();

            while (!message.equalsIgnoreCase(Utils.SAIR)) {
                String response = client.sendMessage(message);
                if (response.equalsIgnoreCase(Utils.SAIR)) {
                    System.out.println("O outro usuário se desconectou do chat.");
                    break;
                }
                System.out.println("> Recebido: " + response);

                System.out.print("> Envie a sua mensagem: ");
                message = bufferedReader.readLine();
            }
            System.out.println("-> Saindo do chat");
            System.exit(0);
        } catch (Exception e) {
            System.out.println("-> Houve um erro de conexão com o servidor");
            System.exit(1);
        }
    }
}