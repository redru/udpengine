import updengine.server.Server;

import java.net.SocketException;

public class UdpEngineServer {

    public static void main(String[] args) throws SocketException {
        new Server(4445).start();
    }

}
