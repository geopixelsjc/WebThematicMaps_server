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
import org.codehaus.jackson.annotate.JsonManagedReference;

/**
 * AppUsuario generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "app_usuario")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AppUsuario implements java.io.Serializable{

	private int usrId;
	private String nome;
	private String cpf;
	private String email;
	private String telefone1;
	private String telefone2;
	private Set <AppEndereco> appEnderecos = new HashSet<AppEndereco>(0);
	private Set <AppAcesso> appAcessos = new HashSet<AppAcesso>(0);
	private Set <AppUsarioxprefil> appUsarioxprefils = new HashSet<AppUsarioxprefil>(0);

	public AppUsuario() {
	}

	public AppUsuario(int usrId, String nome) {
		this.usrId = usrId;
		this.nome = nome;
	}

	public AppUsuario(int usrId, String nome, String cpf, String email,
			String telefone1, String telefone2, Set<AppEndereco> appEnderecos,
			Set<AppAcesso> appAcessos, Set<AppUsarioxprefil> appUsarioxprefils) {
		this.usrId = usrId;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.telefone1 = telefone1;
		this.telefone2 = telefone2;
		this.appEnderecos = appEnderecos;
		this.appAcessos = appAcessos;
		this.appUsarioxprefils = appUsarioxprefils;
	}

	@Id
	@Column(name = "usr_id", unique = true, nullable = false)
	public int getUsrId() {
		return this.usrId;
	}

	public void setUsrId(int usrId) {
		this.usrId = usrId;
	}

	@Column(name = "nome", nullable = true, length = 100)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "cpf", length = 15)
	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Column(name = "email")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "telefone1", length = 25)
	public String getTelefone1() {
		return this.telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	@Column(name = "telefone2", length = 25)
	public String getTelefone2() {
		return this.telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "appUsuario")
	@JsonManagedReference
	public Set<AppEndereco> getAppEnderecos() {
		return this.appEnderecos;
	}

	public void setAppEnderecos(Set<AppEndereco> appEnderecos) {
		this.appEnderecos = appEnderecos;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "appUsuario")
	@JsonManagedReference
	public Set<AppAcesso> getAppAcessos() {
		return this.appAcessos;
	}

	public void setAppAcessos(Set<AppAcesso> appAcessos) {
		this.appAcessos = appAcessos;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "appUsuario")
	@JsonManagedReference
	public Set<AppUsarioxprefil> getAppUsarioxprefils() {
		return this.appUsarioxprefils;
	}

	public void setAppUsarioxprefils(Set<AppUsarioxprefil> appUsarioxprefils) {
		this.appUsarioxprefils = appUsarioxprefils;
	}

}