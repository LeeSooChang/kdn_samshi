package com.kdn.model.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.kdn.model.domain.Review;
import com.kdn.model.domain.ReviewPageBean;
import com.kdn.model.domain.UpdateException;
import com.kdn.util.ReviewPageUtility;

@Service("reviewService")
public class ReviewServiceImpl implements ReviewService {
	@Autowired
	@Qualifier("reviewDao")
	private ReviewDao dao;

	@Override
	public void update(Review review) {
		// TODO Auto-generated method stub
		try {
			dao.update(review);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new UpdateException("update error");
		}

	}

	@Override
	public void remove(int rno) {
		// TODO Auto-generated method stub
		try {
			dao.remove(rno);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new UpdateException("remove error");
		}

	}

	@Override
	public Review search(int rno) {
		// TODO Auto-generated method stub
		try {
			return dao.search(rno);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new UpdateException("search error");
		}
	}

	@Override
	public void add(Review review) {
		// TODO Auto-generated method stub
		try {
			int rno = dao.getBoardNo();
			review.setRno(rno);
			dao.add(review);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new UpdateException("add error");
		}

	}

	@Override
	public List<Review> searchAll(ReviewPageBean bean) {
		// TODO Auto-generated method stub
		try {
			int total = dao.getCount(bean);
			System.out.println("total"+total);
			ReviewPageUtility bar = new ReviewPageUtility(bean.getInterval(), total, bean.getPageNo(), "/images");
			System.out.println("bar"+bar);
			bean.setPagelink(bar.getPageBar());

			return dao.searchAll(bean);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new UpdateException("searchAll error");
		}
	}
}




