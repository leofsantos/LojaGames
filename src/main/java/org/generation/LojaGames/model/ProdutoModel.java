package org.generation.LojaGames.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_produto")
public class ProdutoModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "Insira o nome do jogo")
	private String nomeProduto;
	
	@NotNull(message = "Insira o ano de Lançamento do jogo")
	private long anoLancamento;
	
	@NotBlank(message = "Insira o nome da Desenvolvedora do jogo")
	@Size(min=3,max=255)
	private String desenvolvedora;
	
	@NotNull
	@Positive(message = "Insira um valor maior que zero")
	private BigDecimal valor;
	
	@ManyToOne
	@JsonIgnoreProperties("produto")
	private CategoriaModel categoria;	
	
	public CategoriaModel getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaModel categoria) {
		this.categoria = categoria;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public long getAnoLancamento() {
		return anoLancamento;
	}

	public void setAnoLancamento(long anoLancamento) {
		this.anoLancamento = anoLancamento;
	}

	public String getDesenvolvedora() {
		return desenvolvedora;
	}

	public void setDesenvolvedora(String desenvolvedora) {
		this.desenvolvedora = desenvolvedora;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
}
