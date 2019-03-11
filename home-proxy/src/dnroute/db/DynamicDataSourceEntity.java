package dnroute.db;

/**
 * ��̬����dbSource��
 */
public class DynamicDataSourceEntity {

    private static final String DEFAULT_SOURCE = null;

    //�߳��е����������Դ
    private static ThreadLocal<String> datasources = new ThreadLocal<>();

    private DynamicDataSourceEntity() {
    }

    //��ȡ
    public static String getDataSource() {
        return datasources.get();
    }

    //�������Դ
    public static void set(int year) {
        datasources.set("DB_" + year);
    }


    //���߳��е�datasource���
    public static void store() {
        datasources.set(DEFAULT_SOURCE);
    }


}
