package searchengine;

import java.io.*;
import java.util.*;

/*
 * Searches a file word for word and adds WordResult objects to a HashMap
 * Authors: Jeremy Bohrer + Lou Brand
 */
class SearchFile
{
    private Article article;
    private HashMap<String, MaxHeap> myHashMap;
    private BufferedReader fileScanner;
    private final ArrayList<String> wordSkip = new ArrayList<String>();
    
    /**
     * SearchFile constructor, initializes variables according to parameters
     */
    public SearchFile(Article _article, HashMap _hashMap)
    {
        article = _article;
        myHashMap = _hashMap;
        createWordSkip();
        openFile(article);
        wordCount();
        openFile(article);
    }
    
    /**
     * Optimization method that removes programmer defined "useless" words
     */
    private void createWordSkip() 
    {
        String[] words = new String[] 
        {
            "&", " ", "  ", "", "--", "'", "'s",
            "a", "ap", "at", "as", "an", "and", "another", "all", "also", "are", "about", "after", "again",
            "be", "because", "been", "both", "but", "by", 
            "could",
            "did", "do", "don't", "didn't",
            "each",
            "for", "from", 
            "get", "got", "go", "going", 
            "he", "he's", "had", "has", "have", "here", "him", "his", "how", 
            "i", "if", "I'd", "i'm", "in", "into", "is", "it", "it's", "its", "isn't",
            "just",
            "know", "kind",
            "many", "more", "me", "much", "my", 
            "no", "not",
            "of", "on", "only", "other", "or", "our",
            "put", 
            "so", "some", "sure",
            "really", 
            "to", "too", "that", "that's", "than", "the", "them", "then", "there", "their", "they", "thing", "things", "this", "those",
            "us",
            "was", "wasn't", "way", "we", "we're", "were", "we've", "went", "well", "what", "where", "when", "while", "which", "who", "would", "with", "within",
            "you",                
        };
        wordSkip.addAll(Arrays.asList(words));
    }
    
    /**
     * Initializes a fileScanner that will iterate through the article
     */
    private void openFile(Article _article) 
    {
        try 
        {
            fileScanner = new BufferedReader(new FileReader(_article.getArticle()));
        } catch (FileNotFoundException e) 
        {
            e.printStackTrace();  
        }
    }
    
    /**
     * Counts the words in the article, used in order to rank search results
     */
    public void wordCount()
    {
        try {
            int count = 0;
            while (fileScanner.ready()) 
            {
                String sentence = fileScanner.readLine();
                String[] words = sentence.split("\\s+");
                for (String word : words) 
                {
                    count++;
                }
            }
            fileScanner.close();
            article.setArticleWordLength(count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Searches through the article and updates the hashMap accordingly
     */
    public void search() 
    {
        try {
            while (fileScanner.ready()) 
            {
                String sentenceInitial = fileScanner.readLine();
                String sentence = parseWord(sentenceInitial);
                String[] words = sentence.split("\\s+");

                for (int i = 0; i < words.length; i++) 
                {
                    String key = words[i];
                    if (!wordSkip.contains(key))
                    {
                        WordResult wordRes = new WordResult(article, key);
                        wordRes.setSnippet(sentenceInitial);
                        if (myHashMap.containsKey(key)) 
                        {
                            MaxHeap currentHeap = myHashMap.get(key);
                            WordResult currendWordResult = currentHeap.getElement(wordRes.getArticle().getName());
                            if (currendWordResult != null) 
                            {
                                currendWordResult.addWordCount();
                                currentHeap.bubbleUp(currendWordResult);
                            }
                            else 
                            {
                                wordRes.addWordCount();
                                currentHeap.add(wordRes);
                            }
                        }
                        else 
                        {
                            myHashMap.put(key, new MaxHeap());
                            wordRes.addWordCount();
                            myHashMap.get(key).add(wordRes);
                        }
                    }
                }
            }
            fileScanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }  
    /**
     * Removes all punctuation from the parameters and sets all letters to lower-case
     */
    private String parseWord(String _word) 
    {
        return _word.replaceAll("[.,!;:?()\"]","").toLowerCase();
    }
    
} 
