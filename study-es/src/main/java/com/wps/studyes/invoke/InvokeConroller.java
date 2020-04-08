package com.wps.studyes.invoke;

import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@ResponseBody
public class InvokeConroller {



    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        //
       Class clazz = Class.forName("com.example.elasticsearch.invoke.Person");
        Method method = clazz.getMethod("getId", String.class);
        Constructor constructor = clazz.getConstructor();
        Object object = constructor.newInstance();
        Object aa=method.invoke(object, "11");
        /**
         * 通过反射创建类对象
         * 1:通过class对象的newInstance()方法；
         * 2:通过Constructor对象的newInstance()方法；
         */
        Class invokeClass = Class.forName("com.example.elasticsearch.invoke.Person");
        Person person=(Person) invokeClass.newInstance();
        person.getId("who");
        person.setName("通过Class对象实现");

        Constructor constructor1=invokeClass.getConstructor();
        Person person1= (Person) constructor1.newInstance();
        person1.getId("谁");
        person1.setName("通过Constructor方式实现");

        /**
         * 通过反射操作成员变量
         * 1:getFileds获得成员变量，但无法获得私有属性
         * 2:获取单个成员getField()&getDeclared
         *   修改成员变量的值set(Object obj, Object value)
         */
        Field[] fields = invokeClass.getFields();
        for (Field field :fields){
            System.out.println(field.getName());
        }
        Field field=invokeClass.getField("name");
        field.set(person1,"修改名字");

        Field prField=invokeClass.getDeclaredField("phone");
        prField.setAccessible(true);
        prField.set(person1,"私有修改后的手机号");
        /**
         * 通过反射操作成员方法
         */
        Constructor constructor2=invokeClass.getConstructor();
        Person person2=(Person) constructor2.newInstance();
        Method method1=invokeClass.getMethod("setName", String.class);
        method1.invoke(person2,"反射操作");








        System.out.println(person);



    }

}
