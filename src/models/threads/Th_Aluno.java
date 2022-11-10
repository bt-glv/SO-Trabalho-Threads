package models.threads;
import java.util.ArrayList;

import interfaces.Th_Action;



public class Th_Aluno extends Thread
{
	public final String nome;
	
	private int[] current_pos;
	private int[][]doors;
	private int[]door_path_lengths;
	
	private Th_Action th_action;
	
	public Th_Aluno(String nome) {this.nome=nome;}
	
	
	
	
	
	
	private class exec implements Th_Action
	{
		private int[] selected_door;
		private int[] current_pos;
		
		public exec(int[] selected_door) 
		{
			super();
			this.selected_door = selected_door;
		}


		@Override
		public int[][] run(int[][] pool) 
		{
		
			int p_vertical=selected_door[0];
			int p_horizontal=selected_door[1];
			
			Boolean status=false;
			int temp;
			
			if(p_vertical>current_pos[0]) 
			{
				temp=current_pos[0]-p_vertical;
				if(pool[temp][current_pos[1]]==0) {pool[temp][current_pos[1]]  =   pool[current_pos[0]][current_pos[1]];}
				else {}
				return pool;
			}
			if(p_vertical<current_pos[0]) {return null;}
			// if(p_vertical==current_pos[0]){}
			
			
			//
			// rever o conceito de vertical e horizontal aplicado
			//
			
			
			
			do {
			if(p_vertical>current_pos[1]) {return null;}
			if(p_vertical<current_pos[1]) {return null;}
			if(p_vertical==current_pos[1]){return null;}//true
			
			break;
			} while(true);
			
			
			return null;
			// If it returns null, the the thread has reached the door
		}
		
		private int[] swap(int[] pool, int source, int target)
		{
			int swap = pool[target];
			pool[target]=pool[source];
			pool[source]=swap;
			
			return pool;
		}
		
		
		
	} 
	
	private void calc_path_lenght(int[][] doors, int[] current_pos) 
	{
		int[] output = new int[doors.length];
		
		int p_vertical;
		int p_horizontal;
		int length;
		
		for(int i=0; i!=doors.length; i++) 
		{
			length=0;
			
			p_vertical=doors[i][0];
			p_horizontal=doors[i][1];
			
			do {
			if(p_vertical>current_pos[0]) {length+=current_pos[0]-p_vertical;break;}
			if(p_vertical<current_pos[0]) {length+=current_pos[0]+p_vertical;break;}
			if(p_vertical==current_pos[0]){length+=0;}
			break;
			}while(true);
			
			do {
			if(p_vertical>current_pos[1]) {length+=current_pos[1]-p_vertical;break;}
			if(p_vertical<current_pos[1]) {length+=current_pos[1]+p_vertical;break;}
			if(p_vertical==current_pos[1]) {length+=0;}
			break;
			} while(true);
			
			output[i]=length;
		}
		
		this.door_path_lengths=output;
	}


	
	
	//
	// Main code exec
	//
	
	public void run() {}
	

}