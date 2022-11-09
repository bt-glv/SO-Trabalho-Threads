package pools;import java.util.ArrayList;
import java.util.Random;

import models.*;
import interfaces.*;

public class Pool_Pessoa 
{
	private Pessoa[] pool;
	private int pool_tamanho;
	
	private ArrayList<Integer> posicoes_ocupadas = new ArrayList<Integer> ();
	
	
	
	
	public Pool_Pessoa(int tamanho_pool, Pessoa[] pessoas)
	{
		pool = new Pessoa[tamanho_pool];
		pool_tamanho=tamanho_pool;
		
		for(int i=0; i!=pessoas.length; i++) {
			pool_inserir_pessoa(pessoas[i]);
		}
	}

	public void pool_movimentar_pessoas(int tempo_milisegundos) 
	{
		long startTime = System.currentTimeMillis();
		Random r = new Random();
		
		Pessoa swap;
		int indice_inicial;
		int indice_alvo=0;
		
		while((System.currentTimeMillis()-startTime)<tempo_milisegundos) 
		{
			indice_alvo=r.nextInt(pool_tamanho);
			indice_inicial=posicoes_ocupadas.get(r.nextInt(posicoes_ocupadas.size()));
			
			if(pool[indice_alvo]==null) 
			{
				pool[indice_alvo]=pool[indice_inicial];
				pool[indice_inicial]=null;
			}
			else
			{
				swap=pool[indice_alvo];
				
				pool[indice_alvo]=pool[indice_inicial];
				pool[indice_inicial]=swap;
			}
			
		}
	}





	private void pool_inserir_pessoa(Pessoa p)
	{
		pool[posicoes_ocupadas.size()]=p;
		posicoes_ocupadas.add(posicoes_ocupadas.size());
		
	}

	private Pessoa pool_remover_pessoa(int pool_indice) 
	{
		if(pool[pool_indice]==null) {return null;}
		
		Pessoa output = pool[pool_indice];
		pool[pool_indice]=null;
		posicoes_ocupadas.remove(Integer.valueOf(pool_indice));
		
		return output;
	}





	private int[] pool_listar_posicoes_ocupadas() 
	{
		int[] output = new int[posicoes_ocupadas.size()];
		
		for(int i=0; i!=posicoes_ocupadas.size();i++)
		{
			output[i]=posicoes_ocupadas.get(i);
		}
		
		return output;
	}

	public synchronized Pessoa resgatar_pessoa(Algoritmo_De_Decisao a) 
	{
		int pessoa_selecionada = a.run(posicoes_ocupadas, pool);
		return pool_remover_pessoa(pessoa_selecionada);
	}
/*	
	public synchronized Tuple<int[], Pessoa> thread_action(int action, int pool_indice)
	{
		Tuple<int[], Pessoa> output = new Tuple<int[], Pessoa> ();
		
		if(action==1) 
		{
			int[] temp = pool_listar_posicoes_ocupadas();
			output._1=temp;
			return output;
		}
		if(action==2) 
		{
			Pessoa temp = pool_remover_pessoa(pool_indice);
			output._1=null;output._2=temp;
			return output;
		}
		
		return null;
	}
*/
	
}// END MAIN CLASS STATEMENT
