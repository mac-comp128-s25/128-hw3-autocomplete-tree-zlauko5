package autoComplete;

import java.util.ArrayList;
import java.util.Map;

/**
 * A prefix tree used for autocompletion. The root of the tree just stores links to child nodes (up to 26, one per letter).
 * Each child node represents a letter. A path from a root's child node down to a node where isWord is true represents the sequence
 * of characters in a word.
 */
public class PrefixTree {
    private TreeNode root; 

    // Number of words contained in the tree
    private int size;

    public PrefixTree(){
        root = new TreeNode();
    }

    /**
     * Adds the word to the tree where each letter in sequence is added as a node
     * If the word, is already in the tree, then this has no effect.
     * @param word
     */
    public void add(String word){
        if(!contains(word)){
            TreeNode parentNode = root;
            for(int i = 0; i < word.length(); i++){
                char currentChar = word.charAt(i);
                if(!parentNode.children.containsKey(currentChar)){
                    TreeNode newNode = new TreeNode();
                    newNode.letter = currentChar;
                    if(i == word.length() - 1){
                        newNode.isWord = true;
                    }
                    parentNode.children.put(currentChar, newNode);
                    parentNode = newNode;
                }
                else{
                    parentNode = parentNode.children.get(currentChar);
                }
                
            }
            size++;
        }
        
    }

    /**
     * Checks whether the word has been added to the tree
     * @param word
     * @return true if contained in the tree.
     */
    public boolean contains(String word){
        TreeNode parentNode = root;
        for(int i = 0; i < word.length(); i++){
            char currentChar = word.charAt(i);
            TreeNode currentNode = parentNode.children.get(currentChar);
            if(parentNode.children.containsKey(currentChar) && currentNode.isWord && i == word.length()-1){
                return true;
            }
            else{
                if(!parentNode.children.containsKey(currentChar)){
                    return false;
                }
                else{
                    parentNode = currentNode;
                }
            }
        }
        return false;
    }

    /**
     * Finds the words in the tree that start with prefix (including prefix if it is a word itself).
     * The order of the list can be arbitrary.
     * @param prefix
     * @return list of words with prefix
     */
    public ArrayList<String> getWordsForPrefix(String prefix){
        ArrayList<String> words = new ArrayList<String>();
        TreeNode parentNode = root;
        TreeNode prefixNode = null;
        for(int i = 0; i < prefix.length(); i++){
            char currentChar = prefix.charAt(i);
            TreeNode currentNode = parentNode.children.get(currentChar);
            if(currentNode == null){
                return words;
            }
            if(i == prefix.length() - 1){
                prefixNode = currentNode;
                break;
            }
            parentNode = currentNode;
        }
        String sb = prefix.substring(0, prefix.length()-1);
        if(prefixNode == null || prefixNode.children.isEmpty()){
            return words;
        }
        preOrderTraverse(words, prefixNode, sb);
        return words;
    }

    private void preOrderTraverse(ArrayList<String> words, TreeNode node, String sb){
        sb = sb + node.letter;
        if(node.isWord){
            words.add(sb);
        }
        for(char letter: node.children.keySet()){
            preOrderTraverse(words, node.children.get(letter), sb);
        }
    }

    /**
     * @return the number of words in the tree
     */
    public int size(){
        return size;
    }
    
}
