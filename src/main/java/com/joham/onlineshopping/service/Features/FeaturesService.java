
package com.joham.onlineshopping.service.Features;

import java.util.List;

import com.joham.onlineshopping.modal.ProdSpecs;


public interface FeaturesService {

	// List<ProdSpecs> shopFilters(Integer catId);
	
	List<ProdSpecs> getFeatures();

	List<ProdSpecs> showFeatures(Integer catId);

	void saveProdSpecs(ProdSpecs prodSpecs);

	void deleteSpecs(Integer specId);

}