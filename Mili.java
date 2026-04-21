package Conversor;

//Polimorfismo do método exibir presente em conversor
public class Mili extends Conversor{
	public Mili (double valor) {
		super(valor);
	}
	public double exibir() {
		return 0.001;
	}
}
