package org.example;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.entity.Student;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    SqlSessionFactory sqlSessionFactory;

    try (Reader reader = Resources.getResourceAsReader("mybatis-config.xml")) {
      sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
      try (SqlSession sqlSession = sqlSessionFactory.openSession(true);) {
        List<Student> studentList = sqlSession.selectList("studentMapper.selectStudent");
        studentList.forEach(System.out::println);

        Student student = sqlSession.selectOne("studentMapper.selectStudentOne", 1);
        System.out.println(student);
      }
    } catch (IOException e) {
      throw new ExceptionInInitializerError(e);
    }
  }
}
