package com.wps.studyplatform.curdxml.controller;

import com.wps.studyplatform.curdxml.entity.StudentEntity;
import com.wps.studyplatform.curdxml.utils.OperationUtil;

public class XMLController {

    public static void main(String[] args) {
        StudentEntity studentEntity=new StudentEntity();
        studentEntity.setName("wps");
        studentEntity.setAge(28);
        studentEntity.setBoy(true);
         OperationUtil.addStudent(studentEntity);
    }
}
