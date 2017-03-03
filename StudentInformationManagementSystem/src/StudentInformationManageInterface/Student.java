package StudentInformationManageInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Student{
    public String name, sex, profession, school;
    public int Political, English, Math, Professional, score;
    public List<String> number = new ArrayList<String>();
    public List<String> alphabet = new ArrayList<String>();
    List<String>tempNumber = Collections.synchronizedList(number);
    List<String>tempAlphabet = Collections.synchronizedList(alphabet);
    public Student(String n, String s1, String p1, String s2, int p2, int e, int m, int p3, int s3){
        name = n;
        sex = s1;
        profession = p1;
        school = s2;
        Political = p2;
        English = e;
        Math = m;
        Professional = p3;
        score = s3;
    }
    public Student(String string){
        for(String s : string.replaceAll("[^a-zA-Z]", ",").split(",")){
            if(s.length() > 0){
                tempAlphabet.add(s);
            }
        }
        name = tempAlphabet.get(0);
        sex = tempAlphabet.get(1);
        profession = tempAlphabet.get(2);
        school = tempAlphabet.get(3);
        for(String s : string.replaceAll("[^0-9]", ",").split(",")){
            if(s.length() > 0){
                tempNumber.add(s);
            }
        }
        Political = Integer.parseInt(tempNumber.get(0));
        English = Integer.parseInt(tempNumber.get(1));
        Math = Integer.parseInt(tempNumber.get(2));
        Professional = Integer.parseInt(tempNumber.get(3));
        score = Integer.parseInt(tempNumber.get(4));
    }
    public void display(){
        System.out.println("姓名："+name+" 性别："+sex+" 专业："+profession+" 院校："+school
                +"\n政治分数："+Political+" 英语分数："+English+" 数学分数："+Math+" 专业课程分数："+Professional);
    }
}