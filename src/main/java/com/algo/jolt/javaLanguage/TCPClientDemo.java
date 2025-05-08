package com.algo.jolt.javaLanguage;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TCPClientDemo {
    public static void main(String[] args) throws Exception{
        Socket so = new Socket("127.0.0.1", 9999);
        OutputStream os = so.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        Scanner sc = new Scanner(System.in);
        while (true) {
            String message = sc.nextLine();
            if ("exit".equals(message)) {
                dos.close();
                so.close();
                break;
            }
            dos.writeUTF(message);
            dos.flush();
        }
    }

}
