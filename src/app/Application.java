package app;
import java.util.ArrayList;
import java.util.Scanner;
import models.pool.Pool;


public class Application 
{
	
	private static Front front = new Front();
	
	public static void main(String[] args) 
	{
		int autura, largura, portas , pessoas;
		Scanner ip = new Scanner(System.in);
		System.out.println("incira a largura");
		largura = ip.nextInt();
		System.out.println("incira a autura");
		autura = ip.nextInt();
		System.out.println("incira a portas");
		portas = ip.nextInt();
		System.out.println("incira a pessoas");
		pessoas = ip.nextInt();
	 
		new Pool(portas,pessoas,largura,autura,front);
		
		
		int[][] matriz = new int[autura][largura];
		ArrayList<int[]> lista = new ArrayList<int[]> ();
		lista.add(new int[] {5,7});
		lista.add(new int[] {2,5});
		lista.add(new int[] {7,2});
		
		
	
		front.receber(matriz, lista);
		
		
	}

}
