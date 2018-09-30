package br.com.rbg.pedidovenda.comparator;

import java.util.Comparator;

import br.com.rbg.pedidovenda.model.Estado;

public class EstadoComparator implements Comparator<Estado> {

	@Override
	public int compare(Estado estado, Estado outroEstado) {
		return estado.getNome().compareTo(outroEstado.getNome());
	}
	
}
