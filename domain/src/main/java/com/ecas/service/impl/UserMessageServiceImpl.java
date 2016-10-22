package com.ecas.service.impl;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecas.domain.User;
import com.ecas.domain.UserMessage;
import com.ecas.service.UserMessageService;

@Service("userMessageService")
@Transactional
public class UserMessageServiceImpl extends GenericDataServiceImpl<UserMessage> implements UserMessageService {
	private static final long serialVersionUID = -5444173080936072478L;
	private static final Integer MAX_RESULT = 100;

	@Override
	public List<UserMessage> getMessageByUser(Integer userId) {
		Criteria criteria = this.getCriteria();
		criteria.add(Restrictions.eq("toUser.id", userId));
		criteria.add(Restrictions.eq("read", false));
		criteria.addOrder(Order.desc("updateTime"));
		criteria.setMaxResults(MAX_RESULT);
		return criteria.list();
	}

	@Override
	public List<UserMessage> load(int first, int pageSize, HashMap<String, Object> params) {
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(getDomainClass())
		        .setFirstResult(first).setFetchSize(pageSize).addOrder(Order.asc("read"))
		        .addOrder(Order.desc("updateTime"));

		criteria.add(Restrictions.eq("toUser.id", (Integer) params.get("userId")));
		return criteria.list();
	}

	@Override
	public Long loadCount(HashMap<String, Object> params) {
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(getDomainClass())
		        .setProjection(Projections.rowCount());

		criteria.add(Restrictions.eq("toUser.id", (Integer) params.get("userId")));
		return (Long) criteria.uniqueResult();
	}

	public Long getMessageCount(Integer userId) {
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(getDomainClass())
		        .setProjection(Projections.rowCount());

		criteria.add(Restrictions.eq("toUser.id", userId));
		criteria.add(Restrictions.eq("read", false));
		return (Long) criteria.uniqueResult();
	}

	@Override
	public void markRead(Integer messageId) {
		UserMessage message = super.get(messageId);
		message.markRead();
		this.save(message);

	}

	@Override
	public void cleanUp(User user, User systemAccount) {
		cleanUpToUser(user);
		cleanUpFromUser(user, systemAccount);
	}

	private void cleanUpToUser(User user) {
		Query query = getSessionFactory().getCurrentSession().createQuery("delete UserMessage where toUser = :toUser");
		query.setParameter("toUser", user);
		query.executeUpdate();
	}

	private void cleanUpFromUser(User user, User systemAccount) {
		Query query = getSessionFactory().getCurrentSession().createQuery(
		        "update UserMessage set fromUser=:fromUser where fromUser = :deleteUse");
		query.setParameter("deleteUse", user);
		query.setParameter("fromUser", systemAccount);
		query.executeUpdate();
	}
}
