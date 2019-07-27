package com.itheima.product.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class C3P0Util {

	private static DataSource dataSource = new ComboPooledDataSource();
	
	
	public static DataSource getDataSource() {
		return dataSource;
	}


	public static Connection getConnection(){
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException("服务器忙……");
		}
	}
	
	public static void release(Connection conn,Statement stmt,ResultSet rs){
				if(rs!=null){
					try {
						rs.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					rs = null;
				}
				if(stmt!=null){
					try {
						stmt.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					stmt = null;
				}
				if(conn!=null){
					try {
						conn.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					conn = null;
				}
	}

    public static class ManagerThreadLocal {
    private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

    //�õ�һ������
    public static Connection getConnection(){
        Connection conn = tl.get();//�ӵ�ǰ�߳���ȡ��һ������
        if(conn==null){
        conn = C3P0Util.getConnection();//�ӳ���ȡ��һ��
        tl.set(conn);//��conn������뵽��ǰ�̶߳�����
        }
        return conn;
    }

    //��ʼ����
    public static void startTransacation(){
        try {
        Connection conn = getConnection();
        conn.setAutoCommit(false);//�ӵ�ǰ�̶߳�����ȡ�������ӣ�����ʼ����
        } catch (SQLException e) {
        e.printStackTrace();
        }
    }

    public static void commit(){
        try {
        getConnection().commit();//�ύ����
        } catch (SQLException e) {
        e.printStackTrace();
        }
    }

    public static void rollback(){
        try {
        getConnection().rollback();//�ع�����
        } catch (SQLException e) {
        e.printStackTrace();
        }
    }

    public static void close(){
        try {
        getConnection().close();//�����ӷŻس���
        tl.remove();//�ѵ�ǰ�̶߳����е�conn�Ƴ�
        } catch (SQLException e) {
        e.printStackTrace();
        }
    }
    }
}
