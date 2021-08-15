package com.uam.springboot.app.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.uam.springboot.app.Entidad.Alumno;

public interface AlumnoRepository extends MongoRepository<Alumno, String>,CustomAlumnoRepository{

}
