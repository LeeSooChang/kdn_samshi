package com.kdn.model.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.kdn.model.domain.Anonymity;
import com.kdn.model.domain.AnonymityPageBean;
import com.kdn.model.domain.UpdateException;
import com.kdn.util.AnonymityPageUtility;
@Service("anonymityService")
public class AnonymityServiceImpl implements AnonymityService {

	@Autowired
	@Qualifier("anonymityDao")
	private AnonymityDao dao;
	
	@Override
	public void update(Anonymity anonymity) {
		// TODO Auto-generated method stub
		try {
			dao.update(anonymity);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new UpdateException("ano update error");
		}

	}

	@Override
	public void remove(Anonymity anonymity) {
		// TODO Auto-generated method stub
		try {
			dao.remove(anonymity);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new UpdateException("ano remove error");
		}

	}

	@Override
	public Anonymity search(int ano) {
		// TODO Auto-generated method stub
		try {
			dao.search(ano);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new UpdateException("ano search error");
		}
		return null;
	}

	@Override
	public void add(Anonymity anonymity) {
		// TODO Auto-generated method stub
		try {
			dao.add(anonymity);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new UpdateException("ano add error");
		}

	}

	@Override
	public List<Anonymity> searchAll(AnonymityPageBean bean) {
		// TODO Auto-generated method stub
		try {
			int total = dao.getCount(bean);
			System.out.println("anoTotal : "+total);
			AnonymityPageUtility bar = new AnonymityPageUtility(bean.getInterval(), total, bean.getPageNo(), "/images");
			System.out.println("anobar : "+bar);
			bean.setPagelink(bar.getPageBar());
			
			return dao.searchAll(bean);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new UpdateException("ano searchAll error");
		}
	}

}
