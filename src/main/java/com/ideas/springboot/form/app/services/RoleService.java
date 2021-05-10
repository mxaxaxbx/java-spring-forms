package com.ideas.springboot.form.app.services;

import java.util.List;

import com.ideas.springboot.form.app.models.domain.Role;

public interface RoleService {

	public List<Role> listar();
	public Role obtenerPorId(Integer id);
}
