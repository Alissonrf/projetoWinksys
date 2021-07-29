package br.com.winksys.winksyssb.atividadeDiaria;

import java.util.Collections;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import br.com.winksys.winksyssb.model.entities.AtividadeDiaria;
import br.com.winksys.winksyssb.model.entities.Responsavel;
import br.com.winksys.winksyssb.responsavel.ResponsavelDTO;

public class AtividadeDTO {

	private Long id;

	private String dataCadastro;

	private Date dataPrevista;

	private Date dataConclusao;

	private String descricao;

	private Set<ResponsavelDTO> responsaveis;

	private String prioridade;

	public AtividadeDTO(String dataCadastro, Date dataPrevista, Date dataConclusao, String descricao,
			Set<ResponsavelDTO> responsaveis, String prioridade) {
		this.dataCadastro = dataCadastro;
		this.dataPrevista = dataPrevista;
		this.dataConclusao = dataConclusao;
		this.descricao = descricao;
		this.responsaveis = responsaveis;
		this.prioridade = prioridade;
	}

	public AtividadeDTO() {
	}

	public static AtividadeDTO of(AtividadeDiaria atividadeDiaria) {
		return new AtividadeDTO(atividadeDiaria.getDataCadastro(), atividadeDiaria.getDataPrevista(),
				atividadeDiaria.getDataConclusao(), atividadeDiaria.getDescricao(),
				atividadeDiaria.getResponsaveis().stream().map(ResponsavelDTO::of).collect(Collectors.toSet()),
				atividadeDiaria.getPrioridade());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Date getDataPrevista() {
		return dataPrevista;
	}

	public void setDataPrevista(Date dataPrevista) {
		this.dataPrevista = dataPrevista;
	}

	public Date getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(Date dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Set<ResponsavelDTO> getResponsaveis() {
		return responsaveis;
	}

	public void setResponsaveis(Set<ResponsavelDTO> responsaveis) {
		this.responsaveis = responsaveis;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}

	@Override
	public String toString() {
		return "UsuarioDTO{" + "dataCadastro=" + dataCadastro + "," + " dataPrevista='" + dataPrevista + '\'' + ","
				+ " dataConclusao='" + dataConclusao + '\'' + "," + " descricao='" + descricao + '\'' + ","
				+ " responsaveis='" + responsaveis + '\'' + "," + " prioridade='" + prioridade + '\'' + ",";
	}

}
