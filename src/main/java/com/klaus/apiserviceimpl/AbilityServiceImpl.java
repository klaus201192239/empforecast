package com.klaus.apiserviceimpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.klaus.apiservice.AbilityService;
import com.klaus.bean.Ability;
import com.klaus.bean.CourseAbility;
import com.klaus.dao.AbilityDAO;
import com.klaus.dao.CourseAbilityDAO;
import com.klaus.dao.CourseDAO;
import com.klaus.factory.MyBeansFactory;
import com.klaus.utils.TimeUtil;

public class AbilityServiceImpl implements AbilityService {

	public Map<?, ?> addIndex(List<?> jsonIndexes) {

		// [{"name":"AAAA"},{"name":"BBBBBBB"},{"name":"CCCCCC"},{"name":"DDDDDDDD"}
		// ]

		Map<String, String> result = new HashMap<String, String>();

		try {

			for (int i = 0; i < jsonIndexes.size(); i++) {

				@SuppressWarnings("unchecked")
				Map<String, String> map = (Map<String, String>) jsonIndexes.get(i);

				String name = map.get("name");

				AbilityDAO abilityService = (AbilityDAO) MyBeansFactory.getBeans("abilitydao");

				String tag = abilityService.getIdByName(name);

				if (tag == null) {

					Ability ability = new Ability();
					ability.setId(TimeUtil.getObjectId());
					ability.setName(name);

					abilityService.insertAbility(ability);

				}

			}

			result.put("state", "ok");

		} catch (Exception e) {

			result.put("state", "wrong");

		}

		return result;
	}

	public Map<?, ?> addCourseAbility(final List<?> jsonMapping) {

		// [{"courseid":"AAAA","abilityid":"BBBBBBB","mapping":"CCCCCC"}]

		Map<String, String> result = new HashMap<String, String>();

		try {

			for (int i = 0; i < jsonMapping.size(); i++) {

				@SuppressWarnings("unchecked")
				Map<String, String> map = (Map<String, String>) jsonMapping.get(i);

				AbilityDAO abilityService = (AbilityDAO) MyBeansFactory.getBeans("abilitydao");

				int abilityTag = abilityService.getAbilityCount(map.get("abilityid"));

				if (0 == abilityTag) {

				} else {

					CourseDAO courseService = (CourseDAO) MyBeansFactory.getBeans("coursedao");

					int courseTag = courseService.getCourseCount(map.get("courseid"));

					if (courseTag == 0) {

					} else {

						CourseAbilityDAO courseAbilityService = (CourseAbilityDAO) MyBeansFactory
								.getBeans("courseabilitydao");

						String a = map.get("courseid").toString();
						String b = map.get("abilityid").toString();

						String courseAbilityTag = courseAbilityService.getId(a, b);

						if (courseAbilityTag == null) {

							CourseAbility ca = new CourseAbility();
							ca.setId(TimeUtil.getObjectId());
							ca.setCourseId(map.get("courseid"));
							ca.setAbilityId(map.get("abilityid"));
							ca.setScore(Double.parseDouble(map.get("score")));

							courseAbilityService.insertCourseAbility(ca);

							courseService.deleteCourseById(map.get("courseid"));

						}

					}

				}

			}

			result.put("state", "ok");
		} catch (Exception e) {

			e.printStackTrace();

			result.put("state", "wrong");
		}

		return result;

	}

	public List<?> getAbilityList() {

		AbilityDAO abilityDao = (AbilityDAO) MyBeansFactory.getBeans("abilitydao");

		List<Ability> listB = abilityDao.getAbilityInfo();

		return listB;
	}

}
