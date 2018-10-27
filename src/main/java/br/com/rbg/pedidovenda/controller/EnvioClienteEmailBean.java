package br.com.rbg.pedidovenda.controller;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.outjected.email.api.MailMessage;
import com.outjected.email.impl.templating.velocity.VelocityTemplate;

import br.com.rbg.pedidovenda.model.Cliente;
import br.com.rbg.pedidovenda.util.jsf.FacesUtil;
import br.com.rbg.pedidovenda.util.mail.Mailer;

@Named
@RequestScoped
public class EnvioClienteEmailBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Mailer mailer;
	
	@Inject
	private Cliente cliente;
	
	public void enviarCliente() {
		MailMessage message = mailer.novaMensagem();
		
		message.to(this.cliente.getEmail())
			.subject("Dados do Cliente " + this.cliente.getNome())
			.bodyHtml(new VelocityTemplate(getClass().getResourceAsStream("/emails/cliente.template")))
			.put("cliente", this.cliente)
			.send();
					
		FacesUtil.addInfoMessage("Cliente enviado por e-mail com sucesso.");
	}
	
	
	
}
