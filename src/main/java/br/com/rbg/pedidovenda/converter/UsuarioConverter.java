package br.com.rbg.pedidovenda.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;

import br.com.rbg.pedidovenda.model.Usuario;
import br.com.rbg.pedidovenda.repository.Usuarios;
import br.com.rbg.pedidovenda.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter {

	//@Inject
	private Usuarios usuarios;
		
	public UsuarioConverter() {
		usuarios = (Usuarios) CDIServiceLocator.getBean(Usuarios.class);
	}
		
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Usuario retorno = null;
			
		if(StringUtils.isNoneEmpty(value)) {
			Long id = new Long(value);
			retorno = usuarios.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value != null) {
			Usuario usuario = (Usuario) value;
			return usuario.getId() == null ? null : usuario.getId().toString();
		}
		return "";
	}

}
