package conta;
import operador.Gerente;

public class Agencia {
	int id;
	
	Gerente gerente = new Gerente();
	
	public Agencia(int id, Gerente gerente) {
        this.id = id;
        this.gerente = gerente;
    }
	
	public Agencia() {
	}
}
