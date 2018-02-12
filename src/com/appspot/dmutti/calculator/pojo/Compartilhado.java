package com.appspot.dmutti.calculator.pojo;

import javax.persistence.*;

import org.apache.commons.lang.builder.*;

@Entity
@Table(name="compartilhados")
public class Compartilhado {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    private String valor;
    private String descricao;
    private long idConta;
    
    public Compartilhado() {}
    
    public Compartilhado(String line) {
        descricao = line.substring(11,71).trim();
        valor = line.substring(72).trim().replaceAll(",", ".");
        if (valor.endsWith("-")) {
            valor = "-" + valor.substring(0, valor.length()-1);
        }
    }
    
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getValor() {
        return this.valor;
    }
    public void setValor(String valor) {
        this.valor = valor;
    }
    public String getDescricao() {
        return this.descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public long getIdConta() {
        return this.idConta;
    }
    public void setIdConta(long idConta) {
        this.idConta = idConta;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Compartilhado == false) {
            return false;
          }
          if (this == obj) {
            return true;
          }
          Compartilhado rhs = (Compartilhado) obj;
          return new EqualsBuilder()
                        .appendSuper(super.equals(obj))
                        .append(valor, rhs.valor)
                        .append(descricao, descricao)
                        .append(idConta, idConta)
                        .isEquals();
    }
    
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(valor)
            .append(descricao)
            .append(idConta)
            .toHashCode();
    }
}
