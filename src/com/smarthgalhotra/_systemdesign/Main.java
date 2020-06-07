package com.smarthgalhotra._systemdesign;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        PermanentStorageBST bst = new PermanentStorageBST();
//        bst.insertNode("Smarth","2");
//        bst.insertNode("Galhotra","100");

        System.out.println(bst.searchNode("Smarth"));
        System.out.println(bst.searchNode("Galhotra"));
    }
}
