package com.codecool.javatries;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class AutoComplete {

    private TrieDataNode root;
    private List<String> results;

    /**
     * Starts a new Trie with dummy root data "-"
     */
    public AutoComplete() {
        this.root = new TrieDataNode('-');
        this.results = new ArrayList<>();
    }

    /**
     * Adds a word to the Trie.
     */
    public void addWord(String wordToAdd) {

        char[] characters = createCharArray(wordToAdd);
        TrieDataNode current = this.root;

        for(char character : characters) {
            if( current.getChilds().size() == 0) {
                TrieDataNode newNode = new TrieDataNode(character);
                current.getChilds().add(newNode);
                current = newNode;
            }
            else {
                ListIterator<TrieDataNode> childsIterator = current.getChilds().listIterator();
                TrieDataNode child = null;

                while(childsIterator.hasNext()) {
                    child = childsIterator.next();
                    if(child.getData() == character){
                        current = child;
                        break;
                    }
                }
                if (child != null && child.getData() != character) {
                    TrieDataNode newNode = new TrieDataNode(character);
                    current.getChilds().add(newNode);
                    current = newNode;
                }
            }
        }
        current.setWord(true);
    }

    /**
     * Returns the possible completions of baseChars String from the Trie.
     * @param baseChars The String to autocomplete.
     * @return possible completions. An empty list if there are none.
     */
    public List<String> autoComplete(String baseChars) {
        char[] characters = createCharArray(baseChars);
        List<TrieDataNode> currentList = this.root.getChilds();
        boolean isMatched = false;
        StringBuilder prefix = new StringBuilder();

        for(int i = 0; i < characters.length; i++){
            if(currentList.size() > 0){
                for(int j = 0; j < currentList.size(); j++){
                    boolean isEqualIgnoreCase = compareCharsIgnoreCase(characters[i], currentList.get(j).getData());
                    if(isEqualIgnoreCase){
                        prefix.append(currentList.get(j).getData());
                        currentList = currentList.get(j).getChilds();
                        isMatched = true;
                        break;
                    }
                }
            }
        }
        if(isMatched){
            if(currentList.size() > 0){
                getAllWords(currentList, prefix.toString());
                return this.results;
            }
            results.add(prefix.toString());
        }
        return this.results;
    }

    private List<TrieDataNode> getAllWords(List<TrieDataNode> start, String prefix) {
        List<TrieDataNode> list = new ArrayList<>();

        for(TrieDataNode node : start) {
            if(node.isWord()){
                this.results.add(prefix + node.getData());
            }
            list.addAll(getAllWords(node.getChilds() , prefix + node.getData()));
        }
        return list;
    }

    private char[] createCharArray(String word){
        char[] newChar = new char[word.length()];

        for(int i = 0; i < word.length(); i++){
            newChar[i] = word.charAt(i);
        }
        return newChar;
    }

    private boolean compareCharsIgnoreCase(Character character, Character data){
        Character toLower = Character.toLowerCase(character);
        if(toLower == Character.toLowerCase(data)){
            return true;
        }
        return false;
    }
}
