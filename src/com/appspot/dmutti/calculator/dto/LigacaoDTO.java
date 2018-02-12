package com.appspot.dmutti.calculator.dto;

import java.math.*;
import java.text.*;
import java.util.*;

import com.appspot.dmutti.calculator.pojo.*;
import com.appspot.dmutti.calculator.support.*;

public class LigacaoDTO {
    
    private final long id;
    
    private final String data;
    private final String hora;
    private final String telefone;
    private final String cidadeArea;
    
    private final String operadoraEstado;
    private final String duracao;
    private final String tipo;
    
    private final String valor;
    private final BigDecimal bdValor;
    private Dono dono;
    
    private boolean confirmado = true;
    
    private final NumberFormat nf = Helper.getNumberFormat();
    private final NumberFormat nfDuracao = Helper.getNumberFormatDuracao();
    
    public LigacaoDTO(Ligacao ligacao, Map<String, Dono> emailDono) {
        
        data = Helper.dateFormat.print(ligacao.getData().getTime());
        hora = Helper.timeFormat.print(ligacao.getData().getTime());
        
        telefone = ligacao.getTelefone();
        cidadeArea = ligacao.getCidadeArea();
        operadoraEstado = ligacao.getOperadoraEstado();
        duracao = nfDuracao.format(new BigDecimal(ligacao.getDuracao()).multiply(Helper.MINUTO));
        tipo = ligacao.getTipo();
        
        BigDecimal dec = new BigDecimal(ligacao.getValor());
        valor = nf.format(dec);
        bdValor = dec;
        
        id = ligacao.getId();
        dono = emailDono.get(ligacao.getDono());
    }
    
    public long getId() {
        return this.id;
    }
    public String getData() {
        return this.data;
    }
    public String getHora() {
        return this.hora;
    }
    public String getTelefone() {
        return this.telefone;
    }
    public String getCidadeArea() {
        return this.cidadeArea;
    }
    public String getOperadoraEstado() {
        return this.operadoraEstado;
    }
    public String getDuracao() {
        return this.duracao;
    }
    public String getTipo() {
        return this.tipo;
    }
    public String getValor() {
        return this.valor;
    }
    public Dono getDono() {
        return this.dono;
    }
    public BigDecimal getBdValor() {
        return this.bdValor;
    }
    public boolean isConfirmado() {
        return this.confirmado;
    }
    public void setConfirmado(boolean confirmado) {
        this.confirmado = confirmado;
    }
    public void setDono(Dono dono) {
        this.dono = dono;
    }
}
