package com.dmc.common;

import java.util.Scanner;

public class ClientStart {
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 1; i ++) {
            Client client1 = new Client();
            Thread thread = new Thread(client1);
            thread.start();

            Scanner in = new Scanner(System.in);
            while (in.hasNextLine()) {
                String str = in.nextLine();
                client1.sendMsg(str);
            }
        }
    }
}
