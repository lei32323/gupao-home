## ��̬���� 
��Ҫ����Ķ����������ڴ�������С�
+ ��Ҫ�������һ��Ҫ�нӿ�
+ ������Ҫʵ��InvocationHandler�ӿ�
### ������
    ������������Ҫ������С�����ܶ���
    1.С����Ҫ���Ҫ��
    2.������Ҫ����С����Ҫ��ȥ��ɫ����

### ���� ��
    �ڷֿ�ֱ��ҵ���£���Ҫ��̬�л����ݿ�
    1.ÿһ�괴���Ķ����ŵ�һ������

![��̬����Դ�л�](uml/TIM��ͼ20190311200023.png)


## ��̬����


## ����?
ΪʲôJDK��̬������Ҫ��Ŀ����ʵ�ֵĽӿ��������ܳ���65535����

����jdk���ĵ����˽⣺
~~~
The resulting proxy class must not exceed any limits imposed on classes by the virtual machine. For example, the VM may limit the number of interfaces that a class may implement to 65535; in that case, the size of the interfaces array must not exceed 65535.
~~~
���룺
���ɵĴ����಻�ܳ������������ǿ�ӵ��κ����ƣ�VM���������ʵ�ֽӿڵ����������65535��.

�ֲ��������˽⵽��
+ ����javaʹ��UNICODE(UCS-2)��׼�ַ�����Ϊ16λ�����һ���ܱ�ʾ2��16�η����ַ�����65535��

+ ���ӿڵ�ֱ�ӳ��ӿڵ�������ClassFile�ṹ��interfaces_count��Ĵ�С����Ϊ65535��

+ Class�ļ��з������ֶεȶ���Ҫ����CONSTANT_UTF8_info�ͳ������������ƣ�����CONSTANT_UTF8_info�ͳ�������󳤶�Ҳ����Java�з������ֶ�������󳤶ȡ����ֵlength��65535������Java��������������˳���64KBӢ���ַ��ı����򷽷����������޷����롣

+ ��java�У�Java���ӿ���������65,535�ַ�����Java�й��캯���Ĵ�������Ϊ65,535�ֽ�