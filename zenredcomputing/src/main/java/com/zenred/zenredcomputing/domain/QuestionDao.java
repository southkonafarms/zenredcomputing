package com.zenred.zenredcomputing.domain;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class QuestionDao extends AbstractJDBCDao {

	private static String tableNameGroup = "QuestionGroup";
	private static String tableNameQuestion = "Question";

	private class IntRowMapperQuestionGroupId implements
			ParameterizedRowMapper<Integer> {
		public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getInt("QuestionGroup_id");
		}
	}

	private class StringRowMapperQuestionGroupName implements
			ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("QuestionGroupName");
		}
	}

	private class StringQuestionId implements
			ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("Question_id");
		}
	}

	private class QuestionGroupId implements ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("QuestionGroup_id");
		}
	}
	
	private class QuestionContent implements ParameterizedRowMapper<String> {
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("question_content");
		}
	}
	
	private class QuestionGroupAnswer implements ParameterizedRowMapper<String>{
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString("QuestionGroupAnswer");
		}
		
	}
	
	public Integer numberOfQuestions(){
		Integer answer = 0;
		String sql = "SELECT COUNT(*) FROM QuestionGroup";
		answer = super.jdbcJohnTReddenSetUp().getSimpleJdbcTemplate().queryForInt(sql);
		return answer;
	}
	
	public List<Integer> questionGroupCollecton(){
		List<Integer> questionGroupIds = null;
		String sql = "SELECT QuestionGroup_id FROM QuestionGroup";
		questionGroupIds = super
				.jdbcJohnTReddenSetUp()
				.getSimpleJdbcTemplate()
				.query(sql, new IntRowMapperQuestionGroupId());
		return questionGroupIds;
	}
	
	public List<String> readQuestion(Integer id){
		List<String> questions = null;
		String sql = "SELECT question_content FROM Question WHERE QuestionGroup_id = ?";
		questions = super
				.jdbcJohnTReddenSetUp()
				.getSimpleJdbcTemplate()
				.query(sql, new QuestionContent(), id);
		return questions;
	}
	
	public String getAnswer(Integer QuestionGroup_id ){
		List<String> answer = null;
		String sql = "SELECT QuestionGroupAnswer FROM QuestionGroup WHERE QuestionGroup_id = ?";
		answer = super
				.jdbcJohnTReddenSetUp()
				.getSimpleJdbcTemplate()
				.query(sql, new QuestionGroupAnswer(), QuestionGroup_id);
		
		return answer.get(0);
	}
}
