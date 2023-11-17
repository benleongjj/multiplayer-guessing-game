import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerProgram {
    private static final int port = 7000;
    private static final Game game = new Game();

    private static void RunServer()  {
        ServerSocket serverSocket = null;
        try {

            //Create socket with port 7000
            serverSocket = new ServerSocket(port);
            System.out.println("Waiting for players...");
            //Listen for player connections
            while (true) {
                Socket socket = serverSocket.accept();

                //Upon player connection,a new client handler is created
                new Thread(new ClientHandler(socket, game)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        RunServer();
    }

}
