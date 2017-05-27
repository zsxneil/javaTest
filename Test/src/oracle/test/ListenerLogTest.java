package oracle.test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ListenerLogTest {
	
	public static void main(String[] args) {
		execGathetStats();
	}
	
	public static void grantJavaRole() {
		Connection conn = null;// 创建一个数据库连接
	    PreparedStatement pstmt = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    String sql = "select username from user_users";
	    try {
	    	conn = OracleConnect.getConnection();
	    	System.out.println("连接成功！");
	    	pstmt = conn.prepareStatement(sql);// 实例化预编译语句
	 	    rs = pstmt.executeQuery();// 执行查询，注意括号中不需要再加参数
	        if (rs.next()) {
	        	System.out.println(rs.getString(1));
	 			sql = "grant JAVASYSPRIV,JAVAIDPRIV,JAVAUSERPRIV to " + rs.getString(1);
	 		}
	        stmt = conn.createStatement();
	        stmt.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OracleConnect.cleanup(rs, pstmt, conn);
			OracleConnect.cleanup(null, stmt, null);
		}
	}
	
	//创建获取监听日志路径信息的Oracle数据库的java-source
	public static void createDirectoryJavaSource() {
		Connection conn = null;// 创建一个数据库连接
		Statement stmt = null;
	    ResultSet rs = null;
	    String sql = null;
	    
	    StringBuffer buffer = new StringBuffer();
	    buffer.append("create or replace and compile java source named \"showDirectory\" as ");
	    buffer.append("import java.io.BufferedReader;");
	    buffer.append("import java.io.InputStreamReader;");
	    buffer.append("import java.io.*;");
	    buffer.append("public class ShowDirectory{ ");
	    buffer.append("public static String show(String cmd) { ");
	    buffer.append("StringBuffer pingResult = new StringBuffer();");
	    buffer.append("String showCmd = cmd;");
	    buffer.append("BufferedReader in;");
	    buffer.append("try { ");
	    buffer.append("Runtime r = Runtime.getRuntime();");
	    buffer.append("Process p = r.exec(showCmd);");
	    buffer.append("in = new BufferedReader(new InputStreamReader(p.getInputStream()));");
	    buffer.append("String inputLine;");
	    buffer.append("while ((inputLine = in.readLine()) != null) {pingResult.append(inputLine);}");
	    buffer.append("in.close();");
	    buffer.append("return pingResult.toString();}");
	    buffer.append("catch (Exception e) {return e.getMessage();}}}");
	    sql = buffer.toString();
	    System.out.println(sql);
	    try {
	    	conn = OracleConnect.getConnection();
	    	System.out.println("连接成功！");
	    	//pstmt = conn.prepareStatement(sql);
	    	//pstmt.executeUpdate();
	    	stmt = conn.createStatement();
	    	stmt.setEscapeProcessing(false);//直接发送sql到数据库，不要进行预编译，否则会报：不符合SQL92的异常
	    	boolean result = stmt.execute(sql);
	    	System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OracleConnect.cleanup(rs, stmt, conn);
		}
	}
	
	//使用java-source创建获取监听日志路径信息的函数
	public static void createDirectoryFunction() {
		Connection conn = null;// 创建一个数据库连接
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//创建函数的语句最后需要加上分号，否则会创建失败
		String sql = "CREATE OR REPLACE FUNCTION show_directory(str in varchar2) RETURN varchar2 AS LANGUAGE JAVA NAME 'ShowDirectory.show(java.lang.String) return java.lang.String';";
		System.out.println(sql);
		try {
			conn = OracleConnect.getConnection();
			System.out.println("连接成功！");
			//conn.setAutoCommit(false);
			//pstmt = conn.prepareStatement(sql);
			//pstmt.executeUpdate();
			stmt = conn.createStatement();
			stmt.setEscapeProcessing(false);
			boolean result = stmt.execute(sql);
			//conn.commit();
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OracleConnect.cleanup(rs, pstmt, conn);
		}
	}
	
	//调用函数获取监听日志信息返回结果
	public static void getDirectory() {
		Connection conn = null;// 创建一个数据库连接
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//创建函数的语句最后需要加上分号，否则会创建失败
		String sql = "select show_directory('lsnrctl show log_file') from dual";
		System.out.println(sql);
		try {
			conn = OracleConnect.getConnection();
			System.out.println("连接成功！");
			//conn.setAutoCommit(false);
			//pstmt = conn.prepareStatement(sql);
			//pstmt.executeUpdate();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			String directoryStr = null;
			if (rs.next()) {
				directoryStr = rs.getString(1);
				System.out.println(directoryStr);
				int positionStart = directoryStr.indexOf("设为");
				int positionEnd = directoryStr.indexOf("命令执行成功");
				System.out.println(directoryStr.substring(positionStart + 2,positionEnd).trim());
			}
			
			//conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OracleConnect.cleanup(rs, pstmt, conn);
		}
	}
	
	//创建获取监听日志文件大小的java-source
	public static void createFileJavaSource() {
		Connection conn = null;// 创建一个数据库连接
		Statement stmt = null;
	    ResultSet rs = null;
	    String sql = null;
	    
	    StringBuffer buffer = new StringBuffer();
	    
	    buffer.append("create or replace and compile java source named \"DirectorySize\" as ");
	    buffer.append("import java.io.File;");
	    buffer.append("public class DirectorySize {");
	    buffer.append("public static double fileSize(String path) {");
	    buffer.append("File file = new File(path);");
	    buffer.append("return getDirSize(file);}");
	    buffer.append("public static double getDirSize(File file) { ");
	    buffer.append("if (file.exists()) {");
	    buffer.append("if (file.isDirectory()) { ");
	    buffer.append("File[] children = file.listFiles();");
	    buffer.append("double size = 0;");
	    buffer.append("for (File f : children) ");
	    buffer.append("size += getDirSize(f);");
	    buffer.append("return size;");
	    buffer.append("} else { ");
	    buffer.append("double size = (double) file.length() / 1024 / 1024; ");
	    buffer.append("return size;}");
	    buffer.append("} else { return 0.0;}}} ");
	    sql = buffer.toString();
	    System.out.println(sql);
	    try {
	    	conn = OracleConnect.getConnection();
	    	System.out.println("连接成功！");
	    	//pstmt = conn.prepareStatement(sql);
	    	//pstmt.executeUpdate();
	    	stmt = conn.createStatement();
	    	stmt.setEscapeProcessing(false);//直接发送sql到数据库，不要进行预编译，否则会报：不符合SQL92的异常
	    	boolean result = stmt.execute(sql);
	    	System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OracleConnect.cleanup(rs, stmt, conn);
		}
	}
	
	//使用java-source创建获取监听日志大小的函数
	public static void createFileFunction() {
		Connection conn = null;// 创建一个数据库连接
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//创建函数的语句最后需要加上分号，否则会创建失败
		String sql = "CREATE OR REPLACE FUNCTION file_size(str in varchar2) RETURN number AS LANGUAGE JAVA NAME 'DirectorySize.fileSize(java.lang.String) return double';";
		System.out.println(sql);
		try {
			conn = OracleConnect.getConnection();
			System.out.println("连接成功！");
			//conn.setAutoCommit(false);
			//pstmt = conn.prepareStatement(sql);
			//pstmt.executeUpdate();
			stmt = conn.createStatement();
			stmt.setEscapeProcessing(false);
			boolean result = stmt.execute(sql);
			//conn.commit();
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OracleConnect.cleanup(rs, pstmt, conn);
		}
	}
	
	//调用函数获取监听日志的大小返回结果
	public static void getDirectorySize() {
		Connection conn = null;// 创建一个数据库连接
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//创建函数的语句最后需要加上分号，否则会创建失败
		String sql = "select file_size('d:\\oracle\\diag\\tnslsnr\\HSZC1007-0145\\listener') from dual";
		System.out.println(sql);
		try {
			conn = OracleConnect.getConnection();
			System.out.println("连接成功！");
			//conn.setAutoCommit(false);
			//pstmt = conn.prepareStatement(sql);
			//pstmt.executeUpdate();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			String fileSize = null;
			if (rs.next()) {
				fileSize = rs.getString(1);
				System.out.println(fileSize);
			}
			//conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OracleConnect.cleanup(rs, pstmt, conn);
		}
	}
	
	//使用系统函数执行统计分析（pl/sql执行成功，jdbc未执行成功）
	public static void execGathetStats() {
		Connection conn = null;// 创建一个数据库连接
		ResultSet rs = null;
		CallableStatement cstmt = null;
		Statement stmt = null;
		//创建函数的语句最后需要加上分号，否则会创建失败
		//String sql = "{call dbms_stats.gather_schema_stats( ownname => 'guide' )}";
		String sql = "call dbms_stats.gather_schema_stats( ownname => 'guide' )";
		System.out.println(sql);
		try {
			
			conn = OracleConnect.getConnection();
			System.out.println("连接成功！");
			stmt = conn.createStatement();
			stmt.setEscapeProcessing(false);
			
			cstmt = conn.prepareCall(sql);
			
			stmt.execute(sql);
			//System.out.println(result);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OracleConnect.cleanup(rs, stmt, conn);
		}
	}

}
