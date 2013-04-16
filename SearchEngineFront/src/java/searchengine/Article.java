package searchengine;

import java.io.File;
import java.io.Serializable;

/*
 * Creates an Article
 */
public class Article implements Serializable
{ 
 private File article;
    private double articleWordLength = 0;
 
    /**
     * Creates an article from a given file
     */
    public Article(File file)
    {
        super();
        article = file;
    }
 
 /**
  * Returns the file that contains the parametrized article
  */
 public File getArticle()
 {
  return article;
 }
        
 /**
  * Gets the .txt name of the article (file name)
  */
    public String getName() 
    {
        return article.getName();
    }
 
    /**
     * Gets the path to the article
     */
    public String getPath() 
    {
        return article.getPath();
    }
        
    /**
     * Sets the word length of the article
     */
    public void setArticleWordLength(int _count) 
    {
        articleWordLength = _count;
    }
        
    /**
     * Gets the word length of the article
     */
    public double getArticleWordLength() 
    {
        return articleWordLength;
    }
}
