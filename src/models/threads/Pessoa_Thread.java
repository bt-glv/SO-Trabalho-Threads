package models.threads;

public class Pessoa_Thread extends Thread
{
	private final int id_pessoa;
	
	public int getId_pessoa() {
		return id_pessoa;
	}

	public Pessoa_Thread(int id_pessoa) {
		super();
		this.id_pessoa = id_pessoa;
	}
	
	
}
