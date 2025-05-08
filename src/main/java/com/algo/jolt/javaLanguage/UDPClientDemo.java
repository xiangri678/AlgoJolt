package com.algo.jolt.javaLanguage;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UDPClientDemo {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String message = sc.nextLine();
            if ("exit".equals(message)) {
                socket.close();
                break;
            }
            byte[] bytes = message.getBytes();
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length, InetAddress.getLocalHost(), 8080);
            socket.send(packet);
        }
    }
}
