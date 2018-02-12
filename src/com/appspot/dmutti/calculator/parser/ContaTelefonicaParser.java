package com.appspot.dmutti.calculator.parser;

import java.io.*;
import java.util.*;
import java.util.regex.*;

import net.htmlparser.jericho.*;

import org.apache.commons.lang.*;

import com.appspot.dmutti.calculator.pojo.*;
import com.appspot.dmutti.calculator.support.*;

public class ContaTelefonicaParser {
    
    private final Source source;
    private final Pattern pattern = Pattern.compile(Constants.RegexpReferenciaConta.toString()); 
    
    public ContaTelefonicaParser(InputStream is) {
        try {
            source = new Source(is);
            source.fullSequentialParse();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Ligacao> getLigacoes() {
        
        try {
            String[] content = source.getRenderer().toString().split("\\n");
            ArrayList<Ligacao> ligacoes = new ArrayList<Ligacao>();
            
            for (String s : content) {
                String tmp = s.trim();
                if (hasDate(tmp) && hasPhone(tmp)) {
                    Ligacao ligacao = new Ligacao(tmp);
                    ligacoes.add(ligacao);
                }
            }
            return ligacoes;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Compartilhado> getCompartilhado() {
        try {
            String[] content = source.getRenderer().toString().split("\\n");
            
            List<Compartilhado> result = new ArrayList<Compartilhado>();
            for (String s : content) {
                String tmp = s.trim();
                
                String valor = getValorFrom(tmp);
                if (isItem(tmp) && !hasDate(tmp) && valor.contains(",")) {
                    result.add(new Compartilhado(tmp));
                }
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Conta getMesAnoReferencia() {
        Matcher matcher = pattern.matcher(getTitle());
        
        if (matcher.matches()) {
            return new Conta(Integer.valueOf(matcher.group(1)), Integer.valueOf(matcher.group(2)));
        }

        throw new RuntimeException("Impossivel obter m�s de refer�ncia.");
    }
    
    private String getTitle() {
        Element titleElement = source.getFirstElement(HTMLElementName.TITLE);
        if (titleElement == null) {
            return StringUtils.EMPTY;
        }
        return CharacterReference.decodeCollapseWhiteSpace(titleElement.getContent());
    }
    
    private boolean hasPhone(String tmp) {
        return StringUtils.substring(tmp, 14, 22).matches("\\d{8}");
    }

    private boolean hasDate(String tmp) {
        return StringUtils.substring(tmp, 4, 12).matches("\\d{2}[/]\\d{2}[/]\\d{2}");
    }
    
    private boolean isItem(String tmp) {
        return StringUtils.substring(tmp, 0, 4).matches("\\d{3} ");
    }

    private String getValorFrom(String tmp) {
        return StringUtils.substring(tmp, 72).trim();
    }
}
