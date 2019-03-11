## ԭ��ģʽ

### jdk����ʽ
��Ҫʵ��jdk�� `CloneAble` �ӿ�,��д`clone`����
~~~
public class UserVO implements Cloneable{
...
}
//����
UserVO clone = (UserVO)userVO.clone();
~~~
> ��Ҫ��¡�����Զ���Ҫʵ��`CloneAble`�ӿ�,��д`clone`����

### �������ʽ
~~~
 Field[] declaredFields = userVO.getClass().getDeclaredFields();
        UserVO clone = new UserVO();
        Stream.of(declaredFields).forEach(declaredField -> {
            try {
                //����Ȩ��
                declaredField.setAccessible(true);
                //���и�ֵ
                declaredField.set(clone,declaredField.get(userVO));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
~~~
> ʵ�ֵ�Ϊǳ��¡
### json����ʽ
~~~  
    //--------------fastjson
    //ת��ΪJson
    String json = JSON.toJSONString(userVO);
    //jsonת��Ϊʵ��
    UserVO parse = JSON.parseObject(json,UserVO.class);
    
    //---------------GSON
    //ת��Ϊjson
    Gson gson = new Gson();
    String s = gson.toJson(userVO);
    //ת��Ϊʵ��
    UserVO userVO1 = gson.fromJson(s, UserVO.class);
    System.out.println(userVO1+":"+userVO1.getRole());
~~~
> jsonʵ��Ϊ��ȿ�¡

### spring��BeanUtils��ʽ
~~~
    BeanUtils.copyProperties(userVO,clone);
~~~
> sping��BeanUtilsΪǳ����

### byte��ʽ
~~~
 //д��ȥ
try(ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();){
    try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteOutputStream);){
        objectOutputStream.writeObject(userVO);
    }
    
    //д����
    try(ByteArrayInputStream byteInputStream = new ByteArrayInputStream(byteOutputStream.toByteArray());) {
        try(ObjectInputStream objectInputStream = new ObjectInputStream(byteInputStream);){
            UserVO clone = (UserVO) objectInputStream.readObject();
            System.out.println(clone + ":" + clone.getRole());
        }
    }
}
~~~
> byte��ʽΪ��ȿ�¡

###  BeanCopier��ʽ
~~~
    BeanCopier b = BeanCopier.create(UserVO.class, UserVO.class, false);
    UserVO clone = new UserVO();
    b.copy(userVO,clone,null);
~~~
> BeanCopier��ʽΪǳ����

### ���ܱȽ�
> ����10000�ε�ִ��

byte��ʽ��1023ms��
JDK��ʽ��202ms��
json��ʽ(fastjson:584ms GSON:976ms)
������ʽ(315ms)
springBeanUtils��ʽ(458ms)
BeanCopier(235ms)

`JDK��ʽ < BeanCopier < ���� < springBeanUtils < fastjson < GSON < byte`

> ����100000�ε�ִ��

byte��ʽ��3963ms��
JDK��ʽ��1683ms��
json��ʽ(fastjson:2266ms GSON:4153ms)
������ʽ(2358ms)
spring��Bean��ʽ(2073ms)
BeanCopier(1096ms)

`BeanCopier < spring��BeanUtils  < JDK��ʽ < fastjson < ������ʽ < GSON < byte`



�ܽ᣺BeanCopierһֱ�ıȽ��ȶ��������spring��BeanUtils.