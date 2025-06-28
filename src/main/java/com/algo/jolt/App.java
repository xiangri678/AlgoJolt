package com.algo.jolt;

import com.algo.jolt.SXL_algorithm.ArraysAlgo;
import com.algo.jolt.javaLanguage.CreateThreads;
import com.algo.jolt.javaLanguage.ExecutorServiceDemo;
import com.algo.jolt.javaLanguage.UDPClientDemo;
import com.algo.jolt.javaLanguage.UDPServerDemo;

import lombok.extern.slf4j.Slf4j;

/**
 * Hello world!
 *
 */
@Slf4j
public class App 
{
    public static void main( String[] args )
    {
        log.info("Hello Maven!");
        // ArraysAlgo.main(args);
        // CreateThreads.main(args);
        // ExecutorServiceDemo.main(args);
        try{
            UDPClientDemo.main(args);
            // UDPServerDemo.main(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
