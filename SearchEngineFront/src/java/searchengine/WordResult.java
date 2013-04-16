package searchengine;

import java.io.Serializable;

/*
 * Creates a WordResult object that will be stored in a MaxHeap in the HashMap for quick access
 * during search
 * Authors: Jeremy Bohrer + Lou Brand
 */
public class WordResult implements Comparable, Serializable 
{
    private Article article;
    private String word;
    private String snippet;
    private int wordCount = 0;
    private double rank = 0;
    
    /**
     * Initializes the WordResult object given an article and word, the word in this case is also the key in the HashMap
     */
    public WordResult(Article _article, String _word) 
    {
        super();
        article = _article;
        word = _word;
    }
    
    /**
     * This compareTo method will only work when comparing two WordResults
     */
    public int compareTo(Object WordResult) 
    {
            WordResult compare = (WordResult) WordResult;
            if(getRank() > compare.getRank())
            {
                    return 1;
            }

            if(getRank() < compare.getRank())
            {
                    return -1;
            }
            
            return 0;
    }
    
    /**
     * Adds 1 to the on-going word count
     */
    public void addWordCount() 
    {
        wordCount++;
    }
    
    /**
     * Returns the current word count
     */
    public int getWordCount() 
    {
        return wordCount;
    }
    
    /**
     * Sets the word count
     * @param _count New word count
     */
    public void setWordCount(int _count)
    {
        wordCount = _count;
    }

    /**
     * Calculates the rank for this WordResult
     */
    public void calculateRank()
    {
        rank = wordCount / article.getArticleWordLength();
    }

    /**
     * Gets the rank for this WordResult
     */
    public double getRank()
    {
        calculateRank();
        return rank;
    }
    
    /**
     * Gets the article associated with this WordResult
     */
    public Article getArticle() 
    {
        return article;
    }
    
    /**
     * Sets the snippet associated with this WordResult
     */
    public void setSnippet(String _snippet) 
    {
        snippet = _snippet;
    }
    
    /**
     * Gets the snippet associated with this WordResult
     */
    public String getSnippet() 
    {
        return snippet;
    }
    
    /**
     * Gets the word associated with this WordResult
     */
    public String getWord() 
    {
        return word;
    }
    
}
