package org.development.enhancer.analysis.exception.utils;

import com.google.code.stackexchange.client.query.StackExchangeApiQuery;
import com.google.code.stackexchange.client.query.StackExchangeApiQueryFactory;
import com.google.code.stackexchange.common.PagedList;
import com.google.code.stackexchange.schema.Paging;
import com.google.code.stackexchange.schema.StackExchangeSite;
import com.google.code.stackexchange.schema.User;

class Utils {

	private static final StackExchangeSite STACK_OVERFLOW = StackExchangeSite.STACK_OVERFLOW;
	static final User.QuestionSortOrder MOST_RELEVANT = User.QuestionSortOrder.MOST_RELEVANT;
	final static StackExchangeApiQueryFactory factory = StackExchangeApiQueryFactory.newInstance("", STACK_OVERFLOW);

	static PagedList<?> getResults(StackExchangeApiQuery query) {
		return query.list();
	}

	static Paging getPaging() {
		int pageNumber = 1;
		int size       = 100;
		return getPaging(pageNumber, size);
	}

	static Paging getPaging(int pageNumber, int size) {
		return new Paging(pageNumber, size);
	}

}
