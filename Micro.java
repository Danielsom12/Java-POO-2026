package Conversor;

public class Micro extends Conversor {//polimorfismo do método exibir presente em conversor
    public Micro(double valor) { super(valor); }
    public double exibir() { return 0.000001; }
}
