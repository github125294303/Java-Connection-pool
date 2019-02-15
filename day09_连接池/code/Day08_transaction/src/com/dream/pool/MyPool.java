package com.dream.pool;

import java.sql.Connection;
import java.util.Collections;
import java.util.LinkedList;

import com.dream.utils.DBUtils;
// 模拟连接池,但不具备实际的开发意义
public class MyPool {
	// 用这个集合的作用是它有addLast方法和removeFirst方法
	private static LinkedList<Connection> pool  = new LinkedList<Connection>();
	// 初始化10个连接
	static {
		for(int i=1;i<=10;i++){
			try {
				// 把连接放入连接池
				Connection conn = DBUtils.getConnection();
				pool.add(conn);
			} catch (Exception e) {
				throw new ExceptionInInitializerError("初始化连接失败,请检查配置文件是否正确");
			}
		}
	}
	
	// 不再使用DBUtils.getConnection方法了
	public static Connection getConnectionFromPool(){
		Connection conn = null;
		if(pool.size()>0){
			conn = pool.removeFirst();//移除并且返回第一个
		}else{
			// 1、等待
			// 2、创建一个新的连接,用完了可以直接关闭
			throw new RuntimeException("服务器(真的)繁忙,请稍后重试");
		}
		return conn;
	}
	
	// 释放资源--把连接放回到连接池而不是关闭
	public static void release(Connection conn){
		pool.addLast(conn);
	}
}
