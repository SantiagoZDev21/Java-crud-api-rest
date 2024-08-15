package com.santiagozavala.apirest.apirest.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.santiagozavala.apirest.apirest.Entities.Producto;

public interface ProductoReposiroty extends JpaRepository<Producto, Long>{

    

}
