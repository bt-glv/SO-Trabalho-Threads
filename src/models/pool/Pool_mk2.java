package models.pool;
import java.util.ArrayList;
import java.util.Random;
import models.*;
import models.porta.*;
import models.threads.Pessoa_Thread;
import interfaces.*;



public class Pool_mk2 
{
		private ArrayList<Porta> portas = new ArrayList<Porta> ();
	
		private Pessoa_Thread[] pool;
		private ArrayList<Integer> pool_posicoes_ocupadas = new ArrayList<Integer> ();
		private Log_mk2 logger;

		private Boolean flag_PrimeiraVez_movimentar_p=true;
		private Boolean flag_PrimeiraVez_adicionar=true;

/*	
		
*/
	
	public Pool_mk2(int tamanho_da_pool, int quantidade_portas, Log_mk2 logger, Pessoa_Thread[] pessoas) 
	{
		if(pessoas==null)		{throw new java.lang.Error("Pool =>\nO valor do parametro Pessoa_Thread e nulo");}
		if(pessoas.length==0)	{throw new java.lang.Error("Pool =>\nNao existem pessoas para adicionar a thread");}
	
	
		this.pool=new Pessoa_Thread[tamanho_da_pool];
		this.logger=logger;
	
		
		for(int i=0; i!=pessoas.length;i++)
		{
			pool_adicionar_pessoa(pessoas[i]);
		}
	
		for(int i=0; i<quantidade_portas;i++)
		{
			portas.add(new Porta());
		}
		
		logger.receber("A pool foi instancializada", null);
	}
	private void pool_adicionar_pessoa(Pessoa_Thread p) 
	{
		if(!flag_PrimeiraVez_adicionar) {return;}
		int indice = pool_posicoes_ocupadas.size();
		
		pool[indice]=p;
		pool_posicoes_ocupadas.add(Integer.valueOf(indice));
		
		flag_PrimeiraVez_adicionar=false;
	}



	public void pool_iniciarThreadPessoa() 
	{
		for(int i=0; i!=pool.length; i++)
		{
			pool[i].run();
		}
	}
	public Pessoa_Thread pool_removerPessoa(int id_pessoa, int id_porta) 
	{
		int pool_indice;
		Pessoa_Thread pessoa;
		for(int i=0; i!=pool_posicoes_ocupadas.size();i++) 
		{
			pool_indice=pool_posicoes_ocupadas.get(i);
			if(id_pessoa==pool[pool_indice].getId_pessoa()) 
			{
				pessoa=pool[pool_indice];
				pool[pool_indice]=null;
				pool_posicoes_ocupadas.remove(Integer.valueOf(pool_indice));
				
				logger.receber("Pessoa de id: "+id_pessoa+" saiu da pool e agora se encontra na porta de id: "+id_porta, this.pool);
				return pool[pool_posicoes_ocupadas.get(i)];
			}
		}
		
		return null;
	}
	
	public void pool_movimentar_pessoas(int tempo_milisegundos) 
	{if(!flag_PrimeiraVez_movimentar_p) {return;}
	 if(pool_posicoes_ocupadas.size()==0)	{throw new java.lang.Error("Metodo: pool_movimentar_pessoas =>\nNao ha pessoas para movimentar");}



		long startTime = System.currentTimeMillis();
		Random r = new Random();
		
		Pessoa_Thread swap;
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
		//	
		flag_PrimeiraVez_movimentar_p=false;
	}
}
