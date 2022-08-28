package dao;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import util.DBUtil;
import util.StringUtils;

public abstract class AbstractDAOImpl<T> {
	protected Connection conn = DBUtil.getConnection();
	private PreparedStatement pstmt;
	/**
	 * 实现数据的批量删除，这个时候的批量删除属于彻底删除功能
	 * @param table 表名称
	 * @param column 删除表的列名称
	 * @param ids 所有的id数据，使用Set集合可以避免重复
	 * @return 如果删除成功返回true，否则返回false
	 * @throws SQLException
	 */
	protected boolean removeHandle(String table, String column, Set<Integer> ids)
			throws SQLException {
		if (ids.size() == 0) { // 表示现在没有任何的数据
			return false;
		}
		java.lang.StringBuffer buf = new StringBuffer();
		buf.append("DELETE FROM ").append(table).append(" WHERE ")
				.append(column).append(" IN (");
		Iterator<?> iter = ids.iterator();
		while (iter.hasNext()) {
			buf.append(iter.next()).append(",");
		}
		buf.delete(buf.length() - 1, buf.length()).append(")");
		this.pstmt = this.conn.prepareStatement(buf.toString());
		return this.pstmt.executeUpdate() == ids.size();
	}
	protected boolean removeHandleString(String table, String column, Set<String> ids)
			throws SQLException {
		if (ids.size() == 0) { // 表示现在没有任何的数据
			return false;
		}
		StringBuffer buf = new StringBuffer();
		buf.append("DELETE FROM ").append(table).append(" WHERE ")
				.append(column).append(" IN (");
		Iterator<?> iter = ids.iterator();
		while (iter.hasNext()) {
			buf.append("'").append(iter.next()).append("'").append(",");
		}
		buf.delete(buf.length() - 1, buf.length()).append(")");
		this.pstmt = this.conn.prepareStatement(buf.toString());
		return this.pstmt.executeUpdate() == ids.size();
	}
	/**
	 * 负责统计出数据量
	 * @param table 要统计数据的表名称
	 * @param column 模糊查询的数据列
	 * @param keyWord 模糊查询关键字
	 * @return 返回指定表的数据量，如果表没有数据，返回0
	 * @throws SQLException
	 */
	protected Integer countHandle(String table, String column, String keyWord)
			throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT(*) FROM ").append(table).append(" WHERE ")
				.append(column).append(" LIKE ?");
		this.pstmt = this.conn.prepareStatement(sql.toString());
		this.pstmt.setString(1, "%" + keyWord + "%");
		ResultSet rs = this.pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		}
		return 0;
	}

	/**
	 * 负责统计出数据量
	 * @param table 要统计数据的表名称
	 * @param column 模糊查询的数据列
	 * @param keyWord 模糊查询关键字
	 * @return 返回指定表的数据量，如果表没有数据，返回0
	 * @throws SQLException
	 */
	/**
	 * @param key 条件
	 * @param obj 条件值
	 * @param table 要统计数据的表名称
	 * @param column 模糊查询的数据列
	 * @param keyWord 模糊查询关键字
	 * @return 返回指定表的数据量，如果表没有数据，返回0
	 * @throws SQLException
	 */
	protected Integer countHandle(String key,Object obj,String table, String column, String keyWord)
			throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT(*) FROM ").append(table).append(" WHERE ")
				.append(column).append(" LIKE ?");
		sql.append(" AND ").append(key).append("=?");
		this.pstmt = this.conn.prepareStatement(sql.toString());
		this.pstmt.setString(1, "%" + keyWord + "%");
		if( obj instanceof String){
			this.pstmt.setString(2, (String)obj);
		}else if(obj instanceof Double){
			this.pstmt.setDouble(2, (Double)obj);
		} if(obj instanceof Integer){
			this.pstmt.setInt(2, (Integer)obj);
		}if(obj instanceof Date){
			this.pstmt.setDate(2, (java.sql.Date)obj);
		}
		ResultSet rs = this.pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt(1);
		}
		return 0;
	}
	protected Integer getIdHandle() throws SQLException{
		String sql = "SELECT LAST_INSERT_ID()";
		this.pstmt = this.conn.prepareStatement(sql);
		ResultSet rs = this.pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt(1);
		}
		return null;
	}
	/**
	 * 批量获取图片名称
	 * @param table	表名称
	 * @param photoColumn 图片字段名称
	 * @param column 主键字段名称
	 * @param ids 包含有主键信息的Set集合
	 * @return 包含有图片名称的Set集合
	 * @throws SQLException
	 */
	protected Set<String> photoHandle(String table, String photoColumn,
			String column, Set<?> ids) throws SQLException {
		Set<String> all = new HashSet<String>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ").append(photoColumn).append(" FROM ")
				.append(table).append(" WHERE ").append(column).append(" IN (");
		Iterator<?> iter = ids.iterator();
		while (iter.hasNext()) {
			sql.append(iter.next()).append(",");
		}
		sql.delete(sql.length() - 1, sql.length()).append(")");
		sql.append(" AND ").append(photoColumn).append("<>'nophoto.jpg'");
		this.pstmt = this.conn.prepareStatement(sql.toString());
		ResultSet rs = this.pstmt.executeQuery();
		while (rs.next()) {
			all.add(rs.getString(1));
		}
		return all;
	}
	/**
	 * 实现对任意表的查询全部信息操作
	 * @param cla VO的class类对象
	 * @return 返回包含有VO（类型为Object）类信息的List集合
	 * @throws SQLException
	 */
	protected List<T> findAll(Class<T> cla,boolean details) throws SQLException{
		String vname = cla.getName();
		StringBuffer sql = new StringBuffer("SELECT * FROM ");
		/*for(int x = 0 ;x < field.length ;x ++){
			sql.append(field[x].getName()).append(",");
		}
		sql.delete(sql.length() - 1 , sql.length());*/
		sql.append(vname.substring(vname.lastIndexOf(".") + 1).toLowerCase());
		this.pstmt = this.conn.prepareStatement(sql.toString());
		try {
			return selectAll(vname, this.pstmt,details);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	protected List<T> findAll(String key,Object value,Class<T> cla,boolean details) throws SQLException{
		String vname = cla.getName();
		StringBuffer sql = new StringBuffer("SELECT * FROM ");
		sql.append(vname.substring(vname.lastIndexOf(".") + 1).toLowerCase());
		sql.append(" WHERE " + key + "=?");
		this.pstmt = this.conn.prepareStatement(sql.toString());
		if( value instanceof String){
			this.pstmt.setString(1, (String)value);
		}else if(value instanceof Double){
			this.pstmt.setDouble(1, (Double)value);
		} if(value instanceof Integer){
			this.pstmt.setInt(1, (Integer)value);
		}if(value instanceof Date){
			this.pstmt.setDate(1, (java.sql.Date)value);
		}
		try {
			return selectAll(vname, this.pstmt,details);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 实现对任意表的分页查询信息操作
	* @param cla VO的class类对象
	 * @param currentPage 当前页数
	 * @param pageSize 一页所包含的行数
	 * @param column 模糊查询列
	 * @param keyWord 模糊查询关键字
	 * @return 返回包含有VO（类型为Object）类信息的List集合
	 * @throws SQLException
	 */
	protected List<T> findAllSplit(Class<T> cla,boolean details,Integer currentPage,Integer pageSize,String column,String keyWord) throws SQLException{
		String vname = cla.getName();
		StringBuffer sql = new StringBuffer("SELECT * FROM ");
		/*for(int x = 0 ;x < field.length ;x ++){
			sql.append(field[x].getName()).append(",");
		}
		sql.delete(sql.length() - 1 , sql.length());*/
		sql.append(vname.substring(vname.lastIndexOf(".") + 1).toLowerCase());
		sql.append(" WHERE " + column + " LIKE ? LIMIT ?,?");
		this.pstmt = this.conn.prepareStatement(sql.toString());
		this.pstmt.setString(1, "%" + keyWord +"%");
		this.pstmt.setInt(2, (currentPage -1) * pageSize);
		this.pstmt.setInt(3, pageSize);
		try {
			return selectAll(vname, this.pstmt, details);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 实现根据限定的条件分页查询所有数据
	 * @param cla VO的class类对象
	 * @param key 所要限定的条件列
	 * @param obj 所要限定的值
	 * @param currentPage 当前页数
	 * @param pageSize 一页所包含的行数
	 * @param column 模糊查询列
	 * @param keyWord 模糊查询关键字
	 * @return 返回包含有VO（类型为Object）类信息的List集合
	 * @throws SQLException
	 */
	protected List<T> findAllSplitByKey(Class<T> cla,boolean details,String key,Object obj,Integer currentPage,Integer pageSize,String column,String keyWord) throws SQLException{
		String vname = cla.getName();
		StringBuffer sql = new StringBuffer("SELECT * FROM ");
		/*for(int x = 0 ;x < field.length ;x ++){
			sql.append(field[x].getName()).append(",");
		}
		sql.delete(sql.length() - 1 , sql.length());*/
		sql.append(vname.substring(vname.lastIndexOf(".") + 1).toLowerCase());
		sql.append(" WHERE " + key + "=? AND " + column + " LIKE ? LIMIT ?,?");
		this.pstmt = this.conn.prepareStatement(sql.toString());
		if( obj instanceof String){
			this.pstmt.setString(1, (String)obj);
		}else if(obj instanceof Double){
			this.pstmt.setDouble(1, (Double)obj);
		} if(obj instanceof Integer){
			this.pstmt.setInt(1, (Integer)obj);
		}if(obj instanceof Date){
			this.pstmt.setDate(1, (java.sql.Date)obj);
		}
		this.pstmt.setString(2, "%" + keyWord +"%");
		this.pstmt.setInt(3, (currentPage -1) * pageSize);
		this.pstmt.setInt(4, pageSize);
		try {
			return selectAll(vname, this.pstmt, details);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 实现对任意表的查询单行记录的查询
	 * @param clas VO的class类对象
	 * @param column 主键名称
	 * @param value 指定的主键值
	 * @return 查询成功返回Object类型（根据需要再强制类型转换），否则返回null
	 * @throws SQLException
	 */
	protected T findByColumn(Class<T> clas,String column,Object value,boolean details) throws SQLException{
		String vname = clas.getName();
		StringBuffer sql = new StringBuffer("SELECT * FROM ");
		/*for(int x = 0 ;x < field.length ;x ++){
			sql.append(field[x].getName()).append(",");
		}
		sql.delete(sql.length() - 1 , sql.length());*/
		sql.append(vname.substring(vname.lastIndexOf(".") + 1).toLowerCase());
		sql.append(" WHERE " + column + "=?");
		this.pstmt = this.conn.prepareStatement(sql.toString());
		if( value instanceof String){
			this.pstmt.setString(1, (String)value);
		}else if(value instanceof Double){
			this.pstmt.setDouble(1, (Double)value);
		} if(value instanceof Integer){
			this.pstmt.setInt(1, (Integer)value);
		}if(value instanceof Date){
			this.pstmt.setDate(1, (java.sql.Date)value);
		}
		try {
			return select(vname, this.pstmt, details);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 实现对任意表的更新操作
	 * @param T VO类对象
	 * @param key 要更新的主键名称
	 * @return 更新成功返回true,否则返回false
	 * @throws SQLException
	 */
	protected boolean doUpdate(T t,String key) throws SQLException{
		String vname = t.getClass().getName();
		Class<?> cla = null;
		try {
			cla =  Class.forName(vname);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Field field[] = cla.getDeclaredFields();
		StringBuffer sql = new StringBuffer("UPDATE ");
		sql.append(vname.substring(vname.lastIndexOf(".") + 1).toLowerCase());
		sql.append(" SET ");
		//1、动态拼接SQL语句，将成功拼接的字段序号保存到List集合内，以便第2步预处理赋值使用
		List<Integer> columns = new ArrayList<Integer>();
		int temp = 0;
		for(int x = 0 ; x < field.length ; x++){
			Method method = null;
			try {
				method = t.getClass().getMethod("get" + StringUtils.initcap(field[x].getName()));
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(method.invoke(t) != null){
					if(!field[x].getName().equals(key)){//主键不进行更新
						if(!"protected".equals(Modifier.toString(field[x].getModifiers()))){
							String type = field[x].getType().getSimpleName();
							if(type.equals("Integer") || 
									type.equals("Double") || 
									type.equals("String") ||
									type.equals("Date")){
								sql.append(field[x].getName()).append("=?,");
								columns.add(x);						
							}else if(!(type.equals("Map") || //对类集不做处理
									type.equals("List") || 
									type.equals("Set"))){
								Class<?> tempCla = Class.forName(field[x].getType().getName());
								Field subField[] = tempCla.getDeclaredFields();
								Object subObject = method.invoke(t);
								for(int y=0 ;y < subField.length ;y ++){
									if(subField[y].getName().contains("id")){
										Method tempM = tempCla.getMethod("get" + StringUtils.initcap(subField[y].getName()));
										if(tempM.invoke(subObject) != null){
											sql.append(subField[y].getName()).append("=?,");
											columns.add(x);
										}
									}
								}
							}
						}
					}else{
						temp = x;//将主键在Field中的序号暂存下来
					}
				}
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		sql.delete(sql.length() - 1 , sql.length());
		sql.append(" WHERE ").append(key).append("=?");
		columns.add(temp);//拼接完SQL语句的前部分后再将主键保存的List集合内，以保证赋值顺序
		this.pstmt = this.conn.prepareStatement(sql.toString());
		//2、动态为pstmt赋值
		Iterator<Integer> iter = columns.iterator();
		int i=1;//初始化pstmt参数游标
		while(iter.hasNext()){
			int x = iter.next();
			//判断成员类型
			String type = field[x].getType().getSimpleName();
			Method method = null;
			try {
				method = t.getClass().getMethod("get" + StringUtils.initcap(field[x].getName()));
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(type.equals("Integer")){
				try {
					this.pstmt.setInt(i++,(Integer)method.invoke(t));
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(type.equals("String")){
				try {
					this.pstmt.setString(i++,(String)method.invoke(t));
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(type.equals("Double")){
				try {
					this.pstmt.setDouble(i++,(Double)method.invoke(t));
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(type.equals("Date")){
				Date date = null;
				try {
					date = (Date)method.invoke(t);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.pstmt.setTimestamp(i++,new Timestamp(date.getTime()));
			}else if(!(type.equals("Map") || 
					type.equals("List") || 
					type.equals("Set"))){
				Class<?> tempCla = null;
				try {
					tempCla = Class.forName(field[x].getType().getName());
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Object tempObj = null;
				try {
					tempObj = method.invoke(t);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Field subField[] = tempCla.getDeclaredFields();
				for(int y=0 ;y < subField.length ;y ++){
					if(subField[y].getName().contains("id")){
						Method tempM = null;
						try {
							tempM = tempCla.getMethod("get" + StringUtils.initcap(subField[y].getName()));
						} catch (SecurityException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (NoSuchMethodException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if(subField[y].getType().getSimpleName().equals("Integer")){
							try {
								this.pstmt.setInt(i++, (Integer)tempM.invoke(tempObj));
							} catch (IllegalArgumentException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IllegalAccessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}else if(subField[y].getType().getSimpleName().equals("String")){
							try {
								this.pstmt.setString(i++, (String)tempM.invoke(tempObj));
							} catch (IllegalArgumentException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IllegalAccessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
		return this.pstmt.executeUpdate() == 1;
	}
	/**
	 * 实现对任意表的插入一行记录的操作
	 * @param T	VO类对象
	 * @return 插入成功返回true,否则返回false
	 * @throws SQLException
	 */
	protected boolean doCreate(T t) throws SQLException {
		try {
			this.pstmt = this.conn.prepareStatement(this.createSQL(t));
			return this.create(t).executeUpdate() == 1;
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	protected boolean doCreateBatch(List<T> objs) throws SQLException {
		try {
			if(objs.size() == 0){
				return false;
			}
			this.pstmt = this.conn.prepareStatement(this.createSQL(objs.get(0)));
			Iterator<T> iter = objs.iterator();
			while(iter.hasNext()){
				T t = iter.next();
				this.create(t).addBatch();
			}
			int flag[] = this.pstmt.executeBatch();
			for( int x = 0 ; x < flag.length ; x++){
				if(flag[x] == 0){
					return false;
				}
			}
			return true;
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}
	
	
	/**********************************************************************
	 * 以下是私有方法！！！！！！！！！！
	 * @return 
	 **********************************************************************/
	
	//取得VO类对象，此方法供selectAll()、select()方法使用，不对外使用，所以声明权限为private
	private T getObj(Class<T> cla,Field field[],ResultSet rs,boolean details) throws InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, SQLException, ClassNotFoundException{
		T t = cla.newInstance();
		for(int x = 0 ;x < field.length ;x ++){
			Method method = cla.getMethod("set" + StringUtils.initcap(field[x].getName()), field[x].getType());
			if(!"protected".equals(Modifier.toString(field[x].getModifiers()))){
				String type = field[x].getType().getSimpleName();
				if(type.equals("Integer")){
					method.invoke(t, rs.getInt(field[x].getName()));
				}else if(type.equals("String")){
					method.invoke(t, rs.getString(field[x].getName()));
				}else if(type.equals("Double")){
					method.invoke(t, rs.getDouble(field[x].getName()));
				}else if(type.equals("Date")){
					if(details){
						method.invoke(t, rs.getTimestamp(field[x].getName()));						
					}else{
						method.invoke(t, rs.getDate(field[x].getName()));
					}
				}else if(!(type.equals("Map") || type.equals("List") || type.equals("Set"))){
					Class<?> tempCla = Class.forName(field[x].getType().getName());
					Field subField[] = tempCla.getDeclaredFields();
					Object tempObj = tempCla.newInstance();
					for(int y=0 ;y < subField.length ;y ++){
						if(subField[y].getName().contains("id") && !subField[y].getName().contains("idcard") ){
							Method tempM = tempCla.getMethod("set" + StringUtils.initcap(subField[y].getName()), subField[y].getType());
							if(subField[y].getType().getSimpleName().equals("Integer")){
								tempM.invoke(tempObj, rs.getInt(subField[y].getName()));
							}else if(subField[y].getType().getSimpleName().equals("String")){
								tempM.invoke(tempObj, rs.getString(subField[y].getName()));
							}
						}
					}
					method.invoke(t,tempObj);
				}
			}
		}
		return t;
	}
	//取得List<Object>集合，此方法供findAll()方法使用，不对外使用，所以声明权限为private
	@SuppressWarnings("unchecked")
	private List<T> selectAll(String vname,PreparedStatement pstmt,boolean details) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException{
		List<T> all = new ArrayList<T>();
		Class<T> cla = (Class<T>) Class.forName(vname);
		Field field[] = cla.getDeclaredFields();
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			all.add(getObj(cla,field,rs,details));
		}
		return all;
	}
	//取得VO类对象，此方法供findByColumn()方法使用，不对外使用，所以声明权限为private
	@SuppressWarnings("unchecked")
	private T select(String vname,PreparedStatement pstmt,boolean details) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException{
		Class<T> cla = (Class<T>) Class.forName(vname);
		Field field[] = cla.getDeclaredFields();
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()){
			return getObj(cla,field,rs, details);
		}
		return null;
	}
	/**
	 * 
	 * @param vname
	 * @param obj
	 * @return
	 */
	//取得VO类对象，此方法供doCreate()、doCreateBatch()方法使用，不对外使用，所以声明权限为private
	private PreparedStatement create(Object obj) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException {
			//2、动态为pstmt赋值
			Iterator<Integer> iter = this.columns.iterator();
			int i=1;//初始化pstmt参数游标
			while(iter.hasNext()){
				int x = iter.next();
				//判断成员类型
				String type = this.field[x].getType().getSimpleName();
				Method method = obj.getClass().getMethod("get" + StringUtils.initcap(this.field[x].getName()));
				if(type.equals("Integer")){
					this.pstmt.setInt(i++,(Integer)method.invoke(obj));
				}else if(type.equals("String")){
					this.pstmt.setString(i++,(String)method.invoke(obj));
				}else if(type.equals("Double")){
					this.pstmt.setDouble(i++,(Double)method.invoke(obj));
				}else if(type.equals("Date")){
					Date date = (Date)method.invoke(obj);
					this.pstmt.setTimestamp(i++,new Timestamp(date.getTime()));
				}else if(!(type.equals("Map") ||
						type.equals("List") ||
						type.equals("Set"))){
					Class<?> tempCla = Class.forName(this.field[x].getType().getName());
					Object tempObj = method.invoke(obj);
					Field subField[] = tempCla.getDeclaredFields();
					for(int y=0 ;y < subField.length ;y ++){
						if(subField[y].getName().contains("id") && !subField[y].getName().equals("idcard")){
							Method tempM = tempCla.getMethod("get" + StringUtils.initcap(subField[y].getName()));
							if(subField[y].getType().getSimpleName().equals("Integer")){
								this.pstmt.setInt(i++, (Integer)tempM.invoke(tempObj));
							}else if(subField[y].getType().getSimpleName().equals("String")){
								this.pstmt.setString(i++, (String)tempM.invoke(tempObj));
							}
						}
					}
				}
			}
			return this.pstmt;

	}
	//取得VO类对象，此方法供doCreate()、doCreateBatch()方法使用，不对外使用，所以声明权限为private
	private String createSQL(Object obj) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		String vname = obj.getClass().getName();
		Class<?> cla = Class.forName(vname);
		this.field = cla.getDeclaredFields();
		StringBuffer sql = new StringBuffer("INSERT INTO ");
		sql.append(vname.substring(vname.lastIndexOf(".") + 1).toLowerCase());
		sql.append("(");
		//1、动态拼接SQL语句，将成功拼接的字段数组序号保存到List集合内，以便第2步预处理赋值使用
		this.columns = new ArrayList<Integer>();
		for(int x = 0 ; x < this.field.length ; x++){
			
			Method method = obj.getClass().getMethod("get" + StringUtils.initcap(this.field[x].getName()));
			if(method.invoke(obj) != null){
				if(!"protected".equals(Modifier.toString(this.field[x].getModifiers()))){
					String type = this.field[x].getType().getSimpleName();
					if(type.equals("Integer") || 
							type.equals("Double") || 
							type.equals("String") ||
							type.equals("Date")){
						sql.append(this.field[x].getName()).append(",");
						this.columns.add(x);						
					}else if(!(type.equals("Map") || //对类集不做处理
							type.equals("List") || 
							type.equals("Set"))){
						Class<?> tempCla = Class.forName(this.field[x].getType().getName());
						Field subField[] = tempCla.getDeclaredFields();
						for(int y=0 ;y < subField.length ;y ++){
							if(subField[y].getName().contains("id") && !subField[y].getName().equals("idcard")){
								sql.append(subField[y].getName()).append(",");
								this.columns.add(x);
							}
						}
					}
				}
			}
		}
		sql.delete(sql.length() - 1 , sql.length()).append(")");
		sql.append(" VALUES (");
		Iterator<Integer> iter = this.columns.iterator();
		while(iter.hasNext()){
			iter.next();
			sql.append("?,");
		}
		sql.delete(sql.length() - 1 , sql.length()).append(")");
		return sql.toString();
	}
	private List<Integer> columns;
	private Field field[] ;
}
