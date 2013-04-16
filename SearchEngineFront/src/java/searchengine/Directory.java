package searchengine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/*
 * Creates a directory of all text files in a given folder
 */
public class Directory 
{
 private ArrayList<Article> allArticles = new ArrayList<Article>();
 
 /**
  * Creates and generates an ArrayList of all the files in the given folder
  * @param folder Specific folder
  */
 public Directory(String folder)
 {
        super();
  File dir = new File(folder);
  FileReader currentFile;
  BufferedReader br;
  
  for (File child : dir.listFiles())
  {
   if (!child.isHidden())
            {
                allArticles.add(new Article(child));
            }  
  }
 }
 
 /**
  * Returns all articles in an ArrayList
  * @return All articles
  */
 public ArrayList<Article> getArticles()
 {
  return allArticles;
 }
        
}
