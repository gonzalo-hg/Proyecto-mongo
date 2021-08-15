package com.uam.springboot.app.Repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.BasicUpdate;
import org.springframework.data.mongodb.core.query.Criteria;

import com.uam.springboot.app.Entidad.Alumno;

public class CustomAlumnoRepositoryImpl implements CustomAlumnoRepository{
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void UpdateAlumno(String alumnoId, String fieldname, Object fieldValue) {
		mongoTemplate.findAndModify(BasicQuery.query(Criteria.where("id").is(alumnoId)),
				BasicUpdate.update(fieldname, fieldValue),
				FindAndModifyOptions.none(),Alumno.class);
		
	}

}
