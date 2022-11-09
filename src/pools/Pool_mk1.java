package pools;import java.util.ArrayList;
import java.util.Random;

import models.*;
import interfaces.*;
/*
 * 
 * Notas
 * 
 * 
 * Nessa aproximacao, caso seja feito um mecanismo de sinal
 * nos objetos 'pessoas'
 */
public class Pool_mk1 
{
	public final int id;
	private final Log logger;
	private Pessoa[] pool;
	private ArrayList<Integer> pool_posicoes_ocupadas = new ArrayList<Integer> ();
	
	private Boolean flag_PrimeiraVez_movimentar_p=true;
	public Boolean pool_esta_vazia() {
		if(pool_posicoes_ocupadas.size()==0) {return true;}
		return false;
	}
	
	
	public Pool_mk1(int id_da_pool, int tamanho_pool, Pessoa[] pessoas, Log logger)
	{
		this.id=id_da_pool;
		this.logger=logger;
		
		pool = new Pessoa[tamanho_pool];
		
		for(int i=0; i!=pessoas.length; i++) 	
		{
			pool_inserir_pessoa(pessoas[i]);
		}
	}

	public void pool_movimentar_pessoas(int tempo_milisegundos) 
	{if(!flag_PrimeiraVez_movimentar_p) {return;}
	 if(pool_posicoes_ocupadas.size()==0)	{throw new java.lang.Error("Metodo: pool_movimentar_pessoas =>\nNao ha pessoas para movimentar");}



		long startTime = System.currentTimeMillis();
		Random r = new Random();
		
		Pessoa swap;
		int indice_inicial;
		int indice_alvo=0;
		
		while((System.currentTimeMillis()-startTime)<tempo_milisegundos) 
		{
			indice_inicial=pool_posicoes_ocupadas.get(r.nextInt(pool_posicoes_ocupadas.size()));
			indice_alvo=r.nextInt(pool.length);
			
			if(pool[indice_alvo]==null) 
			{
				pool[indice_alvo]=pool[indice_inicial];
				pool_posicoes_ocupadas.remove(indice_inicial);
				
				pool[indice_inicial]=null;
				pool_posicoes_ocupadas.add(indice_alvo);
			}
			else
			{
				swap=pool[indice_alvo];
				
				pool[indice_alvo]=pool[indice_inicial];
				pool[indice_inicial]=swap;
			}
			
		}
		
		flag_PrimeiraVez_movimentar_p=false;
	}




	// So pode ser usado antes da funcao "pool_movimentar_pessoas" 
	private void pool_inserir_pessoa(Pessoa p)
	{if(!flag_PrimeiraVez_movimentar_p) {return;}
		
		if(pool_posicoes_ocupadas.size()==pool.length) {throw new java.lang.Error("Tentativa de inserir Itens em uma pool cheia.\nId da pool: "+this.id);}
		
		pool[pool_posicoes_ocupadas.size()]=p;
		pool_posicoes_ocupadas.add(pool_posicoes_ocupadas.size());
		
	}

	private Pessoa pool_remover_pessoa(int pool_indice) 
	{
		if(pool[pool_indice]==null) {return null;}
		
		Pessoa output = pool[pool_indice];
		pool[pool_indice]=null;
		pool_posicoes_ocupadas.remove(Integer.valueOf(pool_indice));
		
		return output;
	}





//	Usar essa funcao caso a regiao critica seja o array 'pool'
//	
	public synchronized Pessoa resgatar_pessoa(Algoritmo_De_Decisao a, int id_thread) 
	{
		Pessoa output;
		logger.receber("Thread de id:"+id_thread+" acessou a pool de id: "+id_thread, this.pool);

		int pessoa_selecionada = a.run(pool_posicoes_ocupadas, pool);
		output =pool_remover_pessoa(pessoa_selecionada);
		logger.receber("Thread de id:"+id_thread+" selecionou a pessoa de id: "+output.id, this.pool);

		logger.receber("Thread de id:"+id_thread+" resgatou a pessoa de id: "+output.id, this.pool);
		return output;
	}
	
//	Usar essa funcao caso a regiao critica seja os objetos da classe pessoa
//	
	public Pessoa resgatar_pessoa(int id_thread) {
		
		Pessoa dummy;
		for(int i=0; i!=pool_posicoes_ocupadas.size(); i++) {
			
			dummy=pool[pool_posicoes_ocupadas.get(i)];
			if(dummy.emUsoPor()==0) {
				
				if(dummy.requisitar_uso(id_thread)) {
					return pool_remover_pessoa(pool_posicoes_ocupadas.get(i));
				}
				
			}
		}
		
		return null;
	}
/*	
	public synchronized Tuple<int[], Pessoa> thread_action(int action, int pool_indice)
	{
		Tuple<int[], Pessoa> output = new Tuple<int[], Pessoa> ();
		
		if(action==1) 
		{
			int[] temp = pool_listar_pool_posicoes_ocupadas();
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
		
		
	private int[] pool_listar_pool_posicoes_ocupadas() 
	{
		int[] output = new int[pool_posicoes_ocupadas.size()];
		
		for(int i=0; i!=pool_posicoes_ocupadas.size();i++)
		{
			output[i]=pool_posicoes_ocupadas.get(i);
		}
		
		return output;
	}
		
		
*/
	
}// END MAIN CLASS STATEMENT
