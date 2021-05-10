package com.ideas.springboot.form.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.ideas.springboot.form.app.editors.NombreMayusculaEditor;
import com.ideas.springboot.form.app.editors.PaisPropertyEdito;
import com.ideas.springboot.form.app.editors.RolesEditor;
import com.ideas.springboot.form.app.models.domain.Pais;
import com.ideas.springboot.form.app.models.domain.Role;
import com.ideas.springboot.form.app.models.domain.User;
import com.ideas.springboot.form.app.services.PaisService;
import com.ideas.springboot.form.app.services.RoleService;
import com.ideas.springboot.form.app.validations.UsuarioValidador;

@Controller
@SessionAttributes("user")
public class FormController {
	
	@Autowired
	private UsuarioValidador validador;
	
	@Autowired
	private PaisService paisService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PaisPropertyEdito paisEditor;
	@Autowired
	private RolesEditor roleEditor;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(validador);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, "fechaNacimiento", new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(String.class, "nombre", new NombreMayusculaEditor());
		binder.registerCustomEditor(String.class, "apellido", new NombreMayusculaEditor());
		binder.registerCustomEditor(Pais.class, "pais", paisEditor);
		binder.registerCustomEditor(Role.class, "roles", roleEditor);
	}
	
	@ModelAttribute("genero")
	public List<String> genero() {
		return Arrays.asList("Hombre", "Mujer");
	}
	
	@ModelAttribute("listaRoles")
	public List<Role> listaRoles() {
		return this.roleService.listar();
	}
	
	@ModelAttribute("listaPaises")
	public List<Pais> listaPaises() {
		return paisService.listar();
	}
	
	@ModelAttribute("listaRolesString")
	public List<String> listaRolesString() {
		List<String> roles = new ArrayList<>();
		roles.add("ROLE_ADMIN");
		roles.add("ROLE_USER");
		roles.add("ROLE_MODERATOR");
		return roles;
	}
	
	@ModelAttribute("listaRolesMap")
	public Map<String, String> listaRolesMap() {
		Map<String, String> roles = new HashMap<String, String>();
		roles.put("ROLE_ADMIN", "Asministrador");
		roles.put("ROLE_USER", "Usuario");
		roles.put("ROLE_MODERATOR", "Moderador");
		
		return roles;
	}
	
	@ModelAttribute("paises")
	public List<String> paises() {
		return Arrays.asList("España","Colombia", "México", "Chile");
	}
	
	@ModelAttribute("paisesMap")
	public Map<String, String> paisesMap() {
		Map<String, String> paises = new HashMap<String, String>();
		paises.put("ES", "España");
		paises.put("CO", "Colombia");
		paises.put("MX", "México");
		paises.put("CL", "Chile");
		
		return paises;
	}

	@GetMapping("/form")
	public String form(User user, Model model ) {
		// user.setNombre("Juan");
		// user.setApellido("Cardenas");
		// user.setId("1-k");
		user.setHabilitar(true);
		user.setValorSecreto("algoooo");
		user.setPais( new Pais(2, "CO", "Colombia") );
		user.setRoles( Arrays.asList( new Role(2, "Usuario", "ROLE_USER") ) );
		
		model.addAttribute("titulo", "Users Form");
		return "form";
	}
	
	@PostMapping("/form")
	public String procesar(@Valid User user, BindingResult result, Model model) {
		if( result.hasErrors() ) {
			/* Map<String, String> errores = new HashMap<>();
			
			result.getFieldErrors().forEach(err -> {
				// System.out.println(err.getField());
				errores.put(
					err.getField(),
					"El campo "
					.concat(err.getField())
					.concat(" ")
					.concat(
							err.getDefaultMessage() == null ? "N/A" : err.getDefaultMessage()
					)
				);
			});
			
			model.addAttribute("error", errores); */
			model.addAttribute("titulo", "Resultado form");
			return "form";
		}
		
		return "redirect:/ver";
	}
	
	@GetMapping("/ver")
	public String ver(@SessionAttribute(name="user", required = false) User user, Model model, SessionStatus status) {
		if( user == null ) return "redirect:/form";
		
		model.addAttribute("titulo", "Resultado form");
		
		status.setComplete();
		return "resultado";
	}
	
}
