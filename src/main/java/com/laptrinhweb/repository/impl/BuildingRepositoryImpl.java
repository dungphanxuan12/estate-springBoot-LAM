package com.laptrinhweb.repository.impl;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.laptrinhweb.builder.BuildingSearchBuilder;
import com.laptrinhweb.entity.BuildingEntity;
import com.laptrinhweb.repository.BuildingRepositoryCustom;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<BuildingEntity> findAll(BuildingSearchBuilder builder, Pageable pageable) {
		try {
			StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM building AS A WHERE 1=1");
			Map<String, Object> properties = buildMapSearch(builder);
			StringBuilder whereClause = buildWhereClause(builder);
			sql = createSQLFindAll(sql, properties);
			sql.append(whereClause);
			Query query = entityManager.createNativeQuery(sql.toString());
			if (pageable != null) {
				query.setFirstResult((int) pageable.getOffset());
				query.setMaxResults(pageable.getPageSize());
			}
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private Map<String, Object> buildMapSearch(BuildingSearchBuilder buildingSearchBuilder) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
			for (Field field : fields) {
				if (!"".equals(field.getName()) && !field.getName().startsWith("costRent")
						&& !field.getName().startsWith("rentArea")) {
					field.setAccessible(true);
					if (field.get(buildingSearchBuilder) != null) {
						if (field.getName().equals("numberOfBasement") || field.getName().equals("buildingArea")) {
							result.put(field.getName().toLowerCase(),
									Integer.parseInt(((String) field.get(buildingSearchBuilder))));
						} else {
							result.put(field.getName().toLowerCase(), field.get(buildingSearchBuilder));
						}
					}
				}
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return result;
	}

	private StringBuilder buildWhereClause(BuildingSearchBuilder builder) {
		StringBuilder whereClause = new StringBuilder("");
		if (StringUtils.isNotBlank(builder.getCostRentFrom())) {
			whereClause.append(" AND costrent >= " + builder.getCostRentFrom() + " ");
		}
		if (StringUtils.isNotBlank(builder.getCostRentTo())) {
			whereClause.append(" AND costrent <= " + builder.getCostRentTo() + "");
		}
		if (StringUtils.isNotBlank(builder.getRentAreaFrom()) || StringUtils.isNotBlank(builder.getRentAreaTo())) {
			whereClause.append(" AND EXIST (SELECT * FROM rentarea ra WHERE (ra.buildingid = A.id ");
			if (builder.getRentAreaFrom() != null) {
				whereClause.append(" AND ra.value >= " + builder.getRentAreaFrom() + " ");
			}
			if (builder.getRentAreaTo() != null) {
				whereClause.append(" AND ra.value <= " + builder.getRentAreaTo() + " ");
			}
			whereClause.append(" ))");

		}
		if (builder.getBuildingTypes().length > 0) {
			whereClause.append(" AND (A.type LIKE '%" + builder.getBuildingTypes()[0] + "%'");
			Arrays.stream(builder.getBuildingTypes()).filter(item -> !item.equals(builder.getBuildingTypes()[0]))
					.forEach(item -> whereClause.append(" OR A.type LIKE '%" + item + "%'"));
			whereClause.append(" )");
		}
		return whereClause;
	}

	private StringBuilder createSQLFindAll(StringBuilder result, Map<String, Object> properties) {

		if (properties != null && properties.size() > 0) {
			String[] params = new String[properties.size()];
			Object[] values = new Object[properties.size()];
			int i = 0;
			for (Map.Entry<?, ?> item : properties.entrySet()) {
				params[i] = (String) item.getKey();
				values[i] = item.getValue();
				i++;
			}
			for (int j = 0; j < params.length; j++) {
				if (values[j] instanceof String) {
					result.append(" AND LOWER(" + params[j] + ") LIKE '%" + values[j].toString().toLowerCase() + "%'");
				} else if (values[j] instanceof Integer) {
					result.append(" AND " + params[j] + " = " + values[j]);
				} else if (values[j] instanceof Long) {
					result.append(" AND " + params[j] + " = " + values[j]);
				}
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Long count(BuildingSearchBuilder builder) {
		try {
			StringBuilder sql = new StringBuilder("SELECT * FROM building AS A WHERE 1=1");
			Map<String, Object> properties = buildMapSearch(builder);
			StringBuilder whereClause = buildWhereClause(builder);
			sql = createSQLFindAll(sql, properties);
			sql.append(whereClause);
			Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
			List<BigInteger> resultList = query.getResultList();
			return Long.parseLong(resultList.get(0).toString(), 10);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
