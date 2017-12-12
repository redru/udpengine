package updengine.server;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server extends Thread {

    private DatagramSocket socket;
    private boolean running;
    private byte[] buf = new byte[256];

    public Server() throws SocketException {
        this(4445);
    }

    public Server(int port) throws SocketException {
        socket = new DatagramSocket(port);
    }

    public void run() {
        running = true;

        while (running) {
            GamePacket packet = new GamePacket(buf);

            try {
                socket.receive(packet.getDatagramPacket());
            } catch (IOException e) {
                e.printStackTrace();
            }

            String received = new String(packet.getDatagramPacket().getData(), 0, packet.getDatagramPacket().getLength());
            System.out.println(received);

            if (received.equals("end")) {
                running = false;
                continue;
            }

            try {
                socket.send(packet.getDatagramPacket());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        socket.close();
    }

}