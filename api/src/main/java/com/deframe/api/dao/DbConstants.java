package com.deframe.api.dao;

public class DbConstants {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://deframedevdb.c3qzzpfemlfn.us-east-1.rds.amazonaws.com:3306/DeframeDb";
	static final String USER = "root";
	static final String PASSWORD = "root1234";

	public class Tables {
		public static final String TABLE_USERS = "deframedb.users";
		public static final String TABLE_MUSUEMS = "deframedb.musuems";
		
	}

	public class Columns {
		// USER
		public static final String FIRST_NAME = "first_name";
		public static final String LAST_NAME = "last_name";
		public static final String PHONE_NUM = "phone_num";
		public static final String EMAIL_ADDRESS = "email_address";
		public static final String USERTABLE_USERNAME = "username";
		
		// RESTAURANTSMEAL
		public static final String USER_NAME = "userName";
		public static final String ORDER_DATE  = "orderDate";
		

		// MEALPASS
		public static final String MEAL_USED  = "mealUsed";
		public static final String PLAN_NAME  = "planName";
		public static final String MEAL_CYCLE_END_DATE  = "mealCycleEndDate";
		public static final String MEAL_CYCLE_START_DATE  = "mealCycleStartDate";
		public static final String MEAL_TOTAL  = "mealTotal";
		
	}

}
