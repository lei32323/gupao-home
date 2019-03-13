package cglib.dbroute;

/**
 * 设置成单例模式(ThreadLocal)
 */
public class DataSourceEntity  {

    private DataSourceEntity(){}

    //同线程中单例
    private static ThreadLocal<DataSource> dataSourceThreadLocal = new ThreadLocal<>();


    static {
        DataSource dataSource = new DataSource();
        dataSource.setType("ALL");
        dataSourceThreadLocal.set(dataSource);

    }

    //提供一个存储的方式
    public static void setDataSource(String type){
        DataSource dataSource  = new DataSource();
        dataSource.setType(type);
        dataSourceThreadLocal.set(dataSource);
        System.out.println("执行[db_"+type+"]");
    }

    //提供一个清除的方法
    public static void clear(){
        dataSourceThreadLocal.set(null);
    }

    //反序列化
    private Object readResovle(){
        return dataSourceThreadLocal.get();
    }

}
