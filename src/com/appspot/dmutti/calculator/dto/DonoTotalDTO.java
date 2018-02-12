package com.appspot.dmutti.calculator.dto;

import java.math.*;
import com.appspot.dmutti.calculator.pojo.*;
import com.appspot.dmutti.calculator.support.*;

public class DonoTotalDTO {
    private Dono dono;
    private String total;
    private String compartilhado;
    private String comum;
    private String ligacoes;
    
    private DonoTotalDTO() {}
    
    public static DonoTotalDTO create(Dono dono) {
        DonoTotalDTO result = new DonoTotalDTO();
        result.dono = dono;
        return result;
    }
    
    public DonoTotalDTO withComum(BigDecimal arg) {
        this.comum = Helper.getNumberFormat().format(arg);
        return this;
    }
    
    public DonoTotalDTO withCompartilhado(BigDecimal arg) {
        this.compartilhado = Helper.getNumberFormat().format(arg);
        return this;
    }
    
    public DonoTotalDTO withTotal(BigDecimal arg) {
        this.total = Helper.getNumberFormat().format(arg);
        return this;
    }
    
    public DonoTotalDTO withLigacoes(BigDecimal arg) {
        this.ligacoes = Helper.getNumberFormat().format(arg);
        return this;
    }
    
    public Dono getDono() {
        return this.dono;
    }
    public String getTotal() {
        return this.total;
    }
    public String getCompartilhado() {
        return compartilhado;
    }
    public String getComum() {
        return comum;
    }
    public String getLigacoes() {
        return ligacoes;
    }
}
