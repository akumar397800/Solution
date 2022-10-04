import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.HashMap;

public class LongestCompoundWord {

	public static void main(String[] args) throws FileNotFoundException {
		
		File file = new File("file.txt");
		Trie trie = new Trie();
		LinkedList<Pair<String>> queue = new LinkedList<Pair<String>>();
		HashSet<String> compoundWords = new HashSet<String>();
		
		@SuppressWarnings("resource")
		Scanner s = new Scanner(file);

		String word;				
		List<Integer> sufIndices;	
		
		while (s.hasNext()) {
			word = s.next();		
			sufIndices = trie.getSuffixesStartIndices(word);
		
			for (int i : sufIndices) {
				if (i >= word.length())		
					break;					
											
				queue.add(new Pair<String>(word, word.substring(i)));
			}
	
			trie.insert(word);
		}
		
		Pair<String> p;				
		int maxLength = 0;			
		//int sec_maxLength = 0;	
		String longest = "";		
		String sec_longest = "";	

		while (!queue.isEmpty()) {
			p = queue.removeFirst();
			word = p.second();
			
			sufIndices = trie.getSuffixesStartIndices(word);
			
			
			if (sufIndices.isEmpty()) {
				continue;
			}
			
			for (int i : sufIndices) {
				if (i > word.length()) { 
					break;
				}
				
				if (i == word.length()) { 
					
					if (p.first().length() > maxLength) {
						sec_longest = longest;
						maxLength = p.first().length();
						longest = p.first();
					}
			
					compoundWords.add(p.first());	
					
				} else {
					queue.add(new Pair<String>(p.first(), word.substring(i)));
				}
			}
		}
		
		System.out.println("Longest Compound Word: " + longest);
		System.out.println("Second Longest Compound Word: " + sec_longest);
		System.out.println("Total Number of Compound Words: " + compoundWords.size());
	}
}
class Pair<T> {
	
	private T first;
	private T second;
	
	public Pair(T first, T second) {
		this.first = first;
		this.second = second;
	}
	
	public T first() {
		return first;
	}
	
	public T second() {
		return second;
	}
}
class TrieNode {
	@SuppressWarnings("unused")
	public char val;			
	public HashMap<Character, TrieNode> children;	
													
	public boolean isWord;		
	
	public TrieNode(char val) {
		this.val = val;
		children = new HashMap<Character, TrieNode>();
		isWord = false;
	}
	
	public void addChild(char child) {
		children.put(child, new TrieNode(child));
	}
	
	public TrieNode getChild(char child) {
		if (!children.keySet().contains(child)) {
			return null;
		}
		
		return children.get(child);
	}
	
	public boolean containsChild(char child) {
		return children.keySet().contains(child);
	}
}

class Trie {
	
	private TrieNode root;
	private TrieNode curr;
	
	public Trie() {
		root = new TrieNode(' ');	
		curr = root;
	}
	
	public void insert(String s) {
		char letter;
		curr = root;
		for (int i = 0; i < s.length(); i++) {
			letter = s.charAt(i);
			
			if (!curr.containsChild(letter)) {
				curr.addChild(letter);
			} 
			
			curr = curr.getChild(letter);
		}
		
		curr.isWord = true;
	}
	
	public 
	List<Integer> getSuffixesStartIndices(String s) {
		List<Integer> indices = new LinkedList<Integer>();	
		char letter;
		curr = root;	
		
		for (int i = 0; i < s.length(); i++) {
			letter = s.charAt(i);
			
			if (!curr.containsChild(letter))
				return indices;
			curr = curr.getChild(letter);
			if (curr.isWord)
				indices.add(i + 1);
		}
		
		return indices;
	}
	
}
