import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.math.BigDecimal;

abstract class Management{ //Abstract class
String firstName;
String lastName;
String id;
List<String> courses;
BigDecimal tuition;
Scanner keyboard = new Scanner(System.in);
abstract BigDecimal getTuition();
abstract String getName();
abstract void setFirstName(String firstName);
abstract void setLastName(String lastName);
abstract String getId();
abstract void setId(String id);
abstract void makeID();
abstract void displayInfo(Student[] studentList);
abstract String randomString();
}
interface Course{ //Interface
public void payForCourses();
}
interface ID{
public void makeID();
}
public class Student extends Management { //Inheritance
private Student() {
System.out.println("\n----------------------------------Welcome To Student Management System----------------------------------\n\n");
}
private Student(String fName, String lastName) {
this.firstName = fName;
this.lastName = lastName;
}
BigDecimal getTuition() { return tuition; }
void setTuition(BigDecimal money) {
this.tuition = money;
}
String getName() { return firstName + " " + lastName; }
void setFirstName(String firstName) { this.firstName = firstName; }
void setLastName(String lastName) { this.lastName = lastName; }
String getId() { return id; }
void setId(String id) { this.id = id; }
List<String> getCourses() { return courses; }
void setCourses(List<String> courses) { this.courses = courses; }
void makeID()
{
ID i=()->{ // lambda expression
String grade;
boolean checked = false;
while (!checked)
{
System.out.println("Enter your school year 1. Freshman, 2. Sophomore, 3.Junior and 4.Senior ");
grade = keyboard.nextLine();
if (grade.length() == 1 && Integer.parseInt(grade) > 0 && Integer.parseInt(grade) < 5)
{
setId(grade.concat(randomString()));
checked = true;
} 
else {
System.out.println("The input you enter is incorrect please try again");
}
}
};
i.makeID();
}
String randomString()
{
String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
Random random = new Random();
int great = AB.length();
int temp;
String codeword = "";
for (int i = 0; i < 4; i++)
{
temp = (int) (random.nextFloat() * great);
codeword = codeword.concat(Character.toString(AB.charAt(temp)));
}
return codeword;
}
void payForCourses()
{
Course c=()->{ // lambda expression
String answer;
BigDecimal payment;
BigDecimal moneyLeftOver;
while (getTuition().compareTo(BigDecimal.ZERO) > 0)
{
System.out.println("Your current balance is Rs" + getTuition());
System.out.println("Do you want pay off your balance right now");
answer = keyboard.nextLine();
if (answer.toLowerCase().equals("yes"))
{
System.out.println("How much would you like to pay right now");
if (keyboard.hasNextBigDecimal())
{
payment = keyboard.nextBigDecimal();
payment = payment.setScale(2, RoundingMode.HALF_UP);
keyboard.nextLine();
if ((payment.compareTo(BigDecimal.ZERO) > 0) &&
payment.compareTo(getTuition()) <= 0)
{
moneyLeftOver = getTuition().subtract(payment);
setTuition(moneyLeftOver);
} 
else if (payment.compareTo(getTuition()) > 0) {
System.out.println("The value you have given is greater than your tuition");
} else if (payment.compareTo(BigDecimal.ZERO) < 0) {
System.out.println("You gave an negative number as a payment value. Please enter a positive value next time");
}
} 
else {
keyboard.nextLine();
System.out.println("You entered the wrong input so please input a number next time.");
}
} 
else if (answer.toLowerCase().equals("no")) {
break;
} 
else {
System.out.println("You gave the wrong input either enter yes or no");
}
}
};
c.payForCourses();
}
void chooseCourses(List<String> classes, int courseNumber)
{
switch (courseNumber)
{
case 1:
if (checkDups(classes, "CSE 325"))
classes.add("CSE 325");
break;
case 2:
if (checkDups(classes, "MTH 302"))
classes.add("MTH 302");
break;
case 3:
if (checkDups(classes, "PEV 106"))
classes.add("PEV 101");
break;
case 4:
if (checkDups(classes, "CSE 310"))
classes.add("CSE 310");
break;
case 5:
if (checkDups(classes, "CSE 307"))
classes.add("CSE 307");
break;
default:
System.out.println("You gave the wrong input");
break;
}
}
void addCourses()
{
List<String> classes = new LinkedList<>();
setCourses(classes);
String answer;
int nextCourse;
BigDecimal size;
BigDecimal cost;
System.out.println("Do you want to add any courses? yes or no");
answer = keyboard.nextLine();
while (!answer.toLowerCase().equals("no"))
{
if (answer.toLowerCase().equals("yes"))
{
System.out.println("Which classes would you like to add now? Please choose from the following selection. " + "Choose the number for the courses");
System.out.println("1. CSE 325");
System.out.println("2. MTH 302");
System.out.println("3. PEV 106");
System.out.println("4. CSE 310");
System.out.println("5. CSE 307");
if (keyboard.hasNextInt())
{
class");
nextCourse = keyboard.nextInt();
keyboard.nextLine();
chooseCourses(classes, nextCourse);
} 
else {
System.out.println("You put in the wrong input: Enter a number 1 - 5 for each keyboard.nextLine();
}
} 
else {
System.out.println("You put in the wrong input: Enter either yes or no next time");
}
System.out.println("Do you want to add any more courses?");
answer = keyboard.nextLine();
}
size = new BigDecimal(classes.size());
cost = new BigDecimal(600);
cost = cost.multiply(size);
setTuition(cost);
}
boolean checkDups(List<String> list, String word)
{
for (String temp : list)
{
if (word.equals(temp))
{
System.out.println("You are already enrolled in that course");
return false;
}
}
return true;
}
void displayInfo(Student[] studentList)
{
for (Student student : studentList)
{
System.out.println("\n ----------------------------------------------------- \n");
System.out.println("Student Name: " + student.getName());
System.out.println("Student ID: " + student.getId());
if (student.getCourses().size() > 0) {
System.out.println("Student's Current Courses: " + student.getCourses());
} 
else {
System.out.println("Student's Current Courses: The student isn't enrolled in any courses");
}
System.out.println("Student's Current Balance: Rs" + student.getTuition());
System.out.println("------------------------------------------------------");
}
System.out.println("!!!Thanks for Visiting!!!");
}
public static void main(String[] args) {
new Student();
try { //exception handling
int size;
Scanner keyboard = new Scanner(System.in);
System.out.println("Please enter the number of students you wish to add to the system");
size = keyboard.nextInt();
keyboard.nextLine();
Student[] students = new Student[size];
Student student;
String firstName = "";
String lastName = "";
for (int i = 0; i < size; i++)
{
student = new Student(firstName, lastName);
students[i] = student;
System.out.println("Please enter your first name for Student ");
firstName = keyboard.nextLine();
student.setFirstName(firstName);
System.out.println("Please enter your last name");
lastName = keyboard.nextLine();
student.setLastName(lastName);
student.makeID();
student.addCourses();
student.payForCourses();
if (i == size - 1)
student.displayInfo(students);
}
} catch (NegativeArraySizeException e) {
System.out.println("You can't use a negative number for size");
}
}
}
