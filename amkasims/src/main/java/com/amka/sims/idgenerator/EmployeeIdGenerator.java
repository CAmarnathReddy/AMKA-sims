package com.amka.sims.idgenerator;

import java.io.Serializable;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

@SuppressWarnings("serial")
public class EmployeeIdGenerator implements IdentifierGenerator {


	@SuppressWarnings("deprecation")
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) {
		String prefix = "EMP";

		String query = "SELECT e.id FROM employees e ORDER BY e.id DESC LIMIT 1";
		String lastId = (String) session.createNativeQuery(query).uniqueResult();

		int nextId = 1;
		if (lastId != null && lastId.startsWith(prefix)) {
			nextId = Integer.parseInt(lastId.substring(prefix.length())) + 1;
		}

		return String.format("%s%05d", prefix, nextId);
	}
}
