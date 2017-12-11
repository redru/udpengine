import java.io.IOException;
import java.net.*;

public class Client {

    private DatagramSocket socket;
    private InetAddress address;

    private byte[] buf;

    public Client() throws SocketException, UnknownHostException {
        socket = new DatagramSocket();
        address = InetAddress.getByName("localhost");
    }

    public String sendEcho(String msg) {
        buf = msg.getBytes();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);

        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }

        packet = new DatagramPacket(buf, buf.length);
        try {
            socket.receive(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String received = new String(packet.getData(), 0, packet.getLength());
        return received;
    }

    public void close() {
        socket.close();
    }

    public static void main(String[] args) throws SocketException, UnknownHostException {
        Client client = new Client();

        String echo = client.sendEcho("hello server");
        System.out.println(echo);

        echo = client.sendEcho("server is working");
        System.out.println(echo);
        client.sendEcho("end");
        client.close();
    }

}
