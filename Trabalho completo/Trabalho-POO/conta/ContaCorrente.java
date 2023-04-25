package conta;

import operador.Cliente;
import java.util.Scanner;

public class ContaCorrente extends Conta implements Comparable {

	Scanner sc = new Scanner(System.in);

	ContasEnum tipo = ContasEnum.CORRENTE;
	public static double taxaSaqDep = 0.10;
	public static double taxaTransf = 0.20;

	public ContaCorrente() {
		super();

	}

	public ContaCorrente(int numero, double saldo, Cliente cliente) {
		super(numero, saldo, cliente);

	}

	public void Saque(double valor) {
		if (valor <= this.getSaldo() - taxaSaqDep && valor > 0) {
			this.setSaldo(this.getSaldo() - valor - taxaSaqDep);
		} else if (valor < 0.0) {
			System.out.println("Valor invalido");
		} else {
			System.out.println("Saldo insuficiente");
		}
	}

	public void Deposito(double valor) {
		if (valor > 0) {
			this.setSaldo(this.getSaldo() + valor - taxaSaqDep);
		} else {
			System.out.println("Valor invalido");
		}
	}

	public void Transferir(Conta conta, double valor) {
		if (valor <= this.getSaldo() - taxaTransf && valor > 0) {
			conta.setSaldo(conta.getSaldo() + valor);
			this.setSaldo(this.getSaldo() - valor - taxaTransf);
		} else if (valor <= 0.0) {
			System.out.println("Valor invalido");
		} else {
			System.out.println("Saldo insuficiente");
		}
	}

	@Override
	public ContasEnum getTipoConta() {
		return tipo;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		return "Conta [numero=" + this.getNumero() + ", saldo=" + this.getSaldo() + "]";
	}

}