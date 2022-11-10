package models.threads;
import java.util.ArrayList;

import interfaces.Th_Action;
import models.pool.Pool;



public class Th_Pessoa extends Thread
{
	public final int Th_Aluno_ID;
	private Th_Pessoa self;

	private Pool pool;
	private int[][]doors;
	
	protected ArrayList<int[]> portas = new ArrayList<int[]> ();
	private int[]door_path_lengths;

	
	// Construtor
	
	public Th_Pessoa(int Th_Aluno_ID, Pool pool) 
	{
		this.Th_Aluno_ID=Th_Aluno_ID;
		this.pool=pool;
	}

	
	// Metodo a ser executado em Pool
	
	public class movimentacao_aleatoria implements Th_Action
	{

		private Th_Pessoa pai;

		
		
		public movimentacao_aleatoria(Th_Pessoa pai) {
			super();
			this.pai = pai;
		}



		@Override
		public int[][] run(int[][] pool, int[] Current_POS, ArrayList<int[]> portas) {
			// TODO Auto-generated method stub
			return null;
		}

		
		
	}
	
	public class movimentacao_para_a_porta implements Th_Action
	{

		private Th_Pessoa pai;

		
		
		public movimentacao_para_a_porta(Th_Pessoa pai) {
			super();
			this.pai = pai;
		}



		@Override
		public int[][] run(int[][] pool, int[] Current_POS, ArrayList<int[]> portas) {
			// TODO Auto-generated method stub
			pai.portas=portas;
			return null;
		}


	}
	
	
	private int selecionar_porta() 
	{
		int lower=999999999;
		int selected_index=0;
		
		for(int i=0; i!=door_path_lengths.length; i++) 
		{
			if(door_path_lengths[i]<lower) {lower=door_path_lengths[i]; selected_index=i;}
		}
		
		return selected_index;
	}
	
	
	private int[][] mover_para_porta(int[][] pool, int[] current_pos) 
	{
			int porta_selecionada = selecionar_porta();
			
			int p_horizontal =portas.get(porta_selecionada)[0];
			int p_vertical   =portas.get(porta_selecionada)[1];
			
			Boolean status=false;
			int posicao_alvo;
			
			
			
			
			if(p_horizontal>current_pos[0]) 
			{
				posicao_alvo=current_pos[0]-p_horizontal;
				if(pool[posicao_alvo][current_pos[1]]==0) 
				{
					pool[posicao_alvo][current_pos[1]]  =   pool[current_pos[0]][current_pos[1]];
				}
				else 
				{
					
				}
				return pool;
			}
			if(p_horizontal<current_pos[0]) 
			{
				return null;
			}
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
		
	private void mover_aleatoriamente() {}
	
	
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
	// Tools
	
	private int[] Array_Swap(int[] pool, int source, int target)
	{
			int swap = pool[target];
			pool[target]=pool[source];
			pool[source]=swap;
			
			return pool;
	}
	
	
	//
	// Main code exec
	//
	
	public void run() 
	{
		long startTime = System.currentTimeMillis(); //fetch starting time
		while(false||(System.currentTimeMillis()-startTime)<10000)
		{
			pool.acesso(new movimentacao_aleatoria(this.self), Th_Aluno_ID);
		}
		
		Boolean loop=true;
		
		while(loop) 
		{
			pool.acesso(new movimentacao_para_a_porta(this.self), Th_Aluno_ID);
		}
		
	}
	

}
