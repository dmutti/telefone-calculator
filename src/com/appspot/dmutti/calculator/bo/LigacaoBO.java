package com.appspot.dmutti.calculator.bo;

import java.util.*;

import org.apache.commons.lang.*;

import com.appspot.dmutti.calculator.dao.*;
import com.appspot.dmutti.calculator.pojo.*;

public class LigacaoBO {
    
    private LigacaoDAO ligacaoDAO;
    private DonoBO donoBO;
    
    public void save(long idLigacao, String email) {
        Ligacao ligacao = ligacaoDAO.find(idLigacao);
        if (!StringUtils.equals(email, StringUtils.defaultString(ligacao.getDono()))) {
            if (StringUtils.isBlank(email)) {
                ligacao.setDono(null);
            } else {
                ligacao.setDono(donoBO.findByEmail(email).getEmail());    
            }
            ligacaoDAO.save(ligacao);
        }
    }
    
    public List<Ligacao> findByConta(Conta conta) {
        return ligacaoDAO.findByConta(conta);
    }
    
    public String findPossivelDonoByAreaTelefone(String area, String telefone) {
        List<String> donos = ligacaoDAO.findByAreaTelefone(area, telefone);
        for (String dono : donos) {
            if (StringUtils.isNotBlank(dono)) {
                return dono;
            }
        }

        return StringUtils.EMPTY;
    }
    
    public void insert(List<Ligacao> ligacoes, Conta conta) {
        for (Ligacao ligacao : ligacoes) {
            ligacao.setIdConta(conta.getId());
            ligacaoDAO.save(ligacao);
        }
    }

    public void setLigacaoDao(LigacaoDAO ligacaoDAO) {
        this.ligacaoDAO = ligacaoDAO;
    }

    public void setDonoBO(DonoBO donoBO) {
        this.donoBO = donoBO;
    }
}
