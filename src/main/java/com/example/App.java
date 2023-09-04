package com.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.models.Student;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) throws IOException {
        System.out.println("Project Started...!!");

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

        FileInputStream fileInputStream = new FileInputStream(new File("src/main/resources/image.png"));
        byte[] image = new byte[fileInputStream.available()];
        fileInputStream.read(image);

        Student student = Student.builder()
                .studentName("Rishabh Rawat")
                .rollNo(67)
                .createdAt(new Date())
                .image(image)
                .temp(100)
                .build();

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        session.persist(student);

        System.out.println((Student) session.get(Student.class, 1));

        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }

}
