package app;
import java.util.ArrayList;
import java.util.Scanner;

import models.pool.Pool;


public class Application 
{
	
	private static Front front = new Front();
	
	public static void main(String[] args) 
	{
		int altura, largura, portas , pessoas;
		Scanner ip = new Scanner(System.in);
		System.out.println("Insira a largura:");
		largura = ip.nextInt();
		System.out.println("\n\n");

		System.out.println("Insira a altura:");
		altura = ip.nextInt();
		System.out.println("\n\n");

		System.out.println("Insira a quantidade de portas:");
		portas = ip.nextInt();
		System.out.println("\n\n");

		System.out.println("Insira a quantidade de pessoas:");
		pessoas = ip.nextInt();
		System.out.println("\n\n");

	 
		Pool main_pool = new Pool(portas,pessoas,largura,altura,front);
		main_pool.finish_setup(main_pool);
		front.receber(main_pool.array_pool, main_pool.Portas);
		
		int[][] matriz = new int[altura][largura];
		ArrayList<int[]> lista = new ArrayList<int[]> ();
		lista.add(new int[] {5,7});
		lista.add(new int[] {2,5});
		lista.add(new int[] {7,2});
		
		
	
		front.receber(matriz, lista);
	/*	
		test t = new test(t);
		t.id=10;
		
		test out = t.getSelf();
		
		
		System.out.println(out.id);
		
	 */
	}
}
