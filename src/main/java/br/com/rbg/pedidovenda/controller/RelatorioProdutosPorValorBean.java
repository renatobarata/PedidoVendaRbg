package br.com.rbg.pedidovenda.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import br.com.rbg.pedidovenda.util.jsf.FacesUtil;
import br.com.rbg.pedidovenda.util.report.ExecutorRelatorio;

@Named
@RequestScoped
public class RelatorioProdutosPorValorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private BigDecimal valorInferior;
	private BigDecimal valorSuperior;
	
	@Inject
	private FacesContext facesContext;

	@Inject
	private HttpServletResponse response;

	@Inject
	private EntityManager manager;

	public void emitir() {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("valor_inferior", this.valorInferior);
		parametros.put("valor_superior", this.valorSuperior);
		
		ExecutorRelatorio executor = new ExecutorRelatorio("/relatorios/relatorio_produtos_por_valor.jasper",
				this.response, parametros, "ProdutosPorValorUnitario.pdf");
		
		Session session = manager.unwrap(Session.class);
		session.doWork(executor);
		
		if (executor.isRelatorioGerado()) {
			facesContext.responseComplete();
		} else {
			FacesUtil.addErrorMessage("A execução do relatório não retornou dados.");
		}
	}
	
	
	public BigDecimal getValorInferior() {
		return valorInferior;
	}

	public void setValorInferior(BigDecimal valorInferior) {
		this.valorInferior = valorInferior;
	}

	public BigDecimal getValorSuperior() {
		return valorSuperior;
	}

	public void setValorSuperior(BigDecimal valorSuperior) {
		this.valorSuperior = valorSuperior;
	}
	
	
	
	
}
