# Solution

It is a Java program , 
the main class is LongestCompound 
this program calculate the longest and the second longest words in a file
Pair class is used to store a pair of word .
a pair of word and its remaining suffix
 
  Helper data structure for class LongestCompoundWord
 each node, which contains a letter as its value,
 in trie may have a list of chlidren nodes
 Trie is also able to find all suffixes indices of a word.
 
 A Trie is a special data structure used to store strings that can be visualized like a graph. It consists of nodes and edges. Each node consists of at max 26 children and edges connect each parent node to its children. These 26 pointers are nothing but pointers for each of the 26 letters of the English alphabet A separate edge is maintained for every edge.

Strings are stored in a top to bottom manner on the basis of their prefix in a trie. All prefixes of length 1 are stored at until level 1, all prefixes of length 2 are sorted at until level 2 and so on.

Now, one would be wondering why to use a data structure such as a trie for processing a single string? Actually, Tries are generally used on groups of strings, rather than a single string. When given multiple strings , we can solve a variety of problems based on them. 
  
 
 
