package Conversor;

public class Mili extends Conversor{//polimorfismo do método exibir presente em conversor
	public Mili (double valor) {
		super(valor);
	}
	public double exibir() {
		return 0.001;
	}
}
