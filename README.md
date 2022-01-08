# socket.io-javaclient-test
[Test 1](https://github.com/halimselim/socket.io-javaclient-test/blob/main/src/main/java/io/hubbox/socket_io_javaclient_test/SocketioClientTest.java)
```java
        String server_host = "XXX";
        String user_token = "YYY";
  
        IO.Options opts = new IO.Options();
        opts.query = "x-custom-token=" + user_token;
        Socket mSocket = IO.socket(server_host, opts);

        mSocket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("connected");
            }
        });

        mSocket.connect();
```

# with two way SSL
 [Test2](https://github.com/halimselim/socket.io-javaclient-test/blob/main/src/main/java/io/hubbox/socket_io_javaclient_test/SocketioClientTest2.java) shows 2 way SSL certificate
