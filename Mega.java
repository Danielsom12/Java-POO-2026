package Conversor;

public class Mega extends Conversor {//polimorfismo do método exibir presente em conversor
    public Mega(double valor) { super(valor); }
    public double exibir() {
    	return 1000000.0;
    }
}
