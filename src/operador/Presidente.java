package operador;

public class Presidente extends Diretor {
	OperadorEnum presidente = OperadorEnum.PRESIDENTE;
	private int idAgencia;
    private final String cargo = presidente.name();
    private final int digito = presidente.ordinal();


	public Presidente() {
	}
		
	public Presidente(String nome, int cpf, int senha) {
		super(nome, cpf, senha);
	}

}
