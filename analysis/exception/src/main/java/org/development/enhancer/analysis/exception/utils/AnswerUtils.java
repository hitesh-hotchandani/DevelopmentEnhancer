package org.development.enhancer.analysis.exception.utils;

import com.google.code.stackexchange.client.query.AnswerApiQuery;
import com.google.code.stackexchange.common.PagedArrayList;
import com.google.code.stackexchange.common.PagedList;
import com.google.code.stackexchange.schema.Answer;

import java.util.List;
import java.util.stream.Collectors;

public final class AnswerUtils extends Utils {

	public static PagedList<Answer> getAnswer(long answerId) {

		return convertToAnswers(getAnswers(answerId));
	}

	public static PagedList<Answer> getAnswer(List<Long> ids) {

		return convertToAnswers(getAnswers(ids));
	}

	private static PagedList<?> getAnswers(List<Long> answerId) {

		return getResults(getAnswerApiQuery().withAnswerIds(answerId));
	}

	private static PagedList<?> getAnswers(long... answerId) {

		return getResults(getAnswerApiQuery().withAnswerIds(answerId));
	}

	private static AnswerApiQuery getAnswerApiQuery() {
		return factory.newAnswerApiQuery();
	}

	private static PagedList<Answer> convertToAnswers(PagedList<?> results) {
		PagedList<Answer> answers = new PagedArrayList<>();
		if (results != null) {
			if (results.get(0) instanceof Answer) {
				answers = results.stream().map(result -> (Answer) result).collect(Collectors.toCollection(PagedArrayList::new));
			}
		}

		return answers;
	}

}
