

import java.util.*;

/**
   This class carries out the insertion sort algorithm.
   @author tbahls
    @author Horstman
*/
public class InsertionSorter
{
   /**
      Sorts an array, using the insertion sort algorithm.
      @param a the array to sort
      @param comp the comparator to compare array elements
   */
   public static <E> void sort(E[] a, Comparator<? super E> comp)
   {
      goSort(a, comp);
   }

   /**
      Sorts a range of an array, using the insertion sort
      algorithm.
      @param a the array to sort
      @param comp the comparator to compare array elements
   */
   private static <E> void goSort(E[] a, Comparator<? super E> comp)
   {
	  for(int i=0; i<a.length; i++){
		  E curr=a[i];
		  int j;
		  for(j=i-1; j>=0; j--){
		      if(comp.compare(curr,a[j])>=0){
		    	  		    	  
		    	  a[j+1]=curr;
		    	  break;
		      }else{
		    	  a[j+1]=a[j];
		      }
		  }
		  if(j<0){
			a[0]=curr;  
		  }		    
	  }
   }   
}
