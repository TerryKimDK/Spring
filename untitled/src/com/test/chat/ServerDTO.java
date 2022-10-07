package com.test.chat;

import java.io.*;
import java.net.Socket;

public class ServerDTO extends Thread{
    private Socket s;
    private BufferedReader i;
    private PrintWriter o;
    private Server server;
    private String user;

    public ServerDTO(Server server, Socket s)throws Exception {
        this.server = server;
        this.s = s;
        InputStream ins = s.getInputStream();
        OutputStream os = s.getOutputStream();
        InputStreamReader isr = new InputStreamReader(ins);
        OutputStreamWriter osw = new OutputStreamWriter(os);
        o = new PrintWriter(osw, true);
        i = new BufferedReader(isr);
    }

    public void run() {
        String name = ""; //사용자가 들어올때
        try {
            name = i.readLine();
            setUser(name);
            server.register(this);
//			System.out.println(name);
            broadcast(name + "님이 방문하셨습니다.");
            // 이곳에 append?
            while(true) {
                String msg = i.readLine();
                broadcast(name + " - " + msg);
            }
        }catch(Exception e) {
            server.unregister(this);
            broadcast(name + "님이 나가하셨습니다.");
        }try {
            i.close();
            o.close();
            s.close(); //자원을 해지하는 것도 순서를 지켜주는 것이 바람직하다

        }catch(IOException ex) {
        }
    }
    protected void println(String message) {
        o.println(message);

    }

    protected void broadcast(String message) {
        server.broadcast(message);
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
}
