package conta;

import operador.Cliente;

public abstract class Conta {
	private int numero;
	private double saldo;
	// A relação de cliente para contas é um para muitos, por isso registra-se o
	// cliente dentro da conta e não o inverso
	private Cliente cliente = new Cliente();

	public Conta(int numero, double saldo, Cliente cliente) {
		this.numero = numero;
		this.saldo = saldo;
		this.cliente = cliente;
	}

	public Conta() {
	}

	public abstract ContasEnum getTipoConta();

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public abstract void Saque(double valor);

	public abstract void Deposito(double valor);

	public abstract void Transferir(Conta conta, double valor);

	@Override
	public String toString() {
		return "Conta [numero=" + numero + ", saldo=" + saldo + ", cliente=" + cliente + "]";
	}

}