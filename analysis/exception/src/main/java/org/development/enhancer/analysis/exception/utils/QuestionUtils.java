package org.development.enhancer.analysis.exception.utils;

import com.google.code.stackexchange.client.query.SearchApiQuery;
import com.google.code.stackexchange.common.PagedArrayList;
import com.google.code.stackexchange.common.PagedList;
import com.google.code.stackexchange.schema.Question;

import java.util.List;
import java.util.stream.Collectors;

public class QuestionUtils extends Utils {

	public static SearchApiQuery getQuery(String message, List<String> tags) {
		return factory.newSearchApiQuery()
				.withPaging(getPaging())
				.withSort(MOST_RELEVANT)
				.withTags(tags)
				.withInTitle(message);
	}

	public static PagedList<Question> getQuestions(SearchApiQuery query) {
		PagedList<?>        results   = getResults(query);
		PagedList<Question> questions = new PagedArrayList<>();
		if (results.isEmpty()) return questions;
		if (results.get(0) instanceof Question) {
			questions =
					results.stream().map(result -> (Question) result).collect(Collectors.toCollection(PagedArrayList::new));
		}
		return questions;
	}

}
