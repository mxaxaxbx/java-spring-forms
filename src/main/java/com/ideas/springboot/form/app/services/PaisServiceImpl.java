package com.ideas.springboot.form.app.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ideas.springboot.form.app.models.domain.Pais;

@Service
public class PaisServiceImpl implements PaisService {
	
	private List<Pais> lista;

	public PaisServiceImpl() {
		this.lista = Arrays.asList(
			new Pais(1, "ES", "España"),
			new Pais(2, "CO", "Colombia"),
			new Pais(3, "MX", "México"),
			new Pais(4, "CL", "Chile")
		);
	}

	@Override
	public List<Pais> listar() {
		return lista;
	}

	@Override
	public Pais obtenerPorId(Integer id) {
		Pais resultado = null;
		for(Pais pais: this.lista ) {
			if( id == pais.getId()) {
				resultado = pais;
				break;
			}
		}
		return resultado;
	}

}
