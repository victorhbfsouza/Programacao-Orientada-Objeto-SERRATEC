package operador;

public class Diretor extends Gerente {
	OperadorEnum diretor = OperadorEnum.DIRETOR;
	private int idAgencia;
    private final String cargo = diretor.name();
    private final int digito = diretor.ordinal();

	public Diretor() {
	}
	
	public Diretor(String nome, int cpf, int senha) {
		super(nome, cpf, senha);
	}

}
