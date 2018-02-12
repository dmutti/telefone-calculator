package com.appspot.dmutti.calculator.controller;

import java.util.*;

import javax.servlet.http.*;

import org.springframework.web.servlet.*;
import org.springframework.web.servlet.mvc.*;

import com.appspot.dmutti.calculator.bo.*;
import com.appspot.dmutti.calculator.support.*;
import com.google.appengine.api.users.*;

public class IndexController extends AbstractController {
    
    private DonoBO donoBO;
    private ContaBO contaBO;
    
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        donoBO.init();
        Map<String, Object> result = new LinkedHashMap<String, Object>();
        result.put(Constants.ListaContaParameter.toString(), contaBO.getAll());
        result.put(Constants.LogoutParameter.toString(), UserServiceFactory.getUserService().createLogoutURL("http://google.com.br"));
        return new ModelAndView("index", result);
    }

    public void setDonoBO(DonoBO donoBO) {
        this.donoBO = donoBO;
    }

    public void setContaBO(ContaBO contaBO) {
        this.contaBO = contaBO;
    }
}
