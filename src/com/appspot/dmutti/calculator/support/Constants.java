package com.appspot.dmutti.calculator.support;

public enum Constants {
    
    FactoryAttributeName("SESSION_FACTORY"),
    PersistenceUnitName("transactions-optional"),
    RegexpReferenciaConta(".*([0-9]{2})/([0-9]{4})$"),
    DonosParameter("DONOS"),
    MesAnoReferenciaParameter("MES_ANO_REF"),
    ListaLigacoesParameter("LIGACOES"),
    NomeTotalParameter("DONO_TOTAL"),
    ListaContaParameter("CONTAS"),
    EntityManager("ENTITY_MANAGER"),
    CompartilhadoParameter("COMPARTILHADOS"),
    FiltroParameter("FILTRO"),
    LogoutParameter("LOGOUT");
    
    private final String value;
    
    @Override
    public String toString() {
        return this.value;
    }

    Constants(String value) {
        this.value = value;
    }
}
