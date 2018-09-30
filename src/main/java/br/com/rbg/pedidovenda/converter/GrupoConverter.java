package br.com.rbg.pedidovenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.rbg.pedidovenda.model.Grupo;
import br.com.rbg.pedidovenda.repository.Grupos;
import br.com.rbg.pedidovenda.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Grupo.class)
public class GrupoConverter implements Converter {

	//@Inject
	private Grupos grupos;
		
	public GrupoConverter() {
		grupos = CDIServiceLocator.getBean(Grupos.class);
	}
		
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Grupo retorno = null;
			
		if(value != null) {
			Long id = new Long(value);
			retorno = grupos.porId(id);
		}
			
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null) {
			return ((Grupo) value).getId().toString();
		}
		return "";
	}
	
}
