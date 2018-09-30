package br.com.rbg.pedidovenda.comparator;

import java.util.Comparator;

import br.com.rbg.pedidovenda.model.Cidade;

public class CidadeComparator implements Comparator<Cidade> {

	@Override
	public int compare(Cidade cidade, Cidade outraCidade) {
		return cidade.getNome().compareTo(outraCidade.getNome());
	}
	
}
