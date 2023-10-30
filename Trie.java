<<<<<<< HEAD
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
=======
>>>>>>> caabfda202448d6ec7ca555e3f9cbc03f9c11a1b
import java.lang.reflect.Array;
import java.util.*;

//TODO dict not working printing duplicate values
class Node{
    public char data;
    public int count;
    public Node[] dict;
    public boolean endOfWord;
    Node(){
        this.count=0;
        this.dict=new Node[26];
    }

    Node(char c){
        this.data=c;
        this.count=0;
        this.dict=new Node[26];
    }



    boolean contains(char c){
        int index=getLetterPosition(c);
        return dict[index]==null?false:true;
    }
    
    void markEnd(){
        this.endOfWord=true;
    }

    int getLetterPosition(char c){
        char charLower=Character.toLowerCase(c);
        int asciiValue=(int)charLower;
        return asciiValue-96;
    }

    Node get(char c){
        int charIndex=getLetterPosition(c);
        return dict[charIndex];
    }
}



public class Trie{
    public Node root;
    public List<String> suggestionArray=new ArrayList<>();
    Trie(){
        this.root=new Node();
    } 
    void insert(String word){
        Node curr=root;
        for(int i=0;i<word.length();i++){
            char c=word.charAt(i);
            if(curr.contains(c)){
               curr=curr.get(c); 
            }else{
                Node newNode=new Node(c);
                int charIndex=curr.getLetterPosition(c);
                curr.dict[charIndex]=newNode;
                curr.count++;
                curr=newNode;
            }
        }
        curr.markEnd();
    }
    public String output; 
    /* 
    void perfman(Node curr){
        if(curr.count==0) return;
        for(int i=0;i<curr.count;i++){
            output.concat(curr.child.get(i).data.toString());
            perfman(curr.child.get(i));
        }
    }
    */
    public List<String> allWords;
    //gets all the words in the dict
    void perfman2(){
        Node curr=this.root;
        allWords=new ArrayList<>();
        String s=new String();
        perfmanHelper(curr,s);
    }
    void perfmanHelper(Node curr,String s){
        s=s+curr.data;
        boolean charFound=false;
        for(int i=0;i<26;i++){
            if(curr.dict[i]!=null){
                charFound=true;
                if(curr.endOfWord){
                     allWords.add(s);
                }
                perfmanHelper(curr.dict[i],s);
            }
            
        }
        if(!charFound){
                allWords.add(s);
                s="";
        }
    }

    void printDict(){
        this.perfman2();
        for(int i=0;i<allWords.size();i++){
            System.out.print(allWords.get(i)+" ");
        }
        System.out.println();
    }

    void finder(String s){
        int size=s.length();
        Node curr=this.root;
        boolean wordInDICT=true;
        for(int i=0;i<size;i++){
            char c=s.charAt(i);
            if(curr.contains(c)){
                curr=curr.get(c);
            }else{
                wordInDICT=false;
                System.out.println("not found");
                return;
            }
        }
        if(wordInDICT){
            printRemainningTree(curr);
        }
    }

    public List<String> suggestionStrings;

    void printRemainningTree(Node curr){
        suggestionStrings=new ArrayList<>();
        String s=new String();
        printRemainningTreeHelper(curr, s);
        printSugesstions();
    }

    void printRemainningTreeHelper(Node curr,String s){
        s=s+curr.data;
        boolean charFound=false;
        for(int i=0;i<26;i++){
            if(curr.dict[i]!=null){
                charFound=true;
                if(curr.endOfWord){
                    suggestionStrings.add(s);
                }
                printRemainningTreeHelper(curr.dict[i],s);
            }
        }
        if(!charFound){
            suggestionStrings.add(s);
            s="";
        }

    }

    void printSugesstions(){
        for(int i=0;i<this.suggestionStrings.size();i++){
            System.out.print(suggestionStrings.get(i)+" ");
        }
        System.out.println();
    }
    /* 
    void print(String c){
        Node curr=root;
        output=new String(); 
        for(int i=0;i<c.length();i++){
            if(curr.contains(c.charAt(i))){
                output.concat(toString(c.charAt(i)));
                curr=curr.get(toString(c.charAt(i)));
            }else{
                System.out.println("not found");
                return;
            }

        }
        perfman(curr);
        suggestionArray.add(output);
        printarr(); 
    }
    void printarr(){
        for(int i=0;i<suggestionArrary.size();i++){
            System.out.println(suggestionArray[i]);
        }
    }
    */

    void delete(String word){

    }
<<<<<<< HEAD

    public String createString(char[] arr){
        String s=new String();
        Character emptyChar=Character.MIN_VALUE; 
        for(int i=0;i<arr.length;i++){
            if(arr[i]==emptyChar){
                break;
            }
           System.out.println(arr[i]);
            s=s+arr[i]; 
        }
        return s;
    }

=======
>>>>>>> caabfda202448d6ec7ca555e3f9cbc03f9c11a1b
    public static void main(String[] args){
        Trie t=new Trie();
        t.insert("apple");
        t.insert("abc");
        t.insert("abb");
        t.insert("abd");
<<<<<<< HEAD

        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter a word to search:");
        try{
            String input=reader.readLine();
            t.finder(input);
        }catch(IOException e){
            e.printStackTrace();
        }
        
=======
        
        //t.printDict();
        System.out.println("Enter a word to search:");
        Scanner sc=new Scanner(System.in);
        sc.useDelimiter("\\A");
        
        String input=sc.nextLine();
        t.finder(input);
>>>>>>> caabfda202448d6ec7ca555e3f9cbc03f9c11a1b
        System.out.println("bye."); 
    }
}
