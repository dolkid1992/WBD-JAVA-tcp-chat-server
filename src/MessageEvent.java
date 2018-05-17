public interface MessageEvent {
    void onServerStarted(String message);

    void onClientConnected(String message);

    void onMessageReceived(String message);
}
