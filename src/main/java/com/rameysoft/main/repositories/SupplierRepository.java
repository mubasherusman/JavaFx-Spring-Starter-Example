package com.rameysoft.main.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rameysoft.main.model.Supplier;

@Repository
public interface SupplierRepository extends CrudRepository<Supplier, Integer> {

    public Iterable<Supplier> findSuppliersByName(@Param("name") String name);
}
