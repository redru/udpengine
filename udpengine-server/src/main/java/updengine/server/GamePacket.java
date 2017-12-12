package updengine.server;

import java.net.DatagramPacket;

public class GamePacket {

    private DatagramPacket datagramPacket;

    GamePacket(byte[] buffer) {
        datagramPacket = new DatagramPacket(buffer, buffer.length);
    }

    public DatagramPacket getDatagramPacket() {
        return datagramPacket;
    }

    public void sendData() {

    }

}
