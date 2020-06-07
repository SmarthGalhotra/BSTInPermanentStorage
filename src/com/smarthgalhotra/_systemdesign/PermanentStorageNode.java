package com.smarthgalhotra._systemdesign;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class PermanentStorageNode {

    private String key;
    private String value;
    private PermanentStorageNode leftNode;
    private PermanentStorageNode rightNode;

    // constructor to enable to create node and  write data to file
    PermanentStorageNode(String key, String value) throws IOException{
        this.key = key;
        this.value = value;
        this.leftNode = null;
        this.rightNode = null;

        // storing permanently
        writeStateToFile();
    }

    // consturctor to read data from a file if it's available
    PermanentStorageNode(String key) throws IOException{

        // fetch the node (file) with the given key
        Path filePath = Paths.get("/Users/smarthgalhotra/Desktop/Genesis/SystemDesign/" + key);

        // parse content from the fetched file
        /*
        remember we stored content in the following manner
        key
        value
        left node file name
        right node file name
         */
        List<String> content = Files.readAllLines(filePath);


        /*

         */
        this.key = content.get(0);
        this.value = content.get(1);


        /*

         */
        if(content.get(2) == "null"){
            this.leftNode = null;
        }
        else{
            this.leftNode = new PermanentStorageNode(content.get(2));
        }

        if(content.get(3) == "null"){
            this.rightNode = null;
        }
        else{
            this.rightNode = new PermanentStorageNode(content.get(3));
        }

    }

    String getKey(){
        return this.key;
    }

    String getValue(){
        return  this.value;
    }

    PermanentStorageNode getLeftNode(){
        return this.leftNode;
    }

    PermanentStorageNode getRightNode(){
        return this.rightNode;
    }

    void setLeftNode(PermanentStorageNode node) throws IOException{
        this.leftNode = node;
        writeStateToFile();
    }

    void setRightNode(PermanentStorageNode node) throws IOException{
        this.rightNode = node;
        writeStateToFile();
    }


    private void writeStateToFile() throws IOException{

         /*

        To store BST to permanent storage we create files !
        Here for simplicity - we keep the  filename same as the BST node's key

        Now each node is a file!

        What information should a node of BST carry?
        key
        value
        left node
        right node

        So we store this information in the form of a string in the file
        1st line has - key
        2nd line stores - value
        3rd line stores - file name of left node
        4th line stores - file name of right node
          */

        // get the file(node) which is to be modified
        Path filePath = Paths.get("/Users/smarthgalhotra/Desktop/Genesis/SystemDesign/" + key);

        // if the left node is null then store current's left node as null otherwise fetch the key of it's left node via getter method
        String leftNodeFileName = leftNode == null ? "null" : leftNode.getKey();
        String rightNodeFileName = rightNode == null ? "null" : rightNode.getKey();

        // store all the information of node as string
        String content = key + "\n" + value + "\n" + leftNodeFileName + "\n" + rightNodeFileName;

        // write the content in the file fetched by Path
        Files.write(filePath,content.getBytes(), StandardOpenOption.CREATE);
    }
}
