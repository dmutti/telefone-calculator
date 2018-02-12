package com.appspot.dmutti.calculator.bo;

import java.math.*;
import java.util.*;
import java.util.Map.*;

import com.appspot.dmutti.calculator.dto.*;
import com.appspot.dmutti.calculator.pojo.*;

public class TotalBO {
    private List<Compartilhado> compartilhados;
    private List<LigacaoDTO> ligacoes;
    private Map<String, Dono> donos;
    
    public TotalBO(List<Compartilhado> compartilhados, List<LigacaoDTO> ligacoes, Map<String, Dono> donos) {
        this.compartilhados = compartilhados;
        this.ligacoes = ligacoes;
        this.donos = donos;
    }
    
    public List<NomeTotalDTO> getDescricaoTotalCompartilhado() {
        List<NomeTotalDTO> result = new ArrayList<NomeTotalDTO>(compartilhados.size());
        for (Compartilhado c : compartilhados) {
            BigDecimal valor = new BigDecimal(c.getValor());
            if (valor.compareTo(BigDecimal.ZERO) == 0) {
                continue;
            }
            result.add(new NomeTotalDTO(c.getDescricao(), valor));
        }
        return result;
    }
    
    public List<DonoTotalDTO> getTotalTable() {
        BigDecimal rateioCompartilhado = getValorRateio(getTotalCompartilhado());
        BigDecimal rateioDoadores = getValorRateio(getTotalDoadores());
        Map<Dono, BigDecimal> donoTotalLigacao = getDonoTotalLigacoes();
        
        List<DonoTotalDTO> totalTable = new ArrayList<DonoTotalDTO>();
        
        for (Entry<Dono, BigDecimal> cada : donoTotalLigacao.entrySet()) {
            Dono dono = cada.getKey();
            BigDecimal totalLigacoes = cada.getValue();
            
            DonoTotalDTO dto = DonoTotalDTO.create(dono);
            dto.withLigacoes(totalLigacoes);
            if (dono.isDoador()) {
                dto.withTotal(totalLigacoes);
            } else {
                dto.withCompartilhado(rateioCompartilhado)
                   .withComum(rateioDoadores)
                   .withTotal(totalLigacoes.add(rateioCompartilhado).add(rateioDoadores));
            }
            totalTable.add(dto);
        }
        
        totalTable.add(DonoTotalDTO.create(null)
            .withLigacoes(getTotalNaoMarcado())
            .withTotal(getTotalNaoMarcado()));
        
        return totalTable;
    }
    
    public List<DonoTotalDTO> getTotalTableFor(String email) {
        List<DonoTotalDTO> totalTable = getTotalTable();
        
        for (ListIterator<DonoTotalDTO> it = totalTable.listIterator(); it.hasNext(); ) {
            DonoTotalDTO dto = it.next();
            if (dto.getDono() != null) {
                if (!dto.getDono().equals(donos.get(email)) && !dto.getDono().isDoador()) {
                    it.remove();
                }
            }
        }
        return totalTable;
    }
    
    private BigDecimal getTotalCompartilhado() {
        BigDecimal total = BigDecimal.ZERO;
        for (Compartilhado comp : compartilhados) {
            total = total.add(new BigDecimal(comp.getValor()));
        }
        
        return total;
    }
    
    private BigDecimal getTotalDoadores() {
        BigDecimal total = BigDecimal.ZERO;
        for (LigacaoDTO ligacao : ligacoes) {
            if (isDono(ligacao) && ligacao.getDono().isDoador()) {
                total = total.add(ligacao.getBdValor());
            }
        }
        
        return total;
    }

    private BigDecimal getTotalNaoMarcado() {
        BigDecimal total = BigDecimal.ZERO;
        for (LigacaoDTO ligacao : ligacoes) {
            if (ligacao.getDono() == null) {
                total = total.add(ligacao.getBdValor());
            }
        }
        return total;
    }
    
    private Map<Dono, BigDecimal> getDonoTotalLigacoes() {
        Map<Dono, BigDecimal> donoTotalLigacoes = new LinkedHashMap<Dono, BigDecimal>();
        
        for (Dono dono : donos.values()) {
            donoTotalLigacoes.put(dono, BigDecimal.ZERO);
        }
        
        for (LigacaoDTO ligacao : ligacoes){
            if (ligacao.getDono() != null && ligacao.isConfirmado()) {
                if (donoTotalLigacoes.containsKey(ligacao.getDono())) {
                    donoTotalLigacoes.put(ligacao.getDono(), donoTotalLigacoes.get(ligacao.getDono()).add(ligacao.getBdValor()));
                } else {
                    donoTotalLigacoes.put(ligacao.getDono(), ligacao.getBdValor());
                }
            }
        }
        return donoTotalLigacoes;
    }
    
    private BigDecimal getValorRateio(BigDecimal valorTotal) {
        return valorTotal.divide(new BigDecimal(Math.abs(Math.max(1,donos.size()-getDoadores()))), BigDecimal.ROUND_UP);
    }
    
    private int getDoadores() {
        int count = 0;
        for (Dono dono : donos.values()) {
            if (dono.isDoador()) {
                ++count;
            }
        }
        return count;
    }
    
    private boolean isDono(LigacaoDTO ligacao) {
        return null != ligacao.getDono() && ligacao.isConfirmado();
    }

}
