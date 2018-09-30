package br.com.rbg.pedidovenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import br.com.rbg.pedidovenda.model.Cidade;
import br.com.rbg.pedidovenda.repository.Cidades;

@FacesConverter(forClass = Cidade.class)
public class CidadeConverter implements Converter {

	@Inject
	private Cidades cidades;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Cidade retorno = null;
		
		if (StringUtils.isNotEmpty(value)) {
			Long id = new Long(value);
			retorno = cidades.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Cidade cidade = (Cidade) value;
			return cidade.getId() == null ? null : cidade.getId().toString();
		}
		
		return "";
	}
	
}
