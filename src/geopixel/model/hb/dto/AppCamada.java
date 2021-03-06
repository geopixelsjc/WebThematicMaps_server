package geopixel.model.hb.dto;

// default package
// Generated 15/07/2015 12:59:24 by Hibernate Tools 4.0.0

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * AppCamada generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "app_camada")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AppCamada implements java.io.Serializable{

	private int cmdId;
	private String nome;
	private String nomeTabelaGeo;
	private String fonteDado;
	private Set<AppTema> appTemas = new HashSet<AppTema>(0);

	public AppCamada() {
	}

	public AppCamada(int cmdId) {
		this.cmdId = cmdId;
	}

	public AppCamada(int cmdId, String nome, String nomeTabelaGeo,
			String fonteDado, Set<AppTema> appTemas) {
		this.cmdId = cmdId;
		this.nome = nome;
		this.nomeTabelaGeo = nomeTabelaGeo;
		this.fonteDado = fonteDado;
		this.appTemas = appTemas;
	}

	@Id
	@Column(name = "cmd_id", unique = true, nullable = false)
	public int getCmdId() {
		return this.cmdId;
	}

	public void setCmdId(int cmdId) {
		this.cmdId = cmdId;
	}

	@Column(name = "nome")
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "nome_tabela_geo")
	public String getNomeTabelaGeo() {
		return this.nomeTabelaGeo;
	}

	public void setNomeTabelaGeo(String nomeTabelaGeo) {
		this.nomeTabelaGeo = nomeTabelaGeo;
	}

	@Column(name = "fonte_dado")
	public String getFonteDado() {
		return this.fonteDado;
	}

	public void setFonteDado(String fonteDado) {
		this.fonteDado = fonteDado;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "appCamada")
	public Set<AppTema> getAppTemas() {
		return this.appTemas;
	}

	public void setAppTemas(Set<AppTema> appTemas) {
		this.appTemas = appTemas;
	}

}
