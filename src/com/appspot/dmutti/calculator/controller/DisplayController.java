package com.appspot.dmutti.calculator.controller;

import java.util.*;

import javax.servlet.http.*;

import org.apache.commons.lang.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.mvc.*;

import com.appspot.dmutti.calculator.bo.*;
import com.appspot.dmutti.calculator.dto.*;
import com.appspot.dmutti.calculator.pojo.*;
import com.appspot.dmutti.calculator.support.*;

public class DisplayController extends AbstractController {

    private final Map<String, Object> model = new LinkedHashMap<String, Object>();
    private ContaBO contaBO;
    private DonoBO donoBO;
    
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Map<String, Dono> emailDono = donoBO.getAllAsMap();
        Conta conta = contaBO.find(getContaFrom(req));
        List<LigacaoDTO> ligacoes = contaBO.findLigacoesToDisplayBy(conta);
        
        addToModel(Constants.MesAnoReferenciaParameter, conta.toString());
        addToModel(Constants.FiltroParameter, getFiltro(req));
        
        TotalBO totalBO = new TotalBO(contaBO.findCompartilhadosBy(conta), ligacoes, emailDono);
        if (StringUtils.isNotBlank(getFiltro(req))) {
            addToModel(Constants.NomeTotalParameter, totalBO.getTotalTableFor(getFiltro(req)));
            addToModel(Constants.ListaLigacoesParameter, getLigacoesComFiltro(emailDono.get(getFiltro(req)), ligacoes));
        } else {
            addToModel(Constants.NomeTotalParameter, totalBO.getTotalTable());
            addToModel(Constants.ListaLigacoesParameter, ligacoes);
        }
        
        addToModel(Constants.DonosParameter, emailDono);
        addToModel(Constants.CompartilhadoParameter, totalBO.getDescricaoTotalCompartilhado());
        return new ModelAndView("exibeConta", model);
    }
    

    private String getFiltro(HttpServletRequest req) {
        return StringUtils.defaultString(req.getParameter(Constants.FiltroParameter.toString()));
    }
    
    private List<LigacaoDTO> getLigacoesComFiltro(Dono dono, List<LigacaoDTO> ligacoes) {
        List<LigacaoDTO> result = new ArrayList<LigacaoDTO>();
        
        for (LigacaoDTO dto : ligacoes) {
            if (dono.equals(dto.getDono())) {
                result.add(dto);
            }
        }
        return result;
    }
    
    public Conta getContaFrom(HttpServletRequest req) {
        Conta result = null;
        if (StringUtils.isNotBlank(req.getParameter(Constants.MesAnoReferenciaParameter.toString()))) {
            result = new Conta(req.getParameter(Constants.MesAnoReferenciaParameter.toString()));
        }
        
        if (null != req.getAttribute(Constants.MesAnoReferenciaParameter.toString())) {
            result =  (Conta) req.getAttribute(Constants.MesAnoReferenciaParameter.toString());
        }
        
        return result;
    }
    
    private void addToModel(Constants constant, Object value) {
        model.put(constant.toString(), value);
    }

    public void setContaBO(ContaBO contaBO) {
        this.contaBO = contaBO;
    }
    
    public void setDonoBO(DonoBO donoBO) {
        this.donoBO = donoBO;
    }
}
