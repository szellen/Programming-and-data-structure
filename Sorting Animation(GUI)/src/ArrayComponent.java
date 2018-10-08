/*
 * Resources:
 * https://docs.oracle.com/javase/7/docs/api/javax/swing/JComponent.html
 * http://pages.cs.wisc.edu/~bahls/all/SortAnimation/sort/ArrayComponent.java
 * Big Java 2.9 Drawing on a Component
 */

import java.awt.*;
import javax.swing.*;

public class ArrayComponent extends JComponent {

	private Integer[] values = new Integer[SortGUI.elements];
	private int marked1;
	private int marked2;
	 
	private int comparisons=0; // count number of comparisons
	
	public synchronized void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		int width = 3;
		int height = getHeight();

		for (int i = 0; i < values.length; i++)
		{			
			int v =  values[i];
			Rectangle box = new Rectangle(20*i,20,20,v*8); // draw boxes
			if (v == marked1 || v == marked2){
				g2.setColor(Color.RED);
				g2.fill(box);
				g2.setColor(Color.black);
			}else{
				g2.draw(box);
			}
			g2.drawString("Comparisons: "+comparisons,280,height-10);
		}
	}

	/**
      Sets the values to be painted.
      @param values the array of values to display
      @param marked1 the first marked element
      @param marked2 the second marked element
	 */
	public void setValues(Integer[] values,Integer marked1, Integer marked2)
	{
		this.values = (Integer[]) values.clone();
		this.marked1 = marked1;
		this.marked2 = marked2;
		comparisons++;
		repaint();
	}
}
