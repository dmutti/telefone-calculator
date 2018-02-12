package com.appspot.dmutti.calculator.controller;

import javax.servlet.http.*;

import org.apache.commons.lang.*;
import org.springframework.validation.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.mvc.*;
import org.springframework.web.servlet.view.*;

import com.appspot.dmutti.calculator.bo.*;
import com.appspot.dmutti.calculator.command.*;
import com.appspot.dmutti.calculator.parser.*;
import com.appspot.dmutti.calculator.pojo.*;
import com.appspot.dmutti.calculator.support.*;

public class UploadController extends SimpleFormController {
    
    private ContaBO contaBO;
    
    @Override
    protected ModelAndView onSubmit(HttpServletRequest req, HttpServletResponse resp, Object command, BindException errors) throws Exception {
        FileUploadCommand cmd = FileUploadCommand.class.cast(command);
        ContaTelefonicaParser parser = new ContaTelefonicaParser(cmd.getFile().getInputStream());
        Conta conta = getConta(parser, req);
        if (null == contaBO.find(conta)) {
            contaBO.insert(conta);
        }
       
        parseAndSave(parser, conta);
        return new ModelAndView(new RedirectView("/app/display.htm?" + Constants.MesAnoReferenciaParameter + "=" + conta));
    }
    
    private void parseAndSave(ContaTelefonicaParser parser, Conta conta) {
        if (contaBO.findLigacoesBy(conta).isEmpty()) {
            contaBO.insertItens(parser.getLigacoes(), parser.getCompartilhado(), conta);
        }
    }
    
    private Conta getConta(ContaTelefonicaParser parser, HttpServletRequest req) {
        Conta result = null;
        if (StringUtils.isNotBlank(req.getParameter(Constants.MesAnoReferenciaParameter.toString()))) {
            result = new Conta(req.getParameter(Constants.MesAnoReferenciaParameter.toString()));
        } else {
            result = parser.getMesAnoReferencia();            
        }
        return result;
    }

    public void setContaBO(ContaBO contaBO) {
        this.contaBO = contaBO;
    }
}
