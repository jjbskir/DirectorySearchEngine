package searchengine;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

/*
 * Creates a search engine object form a serialized hashMap
 */
public class SearchEngineOnline {

    private HashMap<String, MaxHeap> myHashMap;
    
    /**
     * Constructor that creates a search engine from the serialized hashMap called by _name
     */
    public SearchEngineOnline()
    {
        String location  = "/Users/jb/Desktop/java/cs222/SearchEngineFront/src/java/searchengine/serializedData/hashMap.ser";
        deserialize(location);
    }
    
    /**
     * Deseralizes and stores the hashMap
     */
    public void deserialize(String _name) 
    {
        try
        {
            FileInputStream fileIn = new FileInputStream(_name);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            myHashMap = (HashMap<String, MaxHeap>) in.readObject();
            in.close();
            fileIn.close();
        }catch(IOException i)
        {
            i.printStackTrace();
            return;
        }catch(ClassNotFoundException c)
        {
            System.out.println("Error");
            c.printStackTrace();
            return;
        }
    }
    
    /**
     * Calculates the intersecting set of heaps based on the words contained in the sentence
     * @param _sentence Search query
     */
    public MaxHeap search(String _sentence) 
    {
        ArrayList<String> inputWords = new ArrayList<String>();
        inputWords.addAll(Arrays.asList(_sentence.toLowerCase().split("\\s+")));
        inputWords = removeWords(inputWords);
        
        ArrayList<Article> articlesSame = getIntersectArticles(inputWords);

        MaxHeap newIntersectHeap = new MaxHeap();
        
        for (Article article : articlesSame)
        {
            WordResult intersectingWR = new WordResult(article, _sentence);
            intersectingWR.setSnippet(myHashMap.get(inputWords.get(0)).getElement(article.getName()).getSnippet());
            int finalWordCount = 0;
            for (String word : inputWords)
            {
                finalWordCount += myHashMap.get(word).getElement(article.getName()).getWordCount();
            }
            intersectingWR.setWordCount(finalWordCount);
            newIntersectHeap.add(intersectingWR);
        }
        return newIntersectHeap;
    }
    
    /**
     * Removes words not contained in the HashMap from the query
     * @param _words A tokenized version of our search query
     */
    private ArrayList<String> removeWords(ArrayList<String> _words) 
    {
        ArrayList<String> modifiedWords = (ArrayList<String>)_words.clone();
        for (String word : _words) 
        {
            if (!myHashMap.containsKey(word)) 
            {
                modifiedWords.remove(word);
            }
        }
        return modifiedWords;
    }
    
    /**
     * Gets an arrayList of Articles that contain all the words in the query
     * @param Tokenized search query
     */
    private ArrayList<Article> getIntersectArticles(ArrayList<String> _words) 
    {
        HashMap<String, ArrayList<Article>> articles = new HashMap<String, ArrayList<Article>>();
        for (String word : _words)
        {
            if (myHashMap.containsKey(word)) 
            {
                articles.put(word, new ArrayList());
                MaxHeap nodes = myHashMap.get(word).clone();
                
                int size = nodes.size();
                for (int i = 0; i < size; i++) 
                {
                    articles.get(word).add(nodes.getMax().getArticle());
                    nodes.removeMax();
                }
            }
        }
        return compareArticles(articles);
    }
    
    /**
     * Iterates through a list of articles and returns the intersection
     */
    private ArrayList<Article> compareArticles(HashMap<String, ArrayList<Article>> _articles) 
    {
        ArrayList<Article> sameArticles = new ArrayList();
        int count = 0;
        Iterator it = _articles.keySet().iterator();
        while (it.hasNext()) 
        {
            if (count == 0) 
            {
                sameArticles = _articles.get(it.next().toString());
                count++;
            }
            else 
            {
                String key = it.next().toString();
                sameArticles = compareArrays(sameArticles, _articles.get(key));
            }
        }
        return sameArticles;
    }
    
    /**
     * Helper function that combines two arrayLists of articles
     */
    private ArrayList<Article> compareArrays(ArrayList<Article> _articles1, ArrayList<Article> _articles2) 
    {
        ArrayList<Article> sameArticles = new ArrayList<Article>();
        for (int i = 0; i < _articles1.size(); i++) 
        {
            Article currentArticle = _articles1.get(i);
            if (_articles2.contains(currentArticle))
            {
                sameArticles.add(currentArticle);
            }
        }
        return sameArticles;
    }
    
    public HashMap<String, MaxHeap> getMap() 
    {
        return myHashMap;
    }
    
}
