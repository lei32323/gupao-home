package dnroute.db;

/**
 * 动态管理dbSource的
 */
public class DynamicDataSourceEntity {

    private static final String DEFAULT_SOURCE = null;

    //线程中单例存放数据源
    private static ThreadLocal<String> datasources = new ThreadLocal<>();

    private DynamicDataSourceEntity() {
    }

    //获取
    public static String getDataSource() {
        return datasources.get();
    }

    //添加数据源
    public static void set(int year) {
        datasources.set("DB_" + year);
    }


    //把线程中的datasource清空
    public static void store() {
        datasources.set(DEFAULT_SOURCE);
    }


}
