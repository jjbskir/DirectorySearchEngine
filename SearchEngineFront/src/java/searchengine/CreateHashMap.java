package searchengine;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;


/*
 * Creates a HashMap and serializes it so it can be used with our search engine
 * Authors: Jeremey Bohrer + Lou Brand
 */
public class CreateHashMap 
{ 
    HashMap<String, MaxHeap> myHashMap;
  
    /**
     * Runner method to create and serialize a hash-map
     */
    public static void main(String[] args) 
    {        
        CreateHashMap chp = new CreateHashMap();
        chp.serialize();
    }
    
    /**
     * Creates a hash map (of arbitrary size) and fills it with our hash-mapped search directory
     */
    public CreateHashMap() 
    {
        myHashMap = new HashMap();
        Directory d = new Directory("/Users/jb/Desktop/java/cs222/SearchEngineFront/web/basketball_docs");
        ArrayList<Article> articles = d.getArticles();
        int count = 0;
        for (Article currentArticle : articles) 
        {
            SearchFile sf = new SearchFile(currentArticle, myHashMap);
            sf.search();
            count++;
            if (count % 50 == 0) 
            {
                System.out.println("working... " + count);
            }
            if (count == 2300)
            {
                break;
            }
        }
        System.out.println("Hash map created.");
    }

    /**
     * Serializes the hash-map for later use
     */
    public void serialize() 
    {
        try
        {
            FileOutputStream fileOut = new FileOutputStream("/Users/jb/Desktop/java/cs222/SearchEngineFront/src/java/searchengine/serializedData/hashMap.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(myHashMap);
            out.close();
            fileOut.close();
        }catch(IOException i)
        {
            i.printStackTrace();
        }
        System.out.println("Hash map serialized.");
    }
    
}


