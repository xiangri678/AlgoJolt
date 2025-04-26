package com.algo.jolt.javaLanguage;

import java.net.InetAddress;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InetAddressDemo {
    public static void main(String[] args) {
        try {
            InetAddress ip1 = InetAddress.getLocalHost();
            System.out.println(ip1.getHostAddress() + ip1.getHostName());
            InetAddress ip2 = InetAddress.getByName("www.google.com");
            System.out.println(ip2.getHostAddress() + ip2.getHostName());
            System.out.println(ip2.isReachable(5000));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
