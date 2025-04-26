package com.algo.jolt.javaLanguage;

import java.net.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UDPServerDemo {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket(8080);
        byte[] buf = new byte[1024 * 64];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        while (true) {
            socket.receive(packet);
            int len = packet.getLength();
            String message = new String(buf, 0, len);
            log.info("接收到：" + message);
            String ip = packet.getAddress().getHostAddress();
            int port = packet.getPort();
            log.info("对方 ip：" + ip + port);
        }
        // socket.close();
    }
}
