package com.pr.util;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by iuliana.cosmina on 4/15/17.
 */
public class CleanUp {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private void destroy(){
		JdbcTemplate destroyerTemplate = new JdbcTemplate(dataSource);
		destroyerTemplate.execute("DROP ALL OBJECTS DELETE FILES;");
	}
}
