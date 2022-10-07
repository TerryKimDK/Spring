package com.test.chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

public class Server {
        private Vector handlers;

    public Server(int port) { // server
        try {
            ServerSocket server = new ServerSocket(port);
            handlers = new Vector();
            System.out.println("ChatServer is ready");

//            Main.sysBank();
            while (true) {// 소켓으로 관리하는게 아니라 객체화해서 관리
                ServerDTO handler = new ServerDTO(this, server.accept()); // this: 서버에 대한 인스턴스
                handler.start(); // thread로 대응
            }
        } catch (Exception e) {

        }
    }

    // 서버에서 필요한것들을 생성하면된다
    public Object getHandler(int index) {
        return handlers.elementAt(index);
    }

    public void register(ServerDTO c) {
        handlers.addElement(c); //처음에 접속했을때 사용자의 이름을 받아주고 connection에 넣엊
    }

    public void unregister(Object o) {
        handlers.removeElement(o);
    }

    public void broadcast(String message) {
        synchronized (handlers) {

            int n = handlers.size();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < n; i++) {
                ServerDTO h = (ServerDTO) handlers.elementAt(i);
                sb.append("#" + h.getUser());
            }
            for (int i=0; i < n; i++) {
                ServerDTO h = (ServerDTO) handlers.elementAt(i);
                try {
                    h.println(message + sb.toString()); // 모든 사용자에게 메세지를 뿌린다
                } catch (Exception e) {}
            }
        } //동기화처리
    }

    public static void main(String[] args) {

        new Server(7979);
    }
}
