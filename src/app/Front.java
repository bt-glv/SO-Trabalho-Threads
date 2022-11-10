package app;
import interfaces.*;
import models.*;


public class Front implements Log
{

	
	@Override
	public void receber(int[][] pool) {
		
		String temp;
		
		 for(int linha=0 ; linha < pool.length ; linha++){
	            for(int coluna = 0; coluna < pool[0].length ; coluna ++){
	            	pool[linha][coluna] = 0;
	            			
	            	if(pool[linha][coluna]==0)	{temp=" ";} else {temp="*";}	
	                System.out.printf("["+temp+"]");
	            }
	            System.out.println(" ");
	        }
		
		
	}
	

}
