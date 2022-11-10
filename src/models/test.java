package models;

public class test
{
	public int id;
	private test self;

	public test(test self) {this.self=self;}
	
	public void setSelf(test t) {this.self=t;}
	public test getSelf() {return this.self;}
}
