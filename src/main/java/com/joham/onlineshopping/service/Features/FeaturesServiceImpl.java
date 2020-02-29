
package com.joham.onlineshopping.service.Features;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joham.onlineshopping.dao.CategoryDao;
import com.joham.onlineshopping.dao.ProdSpecsDao;
import com.joham.onlineshopping.modal.ProdSpecs;
import com.joham.onlineshopping.modal.ProdSpecsDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeaturesServiceImpl implements FeaturesService {


	@Autowired
	ProdSpecsDao prodSpecsDao;
	@Autowired
	CategoryDao categoryDao;
	
	@Override
	public List<ProdSpecs> getFeatures() {
		List<ProdSpecs> prodSpecs = new ArrayList<>();

		prodSpecs  = (List<ProdSpecs>) prodSpecsDao.findAll();
		return prodSpecs;
	}

	// @Override
	// public List<ProdSpecs> shopFilters(Integer catId) {
		
	// 	List<Object[]> obj = prodSpecsDao.findByCategory(catId); 
		
	// 	List<ProdSpecs> prodSpecs = obj.stream()
	// 	.map(objects -> { return ProdSpecsSet(objects); }).collect(Collectors.toList()); 
		
	// 	return prodSpecs;
	// }


	
	// public ProdSpecs ProdSpecsSet(Object[] objects) {

	// 	ProdSpecs prod = new ProdSpecs();
	// 	prod.setId((Integer) objects[0]);
	// 	prod.setName((String) objects[1]);
	// 	return prod;
	//   }


	@Override
	public void saveProdSpecs(ProdSpecs prodSpecs) {
		prodSpecs.setCategory(categoryDao.findById(prodSpecs.getCatId()).get());
		prodSpecsDao.save(prodSpecs);
	}

	@Override
	public void deleteSpecs(Integer specId) {
		prodSpecsDao.deleteById(specId);
	}

	@Override
	public List<ProdSpecs> showFeatures(Integer catId) {

		List<Object[]> obj = prodSpecsDao.shopFilters(catId); 
		Map<Integer,ProdSpecs> featureId = new HashMap<>();

		obj.forEach(prodObj -> {

				ProdSpecs prod = new ProdSpecs(); 
			    if( featureId.containsKey((Integer) prodObj[0]))  {
					
					prod = featureId.get((Integer) prodObj[0]);
					prod.setCatId((Integer) prodObj[4]);
					prod.setProdSpecDtls(ProdSpecsAdd(prodObj,prod.getProdSpecDtls()));

				} else {
					
					prod.setId((Integer) prodObj[0]);
					prod.setName((String) prodObj[1]);
					prod.setCatId((Integer) prodObj[4]);
					prod.setProdSpecDtls(ProdSpecsAdd(prodObj,new ArrayList<>()));	
					featureId.put(prod.getId(),prod);
				}
			
			});
			List<ProdSpecs> prodSpecs = new  ArrayList<>();
			
			for(Map.Entry<Integer,ProdSpecs> prod : featureId.entrySet()){
				prodSpecs.add(prod.getValue());
			}
		return prodSpecs;
	
	}
	
	public List<ProdSpecsDetails> ProdSpecsAdd(Object[] objects,List<ProdSpecsDetails> prodList ) {
		ProdSpecsDetails prodDtls = new ProdSpecsDetails();
		
		prodDtls.setFeatureId((Integer) objects[0]);
		prodDtls.setfValue((String) objects[2]);
		prodDtls.setProdId((Integer) objects[3]);
		prodDtls.setId((Integer) objects[5]);
		prodList.add(prodDtls);
		return prodList;
	  }

}