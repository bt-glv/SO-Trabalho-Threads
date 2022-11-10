package models.pool;
import java.util.ArrayList;
import interfaces.*;

public class Pool {

	ArrayList<int[]> Portas = new ArrayList<int[]> ();
	int[][] Pool;
	
	public Pool(int qtd_portas, int qtd_pessoas, int tamanho_horizontal, int tamanho_vertical, Log logger) 
	{
		
	}
	
	
	public synchronized void acesso() 
	{
		
	}
	
	private void generateDoors() {}
	
	private int[][] getPool(){return null;}
	private int[][] getPOS(int Th_Aluno_id) {return null;}
	private Boolean check_ifReachedDoor(int Th_Aluno_id) {return false;}
	
	
	
}
