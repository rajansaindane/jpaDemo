package com.rajan.spring.jpaDemo.Controller;

import com.rajan.spring.jpaDemo.model.Student;
import com.rajan.spring.jpaDemo.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by rajan on 5/7/17.
 */
@RestController
public class StudentController {
@Autowired
    StudentRepo studentRepo;

    @GetMapping(value="/getdata")
    public List<Student> getList(){

        List<Student> list=studentRepo.findAll();
        return list;
    }


@PostMapping(value = "/postdata")
    public ResponseEntity<?> post(@RequestBody Student student)
{
    studentRepo.save(student);
    ResponseEntity<Student> entity=new ResponseEntity("saved", HttpStatus.OK);
    return entity;
}

    @RequestMapping(value = "/uploadImage")
    public String uploadImage(@RequestParam MultipartFile img,
                           //   @RequestParam Integer id,
                              @RequestParam String name,
                              @RequestParam String city)
{

    String path="/home/rajan/Desktop/image/";
    String image=path+img.getOriginalFilename();
    File f=new File(image);
    Student s=new Student();
   // s.setId(id);
    s.setName(name);
    s.setCity(city);
    s.setImagePath(image);
    studentRepo.save(s);
    try {
        img.transferTo(f);

    } catch (IOException e) {
        e.printStackTrace();
    }
    return "Data saved";
}


    @RequestMapping(value = "/uploadImages")
    public String uploadImage1(@RequestParam List<MultipartFile> imgList)
    {

        for(MultipartFile img:imgList) {

            String path = "/home/rajan/Desktop/image/";
            String image = path + img.getOriginalFilename();
            File f = new File(image);

            try {
                img.transferTo(f);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "Data saved";
    }


}
