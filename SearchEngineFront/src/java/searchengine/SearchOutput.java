package searchengine;

/**
 *
 * @author Jeremy Bohrer
 */
public class SearchOutput {
    
    /**
     * Prints out the heap
     */
    public void printHeap(MaxHeap _heap) 
    {
        int size = _heap.size();
        for (int i = 0; i < size; i++) 
        {
            System.out.println("search result " + i);
            System.out.println("article name: " + _heap.getMax().getArticle().getName());
            System.out.println("rank: " + _heap.getMax().getRank());
            System.out.println(_heap.getMax().getSnippet());
            System.out.println();
            _heap.removeMax();
        }
    }
    
}
