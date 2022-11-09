package models;

public class Pessoa 
{
	public final int id;
	private int id_usuario=0;
	private Boolean ja_foi_resgatado;
	
	public Pessoa(int id) {
		this.id = id;
	}
	
	
	
	public Boolean getResgatado() 
	{
		return ja_foi_resgatado;
	}
	public Boolean setResgatado(int id_usuario, Boolean status) 
	{
		if(id_usuario==0||this.id_usuario!=id_usuario) {return false;}
		ja_foi_resgatado=status;
		return true;
	}
	
	
	
	
	
	public int emUsoPor() 
	{
		return id_usuario;
	} 
	public synchronized Boolean requisitar_uso(int id_usuario) 
	{
		if(id_usuario==0) {this.id_usuario=id_usuario; return true;}
		return false;
	}
	public Boolean liberar_uso (int id_usuario) 
	{
		if(id_usuario==0||this.id_usuario!=id_usuario) {return false;}
		this.id_usuario=0;
		return true;
	}
}
