package com.wps.studyplatform.curdxml.utils;

import com.sun.org.apache.xml.internal.resolver.readers.SAXCatalogReader;
import com.wps.studyplatform.curdxml.entity.StudentEntity;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class OperationUtil {
    private static String FILE_PATH="F:/项目/wpsproject/studyplatform/src/main/resources/curdxml/student.xml";
   //新增
    public static void addStudent(StudentEntity studentEntity){
        //在xml文件中添加一个学生信息
        InputStream input=null;
        SAXReader reader=new SAXReader();
        Document doc=null;
        try {
            input=new FileInputStream(FILE_PATH);
        System.out.println(input);
        doc=reader.read(input);
        Element root=doc.getRootElement();
        Element element=root.addElement("student");
        element.addElement("name").addText(studentEntity.getName());
        element.addElement("age").addText(String.valueOf(studentEntity.getAge()));
        element.addElement("isBoy").addText(String.valueOf(studentEntity.isBoy()));

        XMLWriter writer=new XMLWriter();
        FileOutputStream fos = new FileOutputStream(FILE_PATH);
        System.out.println(fos);
        writer.setOutputStream(fos);
        writer.write(doc);
        } catch (Exception e) {
            System.out.println("error");
        }finally {
            try{
                if (input!=null){
                    input.close();
                }
            }catch (IOException e){

                System.out.println(e.getMessage());
            }

        }
    }
    //更新学生信息
    public static void updateStudent(StudentEntity studentEntity){
        InputStream in = null;
        SAXReader reader = new SAXReader();
        Document doc = null;
        try
        {
            in = new FileInputStream(FILE_PATH);
            doc = reader.read(in);
            Element root = doc.getRootElement();
            for (Iterator it = root.elementIterator(); it.hasNext();)
            {
                Element element = (Element) it.next();
                if (studentEntity.getName().equals(element.element("name").getText()))
                {
                    element.element("age").setText(String.valueOf(studentEntity.getAge()));
                    element.element("isBoy").setText(String.valueOf(studentEntity.isBoy()));
                    break;
                }
            }
            FileWriter writer = new FileWriter(FILE_PATH);
            doc.write(writer);
            writer.flush();
            writer.close();
        }
        catch (Exception e)
        {
            System.out.println("error");
        }
        finally
        {
            try
            {
                in.close();
            }
            catch (IOException e)
            {
                System.out.println("error");
            }
        }



  }
  //列出xml中所有学生的信息
    public static List<StudentEntity> getStudents(){
        InputStream in = null;
        SAXReader reader = new SAXReader();
        Document doc = null;
        List<StudentEntity> Students = new ArrayList<>();
        try
        {
            in = new FileInputStream(FILE_PATH);
            doc = reader.read(in);
            Element root = doc.getRootElement();
            List<Element> elements = root.elements();
            Students = new ArrayList<StudentEntity>();
            for (Element element : elements)
            {
                StudentEntity student = new StudentEntity();
                student.setName(element.elementText("name"));
                student.setAge(Integer.valueOf(element.elementText("age")));
                student.setBoy(Boolean.valueOf(element.elementText("isBoy")));
                Students.add(student);
            }
        }
        catch (Exception e1)
        {
            System.out.println("error");
        }
        finally
        {
            try
            {
                in.close();
            }
            catch (IOException e)
            {
                System.out.println("error");
            }
        }

        return Students;
    }
    //判断某个学生是否存在
    public static boolean checkStudent(String name){
        InputStream in = null;
        SAXReader reader = new SAXReader();
        Document doc = null;

        try {
            in = new FileInputStream(FILE_PATH);
            doc = reader.read(in);
            Element root = doc.getRootElement();
            List<Element> elements = root.elements();
            for (Element element : elements)
            {
                if(name != null && name.equals(element.elementText("name")))
                    return true;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return false;

    }
//删除
    public static boolean deleteStudent(String name){
        InputStream in = null;
        SAXReader reader = new SAXReader();
        Document doc = null;

        try {
            in = new FileInputStream(FILE_PATH);
            doc = reader.read(in);
            Element root = doc.getRootElement();
            List<Element> elements = root.elements();
            for(Element element : elements)
            {
                if(name != null && name.equals(element.elementText("name")))
                    element.detach();
            }
            FileWriter writer = new FileWriter(FILE_PATH);
            doc.write(writer);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }
    //删除全部
    public static boolean deleteAll(){
        InputStream in = null;
        SAXReader reader = new SAXReader();
        Document doc = null;

        try {
            in = new FileInputStream(FILE_PATH);
            doc = reader.read(in);
            Element root = doc.getRootElement();
            List<Element> elements = root.elements();
            for(Element element : elements)
            {
                element.detach();
            }
            FileWriter writer = new FileWriter(FILE_PATH);
            doc.write(writer);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }



}
