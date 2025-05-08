package com.algo.jolt.javaLanguage;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TCPServerDemo {
    public static void main(String[] args) throws Exception{
        ServerSocket ss = new ServerSocket(9999);
        Socket so = ss.accept();
        InputStream is = so.getInputStream();
        DataInputStream dis = new DataInputStream(is);
        while (true) {
            String message = dis.readUTF();
            log.info("message = " + message + "address = " + so.getInetAddress().getHostAddress() + "prot = " + so.getPort());
        }
    }
}
