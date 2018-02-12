<%@ include file="/WEB-INF/jsp/header.jsp" %>
<%@taglib uri="tabelaTags" prefix="t" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" type="text/css" media="screen" href="/telefone-calculator.css" />
    <link rel="shortcut icon" href="/favicon.ico" />
<title>Exibição de Conta</title>
</head>
<body>
    <c:url value="/app/save.htm" var="saveURL">
        <c:param name="MES_ANO_REF">${MES_ANO_REF}</c:param>
        <c:param name="FILTRO">${FILTRO}</c:param>
    </c:url>
    <c:url value="/app/display.htm" var="displayURL">
        <c:param name="MES_ANO_REF">${MES_ANO_REF}</c:param>
    </c:url>
    
	<div class="wrapper-ligacoes">
		<table style="width:100%">
		    <tr class="titulo-tabela" align="left">
		        <th>Data</th>
		        <th>Hora</th>
		        <th>Telefone</th>
		        <th>Cidade<br/>Área</th>
		        <th>Operadora<br/>Estado</th>
		        <th>Dura&ccedil;&atilde;o<br/>(seg)</th>
		        <th>Tipo</th>
		        <th>Valor<br/>(R$)</th>
		        <th>Dono</th>
		        <th>Anterior</th>
		    </tr>
		    <c:forEach var="ligacao" items="${LIGACOES}" varStatus="ligacaoLoop">
		            <c:set var="cor">
		                <c:choose>
		                    <c:when test="${(ligacaoLoop.count mod 2) eq 0}">#EEEEEE</c:when>
		                    <c:otherwise>#FFFFFF</c:otherwise>
		                </c:choose>
		            </c:set>
				<c:choose>
					<c:when test="${(empty DONOS[ligacao.dono.email]) or (ligacao.confirmado eq false)}">
						<c:set var="rowColor">vermelho</c:set>
					</c:when>
					<c:otherwise>
						<c:set var="rowColor">preto</c:set>
					</c:otherwise>
				</c:choose>
				<tr bgcolor="${cor}" class="${rowColor}">
		            <td>${ligacao.data}</td>
		            <td>${ligacao.hora}</td>
		            <td>${ligacao.telefone}</td>
		            <td>${ligacao.cidadeArea}</td>
		            <td>${ligacao.operadoraEstado}</td>
		            <td align="right">${ligacao.duracao}</td>
		            <td>${ligacao.tipo}</td>
		            <td align="right">${ligacao.valor}</td>
		            <td>
		                <form action="${saveURL}" method="post" name="salvar_${ligacao.id}">
		                    <input type="hidden" value="${ligacao.id}" name="id">
			    			<t:selectDono selectName="owner" onChange="document.salvar_${ligacao.id}.submit();" ligacao="${ligacao}" donos="${DONOS}"/>
		    			</form>
		            </td>
		            <td>
		                <c:if test="${ligacao.confirmado eq false}">${ligacao.dono.nomeExibicao}</c:if>
		            </td>
		        </tr>
		    </c:forEach>
		</table>
	</div>
	<p/>
    <form name="filtrar" action="${displayURL}" method="post">
        <label for="FILTRO" class="preto">Exibir as liga&ccedil;&otilde;es de </label>
        <t:selectFiltro selectName="FILTRO" filtro="${FILTRO}" onChange="document.filtrar.submit();" donos="${DONOS}"/>
        <label for="finalizar" class="preto"> ou </label><input type="button" onclick="location.href='/app/index.htm';" value="Finalizar">
    </form>
    <p/>
    
    <div class="wrapper-resto">
	    <div class="compartilhados">
		    <table style="width:100%">
		        <tr>
		            <th colspan="2" class="titulo-tabela">Itens Compartilhados</th>
		        </tr>
		        <tr class="titulo-tabela">
		            <th>Descri&ccedil;&atilde;o</th>
		            <th align="right">Total (R$)</th>
		        </tr>
		        <c:forEach var="compTotal" items="${COMPARTILHADOS}" varStatus="compartilhadosLoop">
		            <c:set var="cor">
			            <c:choose>
			                <c:when test="${(compartilhadosLoop.count mod 2) eq 0}">#EEEEEE</c:when>
			                <c:otherwise>#FFFFFF</c:otherwise>
			            </c:choose>
		            </c:set>
		            <tr bgcolor="${cor}" class="preto">
		                <td>${compTotal.nome}</td>
		                <td align="right">${compTotal.total}</td>
		            </tr>
		        </c:forEach>
		    </table>
	    </div>
	    
	    <div class="sumario">
		    <table style="width:100%">
			    <tr>
	                <th colspan="5" class="titulo-tabela">Total Sumarizado (R$)</th>
	            </tr>
	            <tr class="titulo-tabela">
	                <th>Nome</th>
	                <th>Compartilhado</th>
	                <th>Liga&ccedil;&otilde;es</th>
	                <th>Rateio</th>
	                <th>Total</th>
	            </tr>
			    <c:forEach var="donoTotal" items="${DONO_TOTAL}" varStatus="donoTotalLoop">
		            <c:set var="cor">
		                <c:choose>
		                    <c:when test="${(donoTotalLoop.count mod 2) eq 0}">#EEEEEE</c:when>
		                    <c:otherwise>#FFFFFF</c:otherwise>
		                </c:choose>
		            </c:set>
		            <c:set var="rowColor">
		                <c:choose>
			                <c:when test="${donoTotal.dono.doador eq true}">laranja</c:when>
			                <c:when test="${empty donoTotal.dono.nomeExibicao}">vermelho-total</c:when>
			                <c:otherwise>preto</c:otherwise>
		                </c:choose>
		            </c:set>
		            <c:set var="nomeDisplay">
		                <c:choose>
		                    <c:when test="${empty donoTotal.dono.nomeExibicao}">N&atilde;o marcado</c:when>
		                    <c:otherwise>${donoTotal.dono.nomeExibicao}</c:otherwise>
		                </c:choose>
		            </c:set>
				<tr bgcolor="${cor}" class="${rowColor}">
					<td>${nomeDisplay}</td>
                    <td align="right">${donoTotal.compartilhado}</td>
                    <td align="right">${donoTotal.ligacoes}</td>
					<td align="right">${donoTotal.comum}</td>
					<td align="right">${donoTotal.total}</td>
				</tr>
			    </c:forEach>
		    </table>
		</div>
	</div>
</body>
</html>