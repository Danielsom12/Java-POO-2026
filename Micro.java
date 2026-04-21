package Conversor;

public class Micro extends Conversor {//polimorfismo do método exibir presente em conversor
    public Micro(double valor) { 
    	super(valor); //o 0 em Conversor objDestino = selecionarEscala(escDestino, 0); está guardado aqui
    	}
    public double exibir() { //mas na classe conversor, o método de cálculo faz a conta com base no exibir, logo, "ignora" aquele zero e pega esse valor
    	return 0.000001; 
    	}
}
