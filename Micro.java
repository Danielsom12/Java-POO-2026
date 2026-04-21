package Conversor;

//Polimorfismo do método exibir presente em conversor
public class Micro extends Conversor {
    public Micro(double valor) { 
    	super(valor); //o 0 em Conversor objDestino = selecionarEscala(escDestino, 0); está guardado aqui
    	}
    
  //Mas na classe conversor, o método de cálculo faz a conta com base no exibir, logo, "ignora" aquele zero e pega esse valor
    public double exibir() { 
    	return 0.000001; 
    	}
}
