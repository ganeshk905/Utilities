package org.automation.jdbc;

import javax.management.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shantonu on 12/31/16.
 * 
 */
public class ResultSetUtils {

  /**
  *Assume that you have a DB table and you have a model class where each field name matched to DB column names 
  *and types of the class fields are  are big enought to contains values from DB. you can use this util to get all records 
*  to a list. Make sure you put setters of all fields. And result set has some default unit conversion rules when returning as object.THose will be applied 
* Example : String ss = "Select * from MyTable";
* Statement stmt = Gateway.getConnection().createStatement();
* ResultSet rs = stmt.executeQuery(ss);
* List<MyClass> items = createObjects(rs, MyClass.class); 
* and you get whole table as list. 
*Note : In here, my table , my class had a newmeric & a string filed. working fine, test and let me know in which cases does not work
  **/
    public static <T> List<T> createObjects(ResultSet resultSet, Class<T> tClass) throws
            SQLException, IllegalAccessException, InstantiationException, IntrospectionException, InvocationTargetException, java.beans.IntrospectionException {
        List<T> listImtes = new ArrayList<T>();
        while (resultSet.next()) {
            T intance = tClass.newInstance();
            for (Field field : tClass.getDeclaredFields()) {
                PropertyDescriptor pd = new PropertyDescriptor(field.getName(), tClass);
                //System.out.println("PD " + pd.getName());
                Method method = pd.getWriteMethod();
              /*  System.out.println("Method : " + method.getName());
                System.out.println("Method : Parameter count " + method.getParameterCount());*/
                Class[] types = method.getParameterTypes();
                //System.out.println("Method : Parameter type "+types[0]);
                Object value = resultSet.getObject(field.getName());
                method.invoke(intance, (types[0]).cast(value));
            }
            listImtes.add(intance);
        }
        return listImtes;
    }
  
//result set that has blob return for its select method can use this
  public static void savefile(ResultSet rs, final String file) throws IOException, SQLException {
    int BUFFER_SIZE = 50000;
        File m_file = new File(file);
        Blob blob = rs.getBlob(1);
        InputStream inputStream  =blob.getBinaryStream();
        OutputStream outputStream = new FileOutputStream(m_file);
        int bytes = -1;
        byte[] buffer = new byte[BUFFER_SIZE];
        while ((bytes=inputStream.read(buffer))!=-1){//read untill bytes end of stream
            outputStream.write(buffer,0,bytes);
        }
        inputStream.close();
        outputStream.close();
  }
  

  
  public static List<List<String>> getAllResutlsInTable(final ResultSet resultSet) throws SQLException {
        final List<List<String>> table = new ArrayList<>();
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (resultSet.next()) {
            List<String> aRow = new ArrayList<>();
            for (int i = 1; i <= columnsNumber; i++) {

                //String columnValue = resultSet.getString(i);

                //System.out.print(columnValue + " " + rsmd.getColumnName(i));
                aRow.add(resultSet.getString(i));
            }
            System.out.println("");
        }
        return table;
    }

 public static void printAllItemInResultSet(ResultSet resultSet) throws SQLException {
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        while (resultSet.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",  ");
                String columnValue = resultSet.getString(i);
                System.out.print(columnValue + " " + rsmd.getColumnName(i));
            }
            System.out.println("");
        }
    }
  //bad way to get, traverse full result set
   public static int getRowCount(ResultSet rs) throws SQLException {
        int count = 0;
        while (rs.next()){
            count++;
        }
        return count;
    }

    public static void closeResultSet(ResultSet rs) throws SQLException {
        if (rs != null) {
            rs.close();
        }
    }

    public static void closeStatement(Statement stmt) throws SQLException {
        if (stmt != null) {
            stmt.close();
        }
    }

    public static void closeResultSetAndStatement(ResultSet rs, Statement stmt) throws SQLException {
        try {
            closeResultSet(rs);
        } finally {
            closeStatement(stmt);
        }
    }
    public static String getFirstItem(ResultSet rs) throws SQLException {
        rs.next();
        return rs.getString(1);
    }
  
   public static <T> T getFirstItem(ResultSet rs, Class<T> aClass) throws SQLException {
        rs.next();
        return (T)rs.getObject(1);
    }
}
