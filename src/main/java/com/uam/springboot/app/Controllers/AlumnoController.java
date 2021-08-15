package com.uam.springboot.app.Controllers;


import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.uam.springboot.app.DAO.AlumnoDAO;
import com.uam.springboot.app.Entidad.Alumno;
import com.uam.springboot.app.Repositories.AlumnoRepository;

@RestController
public class AlumnoController {

	@Autowired
	private AlumnoRepository alumnoRepository;
	
	@Autowired
	private AlumnoDAO alumnoDao;
	/**
	 * Metodo para agregar alumnos a la BD
	 * */
	@PostMapping("/alumnos")
	public void agregarAlumno(@RequestBody final List<Alumno> alumno){
		alumnoDao.saveAll(alumno);
	}
	
	/**
	 * Metodo para consultar todos los alumnos en la BD
	 * */
	@GetMapping("/alumnos")
	public List<Alumno> mostrarProductos(){
		return alumnoDao.findAll();	
	}
	
	
	/**
	 * Metodo para consultar un alumno por ID en la DB
	 * */
	@GetMapping("/alumnos/{alumnoId}")
	public Alumno findAlumno(@PathVariable final String alumnoId) {
		return alumnoDao.findById(alumnoId);
	}
	
	/**
	 * Metodo para actulizar un registro en la BD de manera
	 * basado en su ID y es parcialmente
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * */
	@PatchMapping("/alumnos/{alumnoId}")
	public void UpdateAlumno(@PathVariable final String alumnoId,
			@RequestBody final Alumno alumno) throws Exception {
		for(final Field campo : Alumno.class.getDeclaredFields()) {
			final String fieldname = campo.getName();
			
			if(fieldname.equals("id")) {
				continue;
			}
			
			final java.lang.reflect.Method getter = Alumno.class.getDeclaredMethod( "get"+StringUtils.capitalize(fieldname));
			final  Object valorCampo = getter.invoke(alumno);
			
			if(Objects.nonNull(valorCampo)) {
				
				alumnoRepository.UpdateAlumno(alumnoId, fieldname, valorCampo);
			}
		}
	}
	
	/*@PatchMapping("/alumnos/")
	public void UpdateAlumnos(@PathVariable final String alumnoId,
			@RequestBody final Alumno alumno) throws Exception {
		for(final Field campo : Alumno.class.getDeclaredFields()) {
			final String fieldname = campo.getName();
			
			if(fieldname.equals("id")) {
				continue;
			}
			
			final java.lang.reflect.Method getter = Alumno.class.getDeclaredMethod( "get"+StringUtils.capitalize(fieldname));
			final  Object valorCampo = getter.invoke(alumno);
			
			if(Objects.nonNull(valorCampo)) {
				
				alumnoRepository.UpdateAlumno(alumnoId, fieldname, valorCampo);
			}
		}
	}*/
	
	
}
