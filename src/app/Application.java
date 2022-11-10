package app;
import java.util.Scanner;
import models.pool.Pool;


public class Application 
{
	
	private Front front;
	
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
	 
		new Pool(portas,pessoas,largura,autura);
		
		
	}

}
