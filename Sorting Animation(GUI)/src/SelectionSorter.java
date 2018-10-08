

import java.util.*;

/**
 *performs a selection sort 
 *@author tbahls
  @author Horstman
 */


public class SelectionSorter
{

    /**
      Sorts an array, using the selection sort algorithm.
      @param a the array to sort
      @param comp the comparator to compare array elements
   */
 
  public static <E> void sort(E[] a, Comparator<? super E> comp)
   {
       for(int i=0; i<a.length-1; i++){
	   E max=a[i];
	   int maxIndex=i;
	   for(int j=i+1; j<a.length; j++){
	       if(comp.compare(a[j],max)<0){
		   max=a[j];
		   maxIndex=j;
	       }
	   }
	   E temp=max;
	   a[maxIndex]=a[i];
	   a[i]=temp;
	     
       }
   
   }
  
}
