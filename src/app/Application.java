package app;
import java.util.Scanner;

import models.pool.Pool_mk2;
import models.threads.Pessoa_Thread;

public class Application 
{

	public static void main(String[] args) 
	{
		
	/*	
		Scanner s = new Scanner(System.in);
		// perguntar o tamanho do array
		System.out.println("Digite o tamanho do array : ");
		int tamanho = s.nextInt();
		// perguntar o id do array
		System.out.println("Digite o id do array: ");
		int id = s.nextInt();
		System.out.println("Digite o o tanto de pessoas: ");
		Pessoa_Thread[] pessoas = new Pessoa_Thread[s.nextInt()];

		
		System.out.println(tamanho+ id );
		
		Pool_mk2 pool = new Pool_mk2(id, tamanho , new Front(), pessoas);
		*/
		
		Pool_mk2 test = new Pool_mk2(10,2,new Front(), new Pessoa_Thread[] {new Pessoa_Thread(11), new Pessoa_Thread(12)});
		System.out.println(test.pool);
		
		for(int i=0; i!=test.pool_posicoes_ocupadas.size(); i++) {
			System.out.println(test.pool[i].getId_pessoa());
		}
	}

}
