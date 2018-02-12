package com.appspot.dmutti.calculator.pojo;

import java.util.*;
import javax.persistence.*;
import org.apache.commons.lang.builder.*;
import com.appspot.dmutti.calculator.support.*;

@Entity
@Table(name="ligacoes")
public class Ligacao {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    private Date data;
    private String telefone;
    
    @Column(name="cidade_area")
    private String cidadeArea;
    
    @Column(name="operadora_estado")
    private String operadoraEstado;
    private String duracao;
    private String tipo;
    
    private String valor;
    
    private String dono;
    
    @Column(name="id_conta")
    private long idConta;
    
    public Ligacao() {}
    
    public Ligacao(String linha) {
        
        telefone = linha.substring(14,22);
        if (telefone.matches("([7-9][0-9]{7})")) {
            cidadeArea = linha.substring(25,33);
            operadoraEstado = linha.substring(34,44).trim();
            
        } else {
            cidadeArea = linha.substring(25,41).trim();
            operadoraEstado = linha.substring(41,43).trim();
        }
        
        String horario = linha.substring(44,52);
        try {
            data = new Date(Helper.ligacaoFormat.parseMillis(linha.substring(4,12) + " " + horario));            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        duracao = linha.substring(52,62).trim().replace(",", ".");
        tipo = linha.substring(62,80).trim();
        valor = linha.substring(80).trim().replace(",", ".");
    }
    
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Date getData() {
        return this.data;
    }
    public void setData(Date data) {
        this.data = data;
    }
    public String getTelefone() {
        return this.telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public String getCidadeArea() {
        return this.cidadeArea;
    }
    public void setCidadeArea(String cidadeArea) {
        this.cidadeArea = cidadeArea;
    }
    public String getOperadoraEstado() {
        return this.operadoraEstado;
    }
    public void setOperadoraEstado(String operadoraEstado) {
        this.operadoraEstado = operadoraEstado;
    }
    public String getDuracao() {
        return this.duracao;
    }
    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }
    public String getTipo() {
        return this.tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getValor() {
        return this.valor;
    }
    public void setValor(String valor) {
        this.valor = valor;
    }
    public String getDono() {
        return this.dono;
    }
    public void setDono(String dono) {
        this.dono = dono;
    }
    public long getIdConta() {
        return this.idConta;
    }
    public void setIdConta(long idConta) {
        this.idConta = idConta;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Ligacao == false) {
            return false;
          }
          if (this == obj) {
            return true;
          }
          Ligacao rhs = (Ligacao) obj;
          return new EqualsBuilder()
                        .appendSuper(super.equals(obj))
                        .append(data, rhs.data)
                        .append(telefone, rhs.telefone)
                        .append(cidadeArea, rhs.cidadeArea)
                        .append(operadoraEstado, rhs.operadoraEstado)
                        .append(idConta, idConta)
                        .isEquals();
    }
    
    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(data)
            .append(telefone)
            .append(cidadeArea)
            .append(operadoraEstado)
            .append(idConta)
            .toHashCode();
    }
}
