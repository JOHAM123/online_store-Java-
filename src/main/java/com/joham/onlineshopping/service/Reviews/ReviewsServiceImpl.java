
package com.joham.onlineshopping.service.Reviews;

import java.sql.Timestamp;
import java.util.List;

import com.joham.onlineshopping.dao.ReviewsDao;
import com.joham.onlineshopping.dao.UserDao;
import com.joham.onlineshopping.modal.Reviews;
import com.joham.onlineshopping.modal.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewsServiceImpl implements ReviewsService {

	@Autowired
	ReviewsDao reviewsDao;

	@Autowired
	UserDao userDao;
	
	@Override
	public List<Reviews> showListReviews(Integer prodId) {

		return reviewsDao.findByProdId(prodId);
	}

	@Override
	public void saveReviews(Reviews reviews) {
		Timestamp createdate = new Timestamp(System.currentTimeMillis());
		User user = userDao.findByUsername(reviews.getUser().getUsername()).orElse(null);
		reviews.setUser(user);
		reviews.setCreatedate(createdate);

		reviews.getMessage().get(0).setUserId(user);
		reviews.getMessage().get(0).setReviews(reviews);
		reviews.getMessage().get(0).setCreatedate(createdate);

		reviewsDao.save(reviews);
	}

	@Override
	public void deleteById(Integer id) {
	


	}



}