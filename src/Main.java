import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer(new MessageEventImpl());

        try {
            chatServer.start();
        } catch (IOException e) {
            System.out.println("Error starting server");
        }
    }

    static class MessageEventImpl implements MessageEvent{
        @Override
        public void onServerStarted(String message) {
            System.out.println(message);
        }

        @Override
        public void onClientConnected(String message) {
            System.out.println(message);
        }

        @Override
        public void onMessageReceived(String message) {
            System.out.println(message);
        }
    }
}