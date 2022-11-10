package app;
import java.util.ArrayList;

import interfaces.*;
import models.*;


public class Front implements Log
{

	

	public void receber(int[][] pool,ArrayList<int[]> portas) {
		
		String temp = null;
		
		 for(int linha=0 ; linha < pool.length ; linha++)
		 {
	            
			 
			 for(int coluna = 0; coluna < pool[0].length ; coluna ++)
			 {
				 
	            	pool[linha][coluna] = 0;
	            	int[] porta_atual = portas.get(0);
	            	
	            	
	            	if (linha == porta_atual[0] && coluna == porta_atual[1]) 
	            	{
	            		temp="B";
	            	}
	            	else if(pool[linha][coluna]==0)	
		            {
		            	temp=" ";
		            }
	            	else 
	            	{
	            		temp="*";
	            	}
	            	
	                System.out.printf("["+temp+"]");
	                
	            }
			 
			 
	            System.out.println(" ");
	            
	            
	        }
		
	
	}
	

}
