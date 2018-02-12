package com.appspot.dmutti.calculator.pojo;

import javax.persistence.*;
import org.apache.commons.lang.builder.*;

@Table(name="donos")
@Entity
public class Dono {
    
    @Column(name="nome_exibicao")
    private String nomeExibicao;
    
    @Id
    private String email;
    private boolean doador;
    
    public Dono() {}
    public Dono(String email, String nomeExibicao, boolean doador) {
        this.email = email;
        this.nomeExibicao = nomeExibicao;
        this.doador = doador;
    }
    
    
    public String getNomeExibicao() {
        return nomeExibicao;
    }
    public void setNomeExibicao(String nomeExibicao) {
        this.nomeExibicao = nomeExibicao;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public boolean isDoador() {
        return doador;
    }
    public void setDoador(boolean doador) {
        this.doador = doador;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Dono == false) {
            return false;
          }
          if (this == obj) {
            return true;
          }
          Dono rhs = (Dono) obj;
          return new EqualsBuilder()
                        .appendSuper(super.equals(obj))
                        .append(email, rhs.email)
                        .isEquals();
    }
    
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(email)
            .toHashCode();
    }
}
