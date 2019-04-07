package org.development.enhancer.analysis.exception.apis;

import com.google.code.stackexchange.common.PagedList;
import com.google.code.stackexchange.schema.Answer;
import com.google.code.stackexchange.schema.Question;
import org.development.enhancer.analysis.exception.utils.AnswerUtils;
import org.development.enhancer.analysis.exception.utils.QuestionUtils;

import java.util.*;

class ExceptionHandler {

	static void handleException(String message) {
		Map<Question, Answer> acceptedAnswerInfo = new HashMap<>();
		Map<Long, Question>   questionInfo       = new HashMap<>();
		List<Long>            answerIds          = new ArrayList<>();
		List<String>          tags               = new ArrayList<>();
		tags.add("java");
		PagedList<Question> results = QuestionUtils.getQuestions(QuestionUtils.getQuery(message, tags));
		for (Question result : results) {
			System.out.println(result.getTitle());
			if (result.getAcceptedAnswerId() == 0) {
				continue;
			}
			long acceptedAnswerId = result.getAcceptedAnswerId();
			answerIds.add(acceptedAnswerId);
			questionInfo.put(acceptedAnswerId, result);
		}
		PagedList<Answer> answers = AnswerUtils.getAnswer(answerIds);
		for (Answer answer : answers) {
			long answerId = answer.getAnswerId();
			acceptedAnswerInfo.put(questionInfo.get(answerId), answer);
		}
		displayInfo(acceptedAnswerInfo);
	}

	private static void displayInfo(Map<Question, Answer> info) {
		for (Map.Entry<Question, Answer> entry : info.entrySet()) {
			Question question = entry.getKey();
			if (question == null) continue;
			Answer answer = entry.getValue();
			if (answer == null) {
				System.out.println("No accepted answers");
				continue;
			}
			System.out.println(question.getTitle() + "\n" + answer.getBody());
			if (!userInteraction()) break;
		}
	}

	private static boolean userInteraction() {
		System.out.println("Show next question? y|n");
		String input = readInput().toLowerCase();
		if ("y".equals(input)) {
			return true;
		} else if ("n".equals(input) || "q".equals(input)) {
			return false;
		}
		System.out.println("Incorrect input, try again. Q to quit");
		return userInteraction();
	}

	private static String readInput() {
		try (Scanner scanner = new Scanner(System.in)) {
			return scanner.nextLine();
		}
	}
}
