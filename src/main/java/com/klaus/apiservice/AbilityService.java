package com.klaus.apiservice;

import java.util.List;
import java.util.Map;

public interface AbilityService {

	public Map<?, ?> addIndex(final List<?> jsonIndexes);
	public Map<?, ?> addCourseAbility(final List<?> jsonMapping);
	public List<?> getAbilityList();
	
}
