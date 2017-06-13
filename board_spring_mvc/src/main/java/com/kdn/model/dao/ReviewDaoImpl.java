package com.kdn.model.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kdn.model.biz.ReviewDao;
import com.kdn.model.domain.Review;
import com.kdn.model.domain.ReviewPageBean;

@Repository("reviewDao")
public class ReviewDaoImpl implements ReviewDao {
	
	@Autowired
	private SqlSessionTemplate session;
	

	@Override
	public void add(Review review) {
		// TODO Auto-generated method stub
		session.selectOne("review.insert", review);

	}

	@Override
	public void update(Review review) {
		// TODO Auto-generated method stub
		session.update("review.update", review);

	}

	@Override
	public void remove(int rno) {
		// TODO Auto-generated method stub
		session.delete("review.delete", rno);

	}

	@Override
	public Review search(int rno) {
		// TODO Auto-generated method stub
		return session.selectOne("review.search", rno);
	}

	@Override
	public List<Review> searchAll(ReviewPageBean bean) {
		RowBounds rows = new  RowBounds(bean.getStart()-1, bean.getInterval());
		return session.selectList("review.searchAll", bean, rows);
	}

	@Override
	public int getCount(ReviewPageBean bean) {
		// TODO Auto-generated method stub
		return session.selectOne("review.getCount", bean);
	}

	@Override
	public int getBoardNo() {
		// TODO Auto-generated method stub
		return session.selectOne("review.getReviewNo");
	}

}
