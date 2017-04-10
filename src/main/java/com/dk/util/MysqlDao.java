package com.dk.util;

import java.sql.Connection;

public class MysqlDao {
	public static void main(String[] args) throws Exception {
		Connection conn = DBUtil.getConnection();
		System.out.println(conn);
	}

}
