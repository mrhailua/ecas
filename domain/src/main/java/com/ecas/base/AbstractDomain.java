package com.ecas.base;

import org.hibernate.proxy.HibernateProxyHelper;

public abstract class AbstractDomain implements Comparable<AbstractDomain> {
	public abstract Integer getId();

	public abstract void setId(Integer id);

	public boolean isNew() {
		return getId() == 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null) {
			if (obj instanceof Integer) {
				return this.getId().equals(obj);
			}

			if (HibernateProxyHelper.getClassWithoutInitializingProxy(obj).equals(
			        HibernateProxyHelper.getClassWithoutInitializingProxy(this))) {
				return this.getId() == ((AbstractDomain) obj).getId();
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		return this.getId();
	}

	@Override
	public int compareTo(AbstractDomain arg0) {
		if (getId() == arg0.getId()) {
			return 0;
		}
		if (getId() > arg0.getId()) {
			return 1;
		}
		return -1;
	}
}
