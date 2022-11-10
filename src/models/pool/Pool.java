package models.pool;
import java.util.ArrayList;
import java.util.Random;

import interfaces.*;

public class Pool {

	private ArrayList<int[]> Portas = new ArrayList<int[]> ();
	private int[][] Pool;
	private Log logger;
	
	public Pool(int qtd_portas, int qtd_pessoas, int tamanho_horizontal, int tamanho_vertical, Log logger) 
	{
		Pool = new int[tamanho_horizontal][tamanho_vertical];
		this.logger=logger;
	}
	
	
	public synchronized Boolean acesso(Th_Action action) 
	{
		this.Pool=action.run(Pool);
		logger.receber(Pool, Portas);
		return false;
	}
	
	private void generateDoors(int qtd_portas) 
	{
		Random r = new Random();
		
		int random_horizontal;
		int random_vertical;
	}
	
	private int[][] getPool(){return Pool;}
	private int[][] getPOS(int Th_Aluno_id) {return null;}
	private Boolean check_ifReachedDoor(int Th_Aluno_id, int[] current_pos) 
	{
		for(int linha=0 ; linha < Portas.size(); linha++)
		{
			if(current_pos[0]==Portas.get(linha)[0] && current_pos[1]==Portas.get(linha)[1]) {return true;} 
		}
		return false;
	}
	
	
	
}
