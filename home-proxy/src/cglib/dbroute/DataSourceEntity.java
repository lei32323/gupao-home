package cglib.dbroute;

/**
 * ���óɵ���ģʽ(ThreadLocal)
 */
public class DataSourceEntity  {

    private DataSourceEntity(){}

    //ͬ�߳��е���
    private static ThreadLocal<DataSource> dataSourceThreadLocal = new ThreadLocal<>();


    static {
        DataSource dataSource = new DataSource();
        dataSource.setType("ALL");
        dataSourceThreadLocal.set(dataSource);

    }

    //�ṩһ���洢�ķ�ʽ
    public static void setDataSource(String type){
        DataSource dataSource  = new DataSource();
        dataSource.setType(type);
        dataSourceThreadLocal.set(dataSource);
        System.out.println("ִ��[db_"+type+"]");
    }

    //�ṩһ������ķ���
    public static void clear(){
        dataSourceThreadLocal.set(null);
    }

    //�����л�
    private Object readResovle(){
        return dataSourceThreadLocal.get();
    }

}
