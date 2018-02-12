package com.appspot.dmutti.calculator.controller;

import javax.servlet.http.*;

import org.springframework.validation.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.mvc.*;
import org.springframework.web.servlet.view.*;

import com.appspot.dmutti.calculator.bo.*;
import com.appspot.dmutti.calculator.command.*;
import com.appspot.dmutti.calculator.pojo.*;
import com.appspot.dmutti.calculator.support.*;

public class SaveController extends SimpleFormController {
    
    private LigacaoBO ligacaoBO;
    
    @Override
    protected ModelAndView onSubmit(HttpServletRequest req, HttpServletResponse resp, Object command, BindException errors) throws Exception {
        SaveCommand cmd = (SaveCommand) command;
        ligacaoBO.save(cmd.getId(), cmd.getOwner());
        
        Conta conta = new Conta(req.getParameter(Constants.MesAnoReferenciaParameter.toString()));
        return new ModelAndView(new RedirectView("/app/display.htm?" + Constants.MesAnoReferenciaParameter + "=" + conta));
    }
    
    public void setLigacaoBO(LigacaoBO ligacaoBO) {
        this.ligacaoBO = ligacaoBO;
    }
}
