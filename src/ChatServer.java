import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    private static int PORT = 5555;
    private MessageEvent messageEvent;

    public ChatServer(MessageEvent messageEvent) {
        if (messageEvent == null){
            throw new RuntimeException("MessageEvent cannot be null");
        }
        this.messageEvent = messageEvent;
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        messageEvent.onServerStarted("Server started");
        new Thread(){
            @Override
            public void run() {
                try {
                    Socket client = serverSocket.accept();
                    messageEvent.onClientConnected("Client connected: " + client.getInetAddress());
                    BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    String line;
                    while((line = reader.readLine()) != null){
                        messageEvent.onMessageReceived(line);
                    }
                    reader.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}