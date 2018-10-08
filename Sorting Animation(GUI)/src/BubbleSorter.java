import java.util.*;

/**
   This class carries out the bubble sort algorithm.
   @author tbahls
   @author Horstman
*/
public class BubbleSorter
{
   /**
      Sorts an array, using the bubble sort algorithm.
      @param a the array to sort
      @param comp the comparator to compare array elements
   */


   /**
      Sorts a range of an array, using the buuble sort
      algorithm.
      @param a the array to sort
      @param comp the comparator to compare array elements
   */
   private static <E> void Sort(E[] a, Comparator<? super E> comp)
   {
	  for(int i=0; i<a.length-1; i++){
		  for(int j=0; j<a.length-1-i; j++){
		      if(comp.compare(a[j],a[j+1])>0){
		    	  E tmp=a[j];
		    	  a[j]=a[j+1];
		    	  a[j+1]=tmp;
		      }
		  }
	  }
   }   
}
