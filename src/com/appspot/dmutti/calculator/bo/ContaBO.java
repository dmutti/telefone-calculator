package com.appspot.dmutti.calculator.bo;

import java.util.*;

import org.apache.commons.lang.*;

import com.appspot.dmutti.calculator.dao.*;
import com.appspot.dmutti.calculator.dto.*;
import com.appspot.dmutti.calculator.pojo.*;

public class ContaBO {
    
    private ContaDAO contaDAO;
    private LigacaoBO ligacaoBO;
    private DonoBO donoBO;
    private CompartilhadoDAO compartilhadoDAO;
    
    public List<Conta> getAll() {
        return contaDAO.find();
    }
    
    public Conta find(Conta arg) {
        return contaDAO.getContaBy(arg);
    }
    
    public void insert(Conta arg) {
        contaDAO.save(arg);
    }

    public List<LigacaoDTO> findLigacoesToDisplayBy(Conta arg) {
        List<Ligacao> ligacoes = ligacaoBO.findByConta(arg);
        Map<String, Dono> emailDono = donoBO.getAllAsMap();

        List<LigacaoDTO> result = new ArrayList<LigacaoDTO>(ligacoes.size());
        
        for (Ligacao ligacao : ligacoes) {
            LigacaoDTO dto = new LigacaoDTO(ligacao, emailDono);
            String emailProvavelDono = ligacaoBO.findPossivelDonoByAreaTelefone(ligacao.getCidadeArea(), ligacao.getTelefone());
            if (StringUtils.isNotBlank(emailProvavelDono) && StringUtils.isBlank(ligacao.getDono())) {
                dto.setDono(emailDono.get(emailProvavelDono));
                dto.setConfirmado(false);
            }
            result.add(dto);
        }
        
        return result;
    }
    
    public List<Compartilhado> findCompartilhadosBy(Conta arg) {
        return compartilhadoDAO.findByConta(arg);
    }
    
    public void insertItens(List<Ligacao> ligacoes, List<Compartilhado> compartilhados, Conta conta) {
        ligacaoBO.insert(ligacoes, conta);
        compartilhadoDAO.insert(compartilhados, conta);
    }
    
    public List<Ligacao> findLigacoesBy(Conta arg) {
        return ligacaoBO.findByConta(arg);
    }
    
    public void setContaDao(ContaDAO contaDAO) {
        this.contaDAO = contaDAO;
    }

    public void setLigacaoBO(LigacaoBO ligacaoBO) {
        this.ligacaoBO = ligacaoBO;
    }

    public void setDonoBO(DonoBO donoBO) {
        this.donoBO = donoBO;
    }

    public void setCompartilhadoDao(CompartilhadoDAO compartilhadoDAO) {
        this.compartilhadoDAO = compartilhadoDAO;
    }
}
