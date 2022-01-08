package io.hubbox.socket_io_javaclient_test;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import java.net.URISyntaxException;

/**
 *
 * @author fatih halimoglu
 */
public class SocketioClientTest {

    private static Socket mSocket;
    private static String server_host = "XXX";
    private static String user_token = "YYY";

    public static void main(String[] args) throws URISyntaxException {

        String socketUrl = server_host;

        IO.Options opts = new IO.Options();
        opts.query = "x-custom-token=" + user_token;
        mSocket = IO.socket(socketUrl, opts);

        mSocket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("connected");
            }
        });

        mSocket.connect();
    }

}
