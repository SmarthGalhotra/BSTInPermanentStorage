package com.smarthgalhotra._systemdesign;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class PermanentStorageBST {
    private static final Path rootFilePath = Paths.get("/Users/smarthgalhotra/Desktop/Genesis/SystemDesign/root");
    private  PermanentStorageNode rootNode;


    // fetch root node as soon as PermanentStorageBST is instantiated
    PermanentStorageBST() throws IOException {

        // we stored root in in a file named root
        List<String> content = Files.readAllLines(rootFilePath);

        // parse the content stored in file named root i.e checking if root node exists
        if(content.size() > 0){
            this.rootNode = new PermanentStorageNode(content.get(0));
            return;
        }

        // if the file named root is found empty --- there's no tree ==> set root node to null
        this.rootNode = null;
    }

    void insertNode(String key, String value) throws IOException{
        insertNodeInternal(rootNode,key,value);
    }

    private void insertNodeInternal(PermanentStorageNode rootNode, String key, String value ) throws  IOException{

        // if the tree is empty ==> set this node to be root and save it in file named root for future reference
        if(rootNode == null){
            this.rootNode = new PermanentStorageNode(key, value);
            Path filePath = Paths.get("/Users/smarthgalhotra/Desktop/Genesis/SystemDesign/root");
            Files.write(filePath,key.getBytes());
            return;
        }


        // if the key of  node to be inserted is greater than current root node's key ==> we should insert this node to the right of root
        if(key.compareTo(rootNode.getKey() ) > 0){

            // if there's no right node of the root node --> insert the node which needs to be inserted on the right of root node
            if(rootNode.getRightNode() == null){
                rootNode.setRightNode(new PermanentStorageNode(key, value));
                return;
            }
            // if the right node of root node exist --> we should recurse for the root's right node
            insertNodeInternal(rootNode.getRightNode(),key,value);
        }
        // if the key of node to be inserted is lesser than the current root node's key ==> we should insert this node to left of root
        else{

            // if there's no left node of the root node --> insert the node which needs to be inserted on the left of root node
            if(rootNode.getLeftNode() == null){
                rootNode.setLeftNode(new PermanentStorageNode(key, value));
                return;
            }
            // if the left node of root node exist --> we should recurse for the root's left node
            insertNodeInternal(rootNode.getLeftNode(), key, value);
        }
    }

    String searchNode(String key){
        return searchNodeInternal(rootNode, key);
    }

    private String searchNodeInternal(PermanentStorageNode rootNode, String key){
        if(rootNode == null)
            return null;

        if(rootNode.getKey().equals(key))
            return rootNode.getValue();
        else if(rootNode.getKey().compareTo(key) < 0){
            // root key < what we are searching for ==> go to right
            return searchNodeInternal(rootNode.getRightNode(), key);
        }
        else{
            // root key > what we are searching for ==> go left
            return searchNodeInternal(rootNode.getLeftNode(), key);
        }

    }

}
