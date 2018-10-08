/*
 * Resources:
 * http://pages.cs.wisc.edu/~bahls/all/SortAnimation/sort/InsertionSorter.java
 * http://pages.cs.wisc.edu/~bahls/all/SortAnimation/sort/BubbleSorter.java
 * http://pages.cs.wisc.edu/~bahls/all/SortAnimation/sort/SelectionSorter.java
 */

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Sorter implements Runnable
{
	  private Integer[] values;
	   private String algorithm;
	   private ArrayComponent panel;
	   private int delay = 500;
	   
   /**
      @param panel of which the arrayComonent is on
      @param values the array to use
      @param algorithm the algoritm to use.
      @param dalay the speed of sorting
   */
   public Sorter(ArrayComponent panel, Integer[] values,String algorithm, int delay)
   {
      this.panel = panel;
      this.values = values;
      this.algorithm = algorithm;
      this.delay=delay;
   }

   public void run()
   {  
      Comparator<Integer> comp = new Comparator<Integer>()
         {
            public int compare(Integer d1, Integer d2) 
            {          
               panel.setValues(values, d1, d2);
               try
               {
                  Thread.sleep(delay);
               }
               catch (InterruptedException exception)
               {
                  Thread.currentThread().interrupt();
               }
               return (d1).compareTo(d2);
            }
         };

         if(algorithm=="select"){
        	 Selectionsort(values, comp);
      } 
         else if(algorithm=="insert"){
        	 InsertionSort(values, comp);
         }
         else if(algorithm=="bubble"){
        	 BubbleSort(values, comp);
         }
      panel.setValues(values, 0, 0); // refill the red color to white when finish sorting
   }
   
   public static <E> void Selectionsort(E[] a, Comparator<E> comp)
   {
       for(int i=0; i<a.length-1; i++){
	   E max=a[i];
	   int maxIndex=i;
	   //Find the least element in unsorted array
	   for(int j=i+1; j<a.length; j++){
	       if(comp.compare(a[j],max)<0){
		   max=a[j];
		   maxIndex=j;
	       }
	   }
	   // swap the least element with the first element in the unsorted array
	   E temp=max;
	   a[maxIndex]=a[i];
	   a[i]=temp;	     
       }  
   }
   
   private static <E> void InsertionSort(E[] a, Comparator<? super E> comp)
   {
	  for(int i=0; i<a.length; i++){
		  E curr=a[i];
		  int j;
		  for(j=i-1; j>=0; j--){
			  // Move elements that are greater than current value to one position ahead of their current position 
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

   private static <E> void BubbleSort(E[] a, Comparator<? super E> comp)
   {
	  for(int i=0; i<a.length-1; i++){ 
		  for(int j=0; j<a.length-1-i; j++){
		      if(comp.compare(a[j],a[j+1])>0){ // compare with the next value, swap if the second is greater than the first
		    	  E tmp=a[j];
		    	  a[j]=a[j+1];
		    	  a[j+1]=tmp;
		      }
		  }
	  }
   }   
}
