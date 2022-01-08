# socket.io-javaclient-test

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
