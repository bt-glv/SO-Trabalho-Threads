package app;
import java.util.Scanner;

import pools.Pool_mk1;

public class Application 
{

	public static void main(String[] args) 
	{
		Scanner s = new Scanner(System.in);
		
		System.out.println("Digite o tamanho do array : ");
		int tamanho = s.nextInt();
		
		System.out.println("Digite o id do array: ");
		int id = s.nextInt();
		
		// perguntar o tamanho do array
		// perguntar o id do array
		
		Pool_mk1 pool = new Pool_mk1(id, tamanho , null, null);
	}

}
