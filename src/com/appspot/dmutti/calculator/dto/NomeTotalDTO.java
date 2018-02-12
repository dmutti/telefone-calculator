package com.appspot.dmutti.calculator.dto;

import java.math.*;
import com.appspot.dmutti.calculator.support.*;

public class NomeTotalDTO {
    
    private final String nome;
    private final String total;
    
    public NomeTotalDTO(String nome, BigDecimal total) {
        this.nome = nome;
        this.total = Helper.getNumberFormat().format(total);
    }
    
    public String getNome() {
        return this.nome;
    }
    public String getTotal() {
        return this.total;
    }
}
