package com.codecool.javatries;


import java.util.ArrayList;
import java.util.List;

public class TrieDataNode {

    private char data;
    private List<TrieDataNode> childs;
    private boolean isWord = false;

    public boolean isWord() {
        return this.isWord;
    }

    public void setWord(boolean word) {
        this.isWord = word;
    }

    /**
     * Initializes a TrieDataNode with its data
     * @param data The data in this node
     */
    public TrieDataNode(char data) {
        this.data = data;
        this.childs = new ArrayList<>();
    }

    public char getData() {
        return data;
    }

    public void setData(char character) {
        this.data = character;
    }

    public List<TrieDataNode> getChilds() {
        return this.childs;
    }

    public void setChilds(List<TrieDataNode> childs) {
        this.childs = childs;
    }
}
