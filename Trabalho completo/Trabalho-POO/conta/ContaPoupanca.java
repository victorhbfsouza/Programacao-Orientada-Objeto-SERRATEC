package conta;

import java.util.Scanner;

import operador.Cliente;

public class ContaPoupanca extends Conta {

	Scanner sc = new Scanner(System.in);

	ContasEnum tipo = ContasEnum.POUPANCA;
	public static double rendimento = 0.005;

	public ContaPoupanca() {
		super();
	}

	public ContaPoupanca(int numero, double saldo, Cliente cliente) {
		super(numero, saldo, cliente);
	}

	public void Saque(double valor) {
		if (valor <= this.getSaldo() && valor > 0) {
			this.setSaldo(this.getSaldo() - valor);
		} else if (valor <= 0.0) {
			System.out.println("Valor invalido");
		} else {
			System.out.println("Saldo insuficiente");
		}
	}

	public void Deposito(double valor) {
		if (valor > 0) {
			this.setSaldo(this.getSaldo() + valor);
		} else {
			System.out.println("Valor invalido");
		}
	}

	public void Transferir(Conta conta, double valor) {
		if (valor <= this.getSaldo() && valor > 0) {
			conta.setSaldo(conta.getSaldo() + valor);
			this.setSaldo(this.getSaldo() - valor);
		} else if (valor <= 0) {
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
	public String toString() {
		return "Conta [numero=" + this.getNumero() + ", saldo=" + this.getSaldo() + "]";
	}

}