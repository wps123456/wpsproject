package com.wps.studyplatform.stream;

import com.google.common.base.Function;
import org.apache.catalina.User;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {
    public static void main(String[] args) throws NoSuchFieldException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        List<UserEntity> userEntities=new ArrayList<>();
        userEntities.add( new UserEntity(1,5));
        userEntities.add( new UserEntity(1,2));
        userEntities.add( new UserEntity(1,4));
        userEntities.add( new UserEntity(4,8));
        userEntities.add( new UserEntity(5,9));
        userEntities.add( new UserEntity(6,7));

        UserEntity.class.getField("id");
        //先按id排序，然后再按number排序
        List<UserEntity> user= userEntities.stream().sorted(Comparator.comparing(UserEntity::getId).
                thenComparing(UserEntity::getNumber)).collect(Collectors.toList());
         //自定义排序、输入字段名

        Class invokeClass=Class.forName("com.wps.studyplatform.stream.UserEntity");
        Constructor constructor=invokeClass.getConstructor();
        UserEntity userEntity=(UserEntity) constructor.newInstance();
        Field field=invokeClass.getField("id");
        field.get(userEntity);
        List<UserEntity> userEntityList=userEntities.stream()
                .sorted(Comparator.comparing(UserEntity->UserEntity.getId()))
                .collect(Collectors.toList());
        System.out.println(user);




    }
}
