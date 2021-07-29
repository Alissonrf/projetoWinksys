package br.com.winksys.winksyssb.model.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


@Entity
public class AtividadeDiaria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String dataCadastro; 
	
	private Date dataPrevista;

	private Date dataConclusao;

	private String descricao;

	@ManyToMany
	@JoinTable(name = "atividadeDiaria_responsavel", 
		joinColumns = {@JoinColumn(name = "atividade_diaria_id")},
		inverseJoinColumns = {@JoinColumn(name = "responsavel_id")})
	private Set<Responsavel> responsaveis;

	private String prioridade;

	public AtividadeDiaria() {
	}

	public AtividadeDiaria(String nome, String dataCadastro, Date dataPrevista, Date dataConclusao,
			String descricao, Set<Responsavel> responsaveis, String prioridade) {
		super();
		this.dataCadastro = dataCadastro;
		this.dataPrevista = dataPrevista;
		this.dataConclusao = dataConclusao;
		this.descricao = descricao;
		this.responsaveis = responsaveis;
		this.prioridade = prioridade;
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

	public Set<Responsavel> getResponsaveis() {
		return responsaveis;
	}

	public void setResponsaveis(Set<Responsavel> responsaveis) {
		this.responsaveis = responsaveis;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}

}
