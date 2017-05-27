package oracle.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class OracleConnect {
	
	public static void main(String[] args) {
		functionTest();
	}
	

	/**
	 * 一个非常标准的连接Oracle数据库的示例代码
	 */
	public static void testOracle()
	{
	    Connection conn = null;// 创建一个数据库连接
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql = "select 2 * 3 from dual";
	    try {
	    	conn = getConnection();
	    	System.out.println("连接成功！");
	    	pstmt = conn.prepareStatement(sql);// 实例化预编译语句
	 	    rs = pstmt.executeQuery();// 执行查询，注意括号中不需要再加参数
	        if (rs.next()) {
	 			System.out.println("计算结果：" + rs.getString(1));
	 		}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cleanup(rs, pstmt, conn);
		}
	}
	
	public static Connection getConnection() {
		Connection conn = null;// 创建一个数据库连接
	    try {
	    	Class.forName("oracle.jdbc.driver.OracleDriver");// 加载Oracle驱动程序
	        System.out.println("开始尝试连接数据库！");
	        String url = "jdbc:oracle:" + "thin:@172.20.137.177:1521:orcl";// 127.0.0.1是本机地址，XE是精简版Oracle的默认数据库名
	        String user = "guide";// 用户名,系统默认的账户名
	        String password = "guide";// 你安装时选设置的密码
	        conn = DriverManager.getConnection(url, user, password);// 获取连接
		} catch (Exception e) {
			cleanup(null, null, conn);
			e.printStackTrace();
		} 
		return conn;
	}
	
	public static void cleanup(ResultSet rs , Statement stmt, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
				System.out.println("连接已关闭");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void functionTest() {
		Connection conn = null;// 创建一个数据库连接
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    String sql = "select show_test('') from dual";
	    try {
	    	conn = getConnection();
	    	System.out.println("连接成功！");
	    	pstmt = conn.prepareStatement(sql);// 实例化预编译语句
	 	    rs = pstmt.executeQuery();// 执行查询，注意括号中不需要再加参数
	        if (rs.next()) {
	 			System.out.println("计算结果：" + rs.getString(1));
	 		}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cleanup(rs, pstmt, conn);
		}
	}
	
}
