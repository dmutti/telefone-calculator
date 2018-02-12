package com.appspot.dmutti.calculator.tags;

import java.io.*;
import java.util.*;

import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

import org.apache.commons.lang.*;

import com.appspot.dmutti.calculator.pojo.*;

public class SelectFiltro extends SimpleTagSupport {
    
    private String filtro;
    private Map<String, Dono> donos;
    private String selectName;
    private String onChange;
    
    @Override
    public void doTag() throws JspException, IOException {
        
        StringBuilder s = new StringBuilder();
        s.append("<select name=\"").append(selectName).append("\"onchange=\"").append(onChange).append("\">")
         .append("<option value=\" \"");
        if (isNoFilter()) {
            s.append(" selected=\"selected\"");
        }
        s.append(">Todos</option>");
        
        for (Dono dono : donos.values()) {
            s.append("<option value=\"").append(dono.getEmail()).append("\"");
            if (StringUtils.equals(filtro, dono.getEmail())) {
                s.append(" selected=\"selected\"");
            }
            s.append(">").append(dono.getNomeExibicao()).append("</option>");
        }
        
        getJspContext().getOut().println(s.toString());
    }
    
    public boolean isNoFilter() {
        return StringUtils.isEmpty(filtro) || !donos.containsKey(filtro);
    }
    
    public void setFiltro(String filtro) {
        this.filtro = filtro;
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
