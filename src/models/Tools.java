package models;
import java.util.ArrayList;

public class Tools {

public static <T> Boolean existem_duplicados(ArrayList<T> colllection) 
{

	ArrayList<T> temp = new ArrayList<T> ();
	
	for(int i=0; i!=colllection.size(); i++)
	{
		if(temp.add(colllection.get(i))==false) {return true;}
		
	}
	
	return false;
}








}
