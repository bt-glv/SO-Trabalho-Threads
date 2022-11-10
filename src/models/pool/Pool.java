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
	
	private ArrayList<int[]> Portas = new ArrayList<int[]> ();

	private int[][] array_pool;
	private Log logger;
	
	private ArrayList<Th_Pessoa> Pessoas  = new ArrayList<Th_Pessoa> ();
	private int qtd_pessoas;
	//
	//	Construtor 
	//
	public Pool(int qtd_portas, int qtd_pessoas, int tamanho_horizontal, int tamanho_vertical, Log logger) 
	{
		array_pool = new int[tamanho_horizontal][tamanho_vertical];
		this.logger=logger;
		generateDoors(qtd_portas);
		this.qtd_pessoas=qtd_pessoas;
		// generatePessoas(qtd_pessoas);
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
	
	
	
	
	
	//
	// Metodos usados para finalizar a criacao da pool
	//
	
	private void pool_place_pessoas() 
	{
		// ArrayList<int[]> nao_ocupados = new ArrayList<int[]> ();
		//
		// Essa funcao seria melhor implementada com uma lista de posicoes nao ocupadas
		
		
		int horizontal=0;
		int vertical=0;
		
		int horizontal_max=array_pool.length;
		int vertical_max=array_pool[0].length;
		
		for(int i=0; i!=this.qtd_pessoas; i++) 
		{
			horizontal++;
			if(horizontal==horizontal_max) {horizontal=0; ++vertical;}
			
			array_pool[horizontal][vertical]=Pessoas.get(i).Th_Aluno_ID;
			
			if(vertical==vertical_max) {break;}
		}
	}
			
	private void generatePessoas() 
	{
		for(int i=0; i!=this.qtd_pessoas;i++) 
		{
			Pessoas.add(new Th_Pessoa(i,this.self));
		}
		
		pool_place_pessoas();
	}
	
	private void setSelf(Pool self)
	{
		this.self = self;
	}
	
	public void finish_setup(Pool self) 
	{
		setSelf(self);
		generatePessoas();
	}
	
	
	
	
	//
	//	Metodos usados por 'acesso' (regiao critica)
	//
	private int[] getPOS(int Th_Aluno_id) 
	{
		for(int i_horizontal=0; i_horizontal!=array_pool.length; i_horizontal++)
		{
			for(int i_vertical=0; i_vertical!=array_pool[0].length; i_vertical++) 
			{
				if(array_pool[i_horizontal][i_vertical]==Th_Aluno_id) {return new int[] {i_horizontal, i_vertical};}
			}
		}
		
		return null;
	}
	
	
	private Boolean check_ifReachedDoor(int Th_Aluno_id, int[] current_pos) 
	{
		for(int linha=0 ; linha < Portas.size(); linha++)
		{
			if(current_pos[0]==Portas.get(linha)[0] && current_pos[1]==Portas.get(linha)[1]) {return true;} 
		}
		return false;
	}
	
	
	
}
