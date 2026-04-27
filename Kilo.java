package Conversor;

// Subclasse para a escala Kilo (multiplicador 1.000)
public class Kilo extends Conversor {
	public Kilo(double valor) {
		super(valor);
	}

	public double exibir() {
		return 1000.0;
	}
}