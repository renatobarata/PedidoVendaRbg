package br.com.rbg.pedidovenda.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.PieChartModel;

import br.com.rbg.pedidovenda.model.Usuario;
import br.com.rbg.pedidovenda.repository.Pedidos;
import br.com.rbg.pedidovenda.security.UsuarioLogado;
import br.com.rbg.pedidovenda.security.UsuarioSistema;

@Named
@RequestScoped
public class GraficoPedidosCriadosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM");
	
	@Inject
	private Pedidos pedidos;
	
	@Inject
	@UsuarioLogado
	private UsuarioSistema usuarioLogado;
	
	private LineChartModel lineModel;
	private PieChartModel pieModel;

	@PostConstruct
	public void init() {
		this.lineModel = new LineChartModel();
		this.pieModel = new PieChartModel();
		
		createLineModels();
		adicionarSerie("Todos os pedidos", null);
		adicionarSerie("Meus pedidos", usuarioLogado.getUsuario());
		
		createPieModel();
		adicionarPieChart();
		
	}
	
	private void createLineModels() {
		lineModel.setTitle("Pedidos Criados");
        lineModel.setLegendPosition("e");
        lineModel.setAnimate(true);
        lineModel.getAxes().put(AxisType.X, new CategoryAxis("Período"));
        Axis yAxis = lineModel.getAxis(AxisType.Y);
        yAxis.setLabel("Valor total dos pedidos");
        yAxis.setMin(0);
        //yAxis.setMax(10000);
    }
	
	private void adicionarSerie(String rotulo, Usuario criadoPor) {
		Map<Date, BigDecimal> valoresPorData = this.pedidos.valoresTotaisPorData(30, criadoPor);
		
		ChartSeries series = new ChartSeries(rotulo);
				
		series.setLabel(rotulo);
		for (Date data : valoresPorData.keySet()) {
			series.set(DATE_FORMAT.format(data), valoresPorData.get(data));
		}
		
		this.lineModel.addSeries(series);
	}

	private void adicionarPieChart() {
		Map<String, BigDecimal> valoresPorVendedor = this.pedidos.valorTotalPorUsuario();
		
		for (String nome : valoresPorVendedor.keySet()) {
			pieModel.set(nome, valoresPorVendedor.get(nome));
		}
		
	}
	
	private void createPieModel() {
		pieModel.setTitle("Pedidos Criados");
		pieModel.setLegendPosition("e");
		pieModel.setFill(true);
        pieModel.setShowDataLabels(true);
    }
	
	public LineChartModel getLineModel() {
		return lineModel;
	}	
	
	public PieChartModel getPieModel() {
		return pieModel;
	}
	
}
