package operador;

public class Cliente extends Pessoa {
	OperadorEnum cliente = OperadorEnum.CLIENTE;
	private int idAgencia;
    private final String cargo = cliente.name();
    private final int digito = cliente.ordinal();


	public Cliente() {
	}
	public Cliente(String nome, int cpf, int senha) {
		super(nome, cpf, senha);
	}
	@Override
	public String getTipo() {
		return cargo;
	}
	
}
