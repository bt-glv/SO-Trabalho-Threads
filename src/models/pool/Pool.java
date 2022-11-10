package models.pool;
import java.util.ArrayList;
import java.util.Random;

import models.*;
import models.threads.Th_Pessoa;
import interfaces.*;

public class Pool 
{
	//
	//	Variaveis	
	//
	private Pool self;
	public void setSelf(Pool self) {this.self = self;}
	
	private ArrayList<int[]> Portas = new ArrayList<int[]> ();

	private int[][] array_pool;
	private ArrayList<Th_Pessoa> Pessoas  = new ArrayList<Th_Pessoa> ();
	private Log logger;
	
	//
	//	Construtor 
	//
	public Pool(int qtd_portas, int qtd_pessoas, int tamanho_horizontal, int tamanho_vertical, Log logger) 
	{
		array_pool = new int[tamanho_horizontal][tamanho_vertical];
		this.logger=logger;
		generateDoors(qtd_portas);
		generatePessoas(qtd_pessoas);
	}
	
	
	
	//
	// Metodo usado pelos threads para acessar a array_pool (regiao critica)
	//
	public synchronized Boolean acesso(Th_Action action, int th_id) 
	{
		int[] POS = getPOS(th_id);
		
		this.array_pool=action.run(array_pool, POS);
		logger.receber(array_pool, Portas);
		
		return check_ifReachedDoor(th_id, POS);
	}



	//
	// Metodos usados no costrutor
	//
	private void generateDoors(int qtd_portas) 
	{
		Random r = new Random();
		
		int random_horizontal;
		int random_vertical;
		
		Boolean repeated;
		
		for(int i=0;i!=qtd_portas;i++) 
		{
			// Essa implementacao pode cusar um loop infinito se muitos portas forem geradas
			//
			// O correto seria manter um indice de quais indices na oestao ocupados por uma porta
			// e entao escolher um numero entre elas.
			
			while(true) 
			{
			random_horizontal= r.nextInt(array_pool.length);
			random_vertical= r.nextInt(array_pool[0].length);
			
			repeated=this.Portas.add(new int[] {random_vertical, random_vertical});
			
			if(!repeated) {this.Portas.remove(i);} else {break;}
			
			}
		}
	}
	private void generatePessoas(int qtd_pessoas) 
	{
		for(int i=0; i!=qtd_pessoas;i++) 
		{
			Pessoas.add(new Th_Pessoa(i,this.self));
		}
	}
	
	
	
	
	//
	//	Metodos usados por 'acesso' (regiao critica)
	//
	private int[] getPOS(int Th_Aluno_id) {return null;}
	private Boolean check_ifReachedDoor(int Th_Aluno_id, int[] current_pos) 
	{
		for(int linha=0 ; linha < Portas.size(); linha++)
		{
			if(current_pos[0]==Portas.get(linha)[0] && current_pos[1]==Portas.get(linha)[1]) {return true;} 
		}
		return false;
	}
	
	
	
}
