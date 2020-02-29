
package com.joham.onlineshopping.service.Reviews;

import java.util.List;

import com.joham.onlineshopping.modal.Reviews;


public interface ReviewsService {

public List<Reviews> showListReviews(Integer prodId);
void saveReviews(Reviews reviews);
void deleteById(Integer id); 

}