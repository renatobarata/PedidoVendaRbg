package br.com.rbg.pedidovenda.converter;

import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.primefaces.convert.ClientConverter;

import br.com.rbg.pedidovenda.model.Categoria;
import br.com.rbg.pedidovenda.repository.Categorias;
import br.com.rbg.pedidovenda.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter implements Converter, ClientConverter {

	//@Inject
	private Categorias categorias;
	
	public CategoriaConverter() {
		categorias = CDIServiceLocator.getBean(Categorias.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Categoria retorno = null;
		
		if(value != null) {
			Long id = new Long(value);
			retorno = categorias.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null) {
			return ((Categoria) value).getId().toString();
		}
		return "";
	}

	@Override
	public String getConverterId() {
		return "br.com.rbg.Categoria";
	}

	@Override
	public Map<String, Object> getMetadata() {
		return null;
	}
	
}
