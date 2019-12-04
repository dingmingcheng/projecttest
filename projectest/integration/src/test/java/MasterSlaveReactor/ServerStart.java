package MasterSlaveReactor;

public class ServerStart {
    public static void main(String[] args) {
        MainReactor mainReactor = new MainReactor();
        new Thread(mainReactor).start();
    }
}
