package Conversor;

//Polimorfismo do método exibir presente em conversor
public class Mega extends Conversor {
    public Mega(double valor) { super(valor); }
    public double exibir() {
    	return 1000000.0;
    }
}
