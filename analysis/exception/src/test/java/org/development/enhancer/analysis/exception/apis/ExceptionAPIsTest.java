package org.development.enhancer.analysis.exception.apis;

import org.testng.annotations.Test;

public class ExceptionAPIsTest {

	@Test
	public void testSearchException() {
		Exception exception = new ClassCastException("how to parse json in java");
		ExceptionAPIs.searchException(exception);
	}

	@Test
	public void testSearchException1() {
		ExceptionAPIs.searchException("Elasticsearch connection");
	}
}