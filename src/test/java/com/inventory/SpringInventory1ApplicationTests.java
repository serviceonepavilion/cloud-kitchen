package com.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringInventory1ApplicationTests {

	/*@Autowired
	private CatalogService catalogSevice;
	@Autowired
	private SupplierService supplierService;
	
	@MockBean
	private CatalogRepository catalogRepository;
	
	@MockBean
	private SupplierRepository supplierRepository;
	
	@Test
	public void getCatalogByIdTest() {
		Catalog catalog = new Catalog(201,"Kitchen Products", "Daily Products", "bajaj", "bajaj Eletronics", new Supplier(1,"Shubham"));
		when(catalogRepository.findById(201)).thenReturn(Optional.of(catalog));
		assertEquals(catalog,catalogSevice.getCatalogById(201));
	}
	
	@Test
	public void saveOrUpdateTest() {
		Catalog catalog = new Catalog(201,"Kitchen Products", "Daily Products", "bajaj", "bajaj Eletronics", new Supplier(6,"Shubham"));
		when(catalogRepository.save(catalog)).thenReturn(catalog);
		assertEquals(catalog, catalogSevice.saveOrUpdate(catalog));
	}
	
	
	@Test
	public void insertSupplierTest() {
		Supplier supplier = new Supplier(20, "Shubhanjali");
		when(supplierRepository.save(supplier)).thenReturn(supplier);
		assertEquals(supplier, supplierService.saveOrUpdate(supplier));
	}
*/
}
