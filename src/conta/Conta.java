package conta;
import operador.Cliente;

public abstract class Conta{
	
	int numero;
	double saldo;
	Agencia agencia = new Agencia();
	Cliente cliente = new Cliente();
	String tipoConta;
	int digito;
	
	public Conta(int numero, double saldo, Agencia agencia, Cliente cliente) {
		this.numero = numero;
		this.saldo = saldo;
		this.agencia = agencia;
		this.cliente = cliente;
	}

	public Conta() {
	}
	
}