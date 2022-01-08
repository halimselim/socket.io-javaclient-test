package io.hubbox.socket_io_javaclient_test;

import io.socket.client.Ack;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import io.socket.engineio.client.transports.WebSocket;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509ExtendedKeyManager;
import javax.net.ssl.X509ExtendedTrustManager;
import nl.altindag.ssl.SSLFactory;
import nl.altindag.ssl.util.PemUtils;
import okhttp3.OkHttpClient;

/**
 *
 * @author fatih halimoglu
 */
public class SocketioClientTest2 {

    private static Socket mSocket;
    private static final String server_host = "XXX";
    private static final String user_token = "YYY";

    public static void main(String[] args) throws URISyntaxException {

//pem
        X509ExtendedKeyManager km = PemUtils.loadIdentityMaterial(
                Paths.get("C:\\Users\\halim\\Downloads\\client\\client1-crt.pem"),
                Paths.get("C:\\Users\\halim\\Downloads\\client\\client1-key.pem"));

        X509ExtendedTrustManager tm = PemUtils.loadTrustMaterial(Paths.get("C:\\Users\\halim\\Downloads\\client\\ca-crt.pem"));

        SSLFactory sslFactory = SSLFactory.builder()
                .withIdentityMaterial(km)
                .withTrustMaterial(tm)
                .build();

        SSLSocketFactory sslSocketFactory = sslFactory.getSslSocketFactory();
        X509ExtendedTrustManager trustManager = sslFactory.getTrustManager().orElseThrow();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .sslSocketFactory(sslSocketFactory, trustManager)
                .hostnameVerifier(sslFactory.getHostnameVerifier())
                .build();

        io.socket.client.Socket mSocket;

        IO.Options opts = new IO.Options();

        opts.secure = true;
        opts.transports = new String[]{WebSocket.NAME};
        IO.setDefaultOkHttpWebSocketFactory(okHttpClient);

        opts.query = "x-custom-token=" + user_token;
        mSocket = IO.socket(server_host, opts);

        mSocket.on(io.socket.client.Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("connected");
            }
        });
        mSocket.on(io.socket.client.Socket.EVENT_CONNECT_ERROR, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("connection error");
            }
        });
        mSocket.on(io.socket.client.Socket.EVENT_DISCONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("disconnected");
            }
        });

        mSocket.connect();
        mSocket.emit("earthquake", "hello", new Ack() {
            @Override
            public void call(Object... args) {
                System.out.println("ack confirmed");
            }
        });

    }

}
