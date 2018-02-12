package com.appspot.dmutti.calculator.tags;

import java.io.*;
import java.util.*;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import org.apache.commons.lang.*;

import com.appspot.dmutti.calculator.dto.*;
import com.appspot.dmutti.calculator.pojo.*;

public class SelectDono extends SimpleTagSupport {
    
    private LigacaoDTO ligacao;
    private Map<String, Dono> donos;
    private String selectName;
    private String onChange;
    
    @Override
    public void doTag() throws JspException, IOException {
        
        StringBuilder s = new StringBuilder();
        s.append("<select name=\"").append(selectName).append("\"onchange=\"").append(onChange).append("\">")
         .append("<option value=\" \"");
        if (!hasOwner()) {
            s.append(" selected=\"selected\"");
        }
        s.append("/>");
        
        for (Dono dono : donos.values()) {
            s.append("<option value=\"").append(dono.getEmail()).append("\"");
            if (isSelected(dono)) {
                s.append(" selected=\"selected\"");
            }
            s.append(">").append(dono.getNomeExibicao()).append("</option>");
        }
        
        getJspContext().getOut().println(s.toString());
    }

    private boolean hasOwner() {
        return (ligacao.getDono() != null && StringUtils.isNotBlank(ligacao.getDono().getEmail()) && donos.containsKey(ligacao.getDono().getEmail())) && ligacao.isConfirmado();
    }

    private boolean isSelected(Dono dono) {
        return ligacao.isConfirmado() && ligacao.getDono() != null && StringUtils.equals(ligacao.getDono().getEmail(),dono.getEmail());
    }
    
    public void setLigacao(LigacaoDTO ligacao) {
        this.ligacao = ligacao;
    }
    
    public void setDonos(Map<String, Dono> donos) {
        this.donos = donos;
    }
    
    public void setOnChange(String onChange) {
        this.onChange = onChange;
    }

    public void setSelectName(String selectName) {
        this.selectName = selectName;
    }
}
