# Comp128 HW3: Auto-Complete

Your final homework assignment is to implement a new type of tree data structure. This is a data structure that we have not discussed in class (although it is similar to other trees), so this assignment is meant to simulate the sorts of problems you might encounter as a developer: implementing a new data structure for a particular task. You should be well prepared for this based on your existing knowledge of trees and ADT implementations.

The context for this assignment is a text auto-complete program. Auto-complete checkers, like the predictive ones on your phone that provide word suggestions once you start typing, need to store a spelling dictionary in a way that makes it as fast as possible to see what words start with a specific prefix. For example if you started typing the word "do", the auto-complete program should quickly be able to suggest longer words like "dog" or "doctor" that contain the prefix "do".

To make this work efficiently with a tree, rather than storing each word as a node in the tree. We will break the words into individual characters. Each character will correspond to a node in the tree. If the node corresponds with the last letter in the word, then we will mark it using a boolean value that it is the end of a word. Let's look at an example for how each letter gets added, assuming we want to add the words "do", "don", "cat", and "car" to the tree:

<img src="/images/tree1.png" width=60%>
<img src="/images/tree2.png" width=60%>
<img src="/images/tree3.png" width=60%>
<img src="/images/tree4.png" width=60%>
<img src="/images/tree5.png" width=60%>
<img src="/images/tree6.png" width=60%>
<img src="/images/tree7.png" width=60%>
<img src="/images/tree8.png" width=60%>
<img src="/images/tree9.png" width=60%>
<img src="/images/tree10.png" width=60%>
<img src="/images/tree11.png" width=60%>
<img src="/images/tree12.png" width=60%>
<img src="/images/tree13.png" width=60%>
<img src="/images/tree14.png" width=60%>
<img src="/images/tree15.png" width=60%>
<img src="/images/tree16.png" width=60%>

It is very fast to check whether a word is in the tree: start from the root and trace out the word along the edges; if one ends up at a word-terminating node (a purple node in the images above), then the word is in the tree, otherwise it is not.

This tree can also efficiently support auto-completion. Given the prefix-based structure of the tree, it is straightforward to enumerate all of the words that begin with a given letter sequence: these will be represented by nodes that are descendants of the node corresponding to that prefix.

There are multiple ways you might implement the structure shown in the images. We will use a simple implementation that uses a lot of space, but is fast. Each node of the tree has a boolean variable indicating whether a string ending there is a word (corresponding to whether the node is purple or orange in the diagrams). Each node also contains a Map representing outgoing links to child nodes. The map keys are individual letters, and the values are references to other tree nodes. Notice that this is not a binary tree. Each node might have up to 26 children.

## Task

Explore the starter code that we have given you. We have included a `TreeNode` class to represent nodes in the tree. There is also a `PrefixTree` class where you will write your code, and an `AutoComplete` class to play with your completed tree.

In the `PrefixTree` class implement the tree structure defined above. Specifically, you should implement the following methods:

### add(String word)

This should add the word to the tree by creating nodes for each character in the word (if they don't already exist in the tree!), and linking the nodes together by correctly inserting them in the children maps stored at each node.
When the last character is reached, that node should be marked as the end of a word.
If the word does not already exist in the tree, the size variable should be incremented. If the word already exists, this method should not change the tree structure or size variable. 
 
 The string `charAt` method may be helpful to get individual characters from the word.
 Once you complete this, the testAdd method in the `TestPrefixTree` file should pass.

### contains(String word)

This should return true if the word is contained in the tree. Starting at the root, you can iterate through the word's characters searching down the path of nodes. If each character is found in the correct order and the last is marked as a word, then the word is contained in the tree.

Once you complete this, the testContains method should pass.

### getWordsForPrefix(String prefix)

This should return a list of words contained in the tree that start with the prefix characters, including the prefix if it is a word itself. These words can be found by traversing through the letters of the prefix and then traversing any child decendents of the last prefix node.

There are multiple ways to implement this, but probably the easiest is to write a separate helper, recursive method to preorder-traverse the child decendents. Look in the BST activity for an example of a recursive pre-order traversal; however, note that this tree is not binary.

Once you complete this, the testPrefix method should pass.

## Play!
Once your tree is working, run the `AutoComplete` class. Enter the text for a prefix and it will show you the results from a dictionary:

```
Enter text prefix to search, or 'stop'.
appl
========== Results =============
applause
applaud
applauds
apple
apples
applique
applier
appliers
applies
applied
apply
applying

Enter text prefix to search, or 'stop'.
compu
========== Results =============
compute
computer
computes
computed
```
## Submit

When you are done, commit and push your code back to github.

## Attribution

Images for the tree are from Educative.io
