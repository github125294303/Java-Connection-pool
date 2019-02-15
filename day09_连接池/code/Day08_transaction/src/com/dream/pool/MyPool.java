package com.dream.pool;

import java.sql.Connection;
import java.util.Collections;
import java.util.LinkedList;

import com.dream.utils.DBUtils;
// ģ�����ӳ�,�����߱�ʵ�ʵĿ�������
public class MyPool {
	// ��������ϵ�����������addLast������removeFirst����
	private static LinkedList<Connection> pool  = new LinkedList<Connection>();
	// ��ʼ��10������
	static {
		for(int i=1;i<=10;i++){
			try {
				// �����ӷ������ӳ�
				Connection conn = DBUtils.getConnection();
				pool.add(conn);
			} catch (Exception e) {
				throw new ExceptionInInitializerError("��ʼ������ʧ��,���������ļ��Ƿ���ȷ");
			}
		}
	}
	
	// ����ʹ��DBUtils.getConnection������
	public static Connection getConnectionFromPool(){
		Connection conn = null;
		if(pool.size()>0){
			conn = pool.removeFirst();//�Ƴ����ҷ��ص�һ��
		}else{
			// 1���ȴ�
			// 2������һ���µ�����,�����˿���ֱ�ӹر�
			throw new RuntimeException("������(���)��æ,���Ժ�����");
		}
		return conn;
	}
	
	// �ͷ���Դ--�����ӷŻص����ӳض����ǹر�
	public static void release(Connection conn){
		pool.addLast(conn);
	}
}
