package com.test.chat;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.util.StringTokenizer;

import javax.swing.*;


public class ServerChat extends JFrame implements Runnable, ActionListener{
    private BufferedReader i;
    private PrintWriter o;
    private JTextArea output;
    private JTextField input;
    private JLabel label;
    private Thread listener;
    private String host; //서버 ip
    private JScrollPane jp;
    private JScrollBar jb;
    private JButton button,button2;
    private List user;

    public ServerChat(String server)   {
        super("DK 은행 고객센터");
//         String NickName;

        user = new List();
        host = server;
        listener = new Thread(this);
        listener.start();
        output = new JTextArea();
        button = new JButton("전송");
        button2 = new JButton("취소");
        jp = new JScrollPane(output);
        jb = jp.getVerticalScrollBar();
        add(jp, "Center");
//      add(new JScrollBar(0), "East");
        output.setEditable(false);
        Panel bottom = new Panel(new BorderLayout());

        add(user,"East");
        Panel bottom2 = new Panel(new FlowLayout());
        label = new JLabel("사용자 이름");
        bottom2.add(button);
        bottom2.add(button2);
        bottom.add(bottom2,"East");
        bottom.add(label, "West");
        input = new JTextField();
        bottom.add(input, "Center");
        add(bottom, "South");

        input.addActionListener(this);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(400,400,400,400);
        setVisible(true);

    } // 생성자
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        Object c = e.getSource(); // getsource는 모든 이밴트 발생 객체를 가져옴
        if(c == input) {
            label.setText("메세지");
            o.println(input.getText()); // input에 텍스트를 가져와서 o.println에 찍어? --> 사용자 이름으로 바꿔 -> 그 다음부턴 메세지를 보내는거
            input.setText("");

        }
    }

    @Override
    public void run() {
        try {

            Socket s = new Socket(host, 7979);
            InputStream ins = s.getInputStream();
            OutputStream os = s.getOutputStream();
            i = new BufferedReader(new InputStreamReader(ins));
            o = new PrintWriter(new OutputStreamWriter(os), true);
            while(true) {
                String line = i.readLine();
                StringTokenizer st = new StringTokenizer(line, "#");
                output.append(st.nextToken() + "\n");
                user.removeAll();
                while(st.hasMoreElements()) {
                    user.add(st.nextToken());
                }
//            output.append(line + "\n");
                jb.setValue(jb.getMaximum());
            }
        }catch(Exception e) {
            e.printStackTrace();
        }

    }

//    public static void main(String[] args) {
//        if(args.length > 0) {
//            new ServerChat(args[0]);
//        } else {
//            new ServerChat("localhost");
//        }
//    }


}