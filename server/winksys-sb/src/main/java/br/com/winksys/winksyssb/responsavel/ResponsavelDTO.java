package br.com.winksys.winksyssb.responsavel;

import br.com.winksys.winksyssb.model.entities.Responsavel;

/**
 * Classe para tráfego das informações do usuário
 */
public class ResponsavelDTO {
	private String cpf;
	private String nome;

	public ResponsavelDTO() {
	}

	public ResponsavelDTO(String cpf, String nome) {
		this.cpf = cpf;
		this.nome = nome;
	}

	public static ResponsavelDTO of(Responsavel responsavel) {
		return new ResponsavelDTO(responsavel.getNome(), responsavel.getCpf());
	}

	public String getCpf() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "UsuarioDTO{" + "cpf=" + cpf + ","
				+ " nome='" + nome + '\'' + '}';
	}
}