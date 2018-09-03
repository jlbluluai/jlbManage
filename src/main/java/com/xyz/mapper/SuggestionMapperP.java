package com.xyz.mapper;

import java.util.List;

import com.xyz.domain.Suggestion;

public interface SuggestionMapperP {
	
	List<Suggestion> selectPages(Suggestion item);
}