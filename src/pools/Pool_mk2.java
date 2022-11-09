package pools;
import java.util.ArrayList;
import java.util.Random;
import models.*; import portas.*;
import interfaces.*;



public class Pool_mk2 
{
		private ArrayList<Porta> portas = new ArrayList<Porta> ();
	
		private Pessoa_Thread[] pool;
		private ArrayList<Integer> pool_posicoes_ocupadas = new ArrayList<Integer> ();
		private Log logger;

		private Boolean flag_PrimeiraVez_movimentar_p=true;
		private Boolean flag_PrimeiraVez_adicionar=true;

/*	
		
*/
	
	public Pool_mk2(int tamanho_da_pool, int quantidade_portas, Log logger, Pessoa_Thread[] pessoas) 
	{
		if(pessoas==null)		{throw new java.lang.Error("Pool =>\nO valor do parametro Pessoa_Thread e nulo");}
		if(pessoas.length==0)	{throw new java.lang.Error("Pool =>\nNao existem pessoas para adicionar a thread");}
	
	
		this.pool=new Pessoa_Thread[tamanho_da_pool];
		this.logger=logger;
	
		
		for(int i=0; i!=pessoas.length;i++)
		{
			pool_adicionar_pessoa(pessoas[i]);
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
