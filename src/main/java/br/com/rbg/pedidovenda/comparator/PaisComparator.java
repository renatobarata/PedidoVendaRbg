package br.com.rbg.pedidovenda.comparator;

import java.util.Comparator;

import br.com.rbg.pedidovenda.model.Pais;

public class PaisComparator implements Comparator<Pais> {

	@Override
	public int compare(Pais pais, Pais outroPais) {
		return pais.getNome().compareTo(outroPais.getNome());
	}
	
}
