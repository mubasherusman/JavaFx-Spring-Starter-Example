package com.rameysoft.main.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rameysoft.main.model.Supplier;
import com.rameysoft.main.repositories.SupplierRepository;


@Service
@Transactional(rollbackFor = Exception.class)
public class InventoryService {

	final static Logger LOGGER = LoggerFactory.getLogger(InventoryService.class);
	@Autowired
    protected SupplierRepository supplierRepository;

	public void addNewSupplier() {
		Supplier supplier = new Supplier();
		supplier.setName("Mubasher");
		supplier = supplierRepository.save(supplier);
		supplier = supplierRepository.findOne(supplier.getId());
		LOGGER.debug("Supplier Info {}", supplier);
	}
}
