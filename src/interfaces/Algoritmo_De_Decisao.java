package interfaces;
import java.util.ArrayList;
import models.*;



public interface Algoritmo_De_Decisao {

	public int run(ArrayList<Integer> posicoes_ocupadas, Pessoa[] pool_array);
	public int run(ArrayList<Integer> posicoes_ocupadas);
}
