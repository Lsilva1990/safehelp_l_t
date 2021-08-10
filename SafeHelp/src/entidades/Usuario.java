/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author lsilva
 */
@Entity
@Table(name = "usuario")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByUsuarioId", query = "SELECT u FROM Usuario u WHERE u.usuarioId = :usuarioId"),
    @NamedQuery(name = "Usuario.findByUsuarioNome", query = "SELECT u FROM Usuario u WHERE u.usuarioNome = :usuarioNome"),
    @NamedQuery(name = "Usuario.findByUsuarioCpf", query = "SELECT u FROM Usuario u WHERE u.usuarioCpf = :usuarioCpf"),
    @NamedQuery(name = "Usuario.findByUsuarioEmail", query = "SELECT u FROM Usuario u WHERE u.usuarioEmail = :usuarioEmail"),
    @NamedQuery(name = "Usuario.findByUsuarioSenha", query = "SELECT u FROM Usuario u WHERE u.usuarioSenha = :usuarioSenha"),
    @NamedQuery(name = "Usuario.findByUsuarioEndereco", query = "SELECT u FROM Usuario u WHERE u.usuarioEndereco = :usuarioEndereco"),
    @NamedQuery(name = "Usuario.findByUsuarioTelefone", query = "SELECT u FROM Usuario u WHERE u.usuarioTelefone = :usuarioTelefone"),
    @NamedQuery(name = "Usuario.login", query = "SELECT u FROM User u WHERE u.email = :email and u.password = :password")})

public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "usuario_id")
    private Integer usuarioId;
    @Basic(optional = false)
    @Column(name = "usuario_nome")
    private String usuarioNome;
    @Basic(optional = false)
    @Column(name = "usuario_cpf")
    private String usuarioCpf;
    @Basic(optional = false)
    @Column(name = "usuario_email")
    private String usuarioEmail;
    @Basic(optional = false)
    @Column(name = "usuario_senha")
    private String usuarioSenha;
    @Column(name = "usuario_endereco")
    private String usuarioEndereco;
    @Column(name = "usuario_telefone")
    private String usuarioTelefone;

    public Usuario() {
    }

    public Usuario(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Usuario(Integer usuarioId, String usuarioNome, String usuarioCpf, String usuarioEmail, String usuarioSenha) {
        this.usuarioId = usuarioId;
        this.usuarioNome = usuarioNome;
        this.usuarioCpf = usuarioCpf;
        this.usuarioEmail = usuarioEmail;
        this.usuarioSenha = usuarioSenha;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsuarioNome() {
        return usuarioNome;
    }

    public void setUsuarioNome(String usuarioNome) {
        this.usuarioNome = usuarioNome;
    }

    public String getUsuarioCpf() {
        return usuarioCpf;
    }

    public void setUsuarioCpf(String usuarioCpf) {
        this.usuarioCpf = usuarioCpf;
    }

    public String getUsuarioEmail() {
        return usuarioEmail;
    }

    public void setUsuarioEmail(String usuarioEmail) {
        this.usuarioEmail = usuarioEmail;
    }

    public String getUsuarioSenha() {
        return usuarioSenha;
    }

    public void setUsuarioSenha(String usuarioSenha) {
        this.usuarioSenha = usuarioSenha;
    }

    public String getUsuarioEndereco() {
        return usuarioEndereco;
    }

    public void setUsuarioEndereco(String usuarioEndereco) {
        this.usuarioEndereco = usuarioEndereco;
    }

    public String getUsuarioTelefone() {
        return usuarioTelefone;
    }

    public void setUsuarioTelefone(String usuarioTelefone) {
        this.usuarioTelefone = usuarioTelefone;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioId != null ? usuarioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.usuarioId == null && other.usuarioId != null) || (this.usuarioId != null && !this.usuarioId.equals(other.usuarioId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Usuario[ usuarioId=" + usuarioId + " ]";
    }
    
}
