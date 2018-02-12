package com.appspot.dmutti.calculator.pojo;

import javax.persistence.*;

import org.apache.commons.lang.builder.*;

@Table(name="contas")
@Entity
public class Conta {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    private int mes;
    private int ano;
    
    public Conta(int mes, int ano) {
        this.mes = mes;
        this.ano = ano;
    }
    
    public Conta() {}
    
    public Conta(String arg) {
        String[] values = arg.split("_");
        this.mes = Integer.valueOf(values[0]);
        this.ano = Integer.valueOf(values[1]);
    }
    
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setMes(int mes) {
        this.mes = mes;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }
    public int getMes() {
        return this.mes;
    }
    public int getAno() {
        return this.ano;
    }

    @Override
    public String toString() {
        return mes + "_" + ano;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Conta == false) {
            return false;
          }
          if (this == obj) {
            return true;
          }
          Conta rhs = (Conta) obj;
          return new EqualsBuilder()
                        .appendSuper(super.equals(obj))
                        .append(mes, rhs.mes)
                        .append(ano, rhs.ano)
                        .isEquals();
    }
    
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(mes)
            .append(ano)
            .toHashCode();
    }
}
