import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;

abstract class CampusEntity implements Serializable{

    protected String EntityID;
    protected String name;
    protected String loc;

    CampusEntity(){

    }

    CampusEntity(String e,String n,String l){

        EntityID=e;
        name=n;
        loc=l;
    }

    public String getEntityID() {
        return EntityID;
    }

    public void setEntityID(String e) {
        EntityID = e;
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        name = n;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String l) {
        loc = l;
    }

    public abstract double calOperationalCost();

    public String toString(){
        return "ID: " + EntityID + ", Name: " + name + ", Location: " + loc;
    }

}

class AcademicUnit extends CampusEntity{

    protected int no;
    protected int equipment;

    AcademicUnit(){

        super();
        no=0;
        equipment=0;
    }

    AcademicUnit(String e,String n,String l,int no,int equip){

        super(e,n,l);
        this.no=no;
        equipment=equip;
    }


    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public int getEquipment() {
        return equipment;
    }

    public void setEquipment(int equip) {
        equipment = equip;
    }

    public double calOperationalCost(){
        return no*equipment;

    }

    public String toString(){
        return super.toString() + ", Students: " + no + ", Equipment: " + equipment + ", Cost: " + calOperationalCost();
    }
}

class Facility extends CampusEntity{

    protected int maintenance;
    protected int usagefrequency;
    static int totalfacilityusage;

    Facility(){

        super();
        maintenance=0;
        usagefrequency=0;
        totalfacilityusage++;
    }

    Facility(String e,String n,String l,int m,int u){

        super(e,n,l);
        maintenance=m;
        usagefrequency=u;
        totalfacilityusage++;
    }


    public int getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(int m) {
        maintenance = m;
    }

    public int getUsagefrequency() {
        return usagefrequency;
    }

    public void setUsagefrequency(int u) {
        usagefrequency = u;
    }

    public double calOperationalCost(){
        return maintenance*usagefrequency;

    }

    public String toString(){
        return super.toString() +", Maintenance: " + maintenance +", Usage Frequency: " + usagefrequency +", Cost: " + calOperationalCost();
    }
}

class ServiceUnit extends CampusEntity{

    protected int servicehours;
    protected int staff;

    ServiceUnit(){

        super();
        servicehours=0;
        staff=0;
    }

    ServiceUnit(String e,String n,String l,int s,int Staff){

        super(e,n,l);
        servicehours=s;
        staff=Staff;
    }

    public int getServicehours() {
        return servicehours;
    }

    public void setServicehours(int s) {
        servicehours = s;
    }

    public int getStaff() {
        return staff;
    }

    public void setStaff(int s) {
        staff = s;
    }

    public double calOperationalCost(){
        return servicehours*staff;

    }

    public String toString(){
        return super.toString() +", Service Hours: " + servicehours +", Staff: " + staff +", Cost: " + calOperationalCost();
    }
}

class Department extends AcademicUnit implements Reportable{

    private ArrayList<Courses> courses=new ArrayList<>();

    Department(){
        super();
    }

    Department(String e,String n,String l,int no,int equip){
        super(e,n,l,no,equip);
    }

    public void addcourse(Courses co){
        courses.add(co);
    }

    public void generateReport(){
        System.out.println(" Generating Report for Department ! ");
    }

    public String toString(){
        return "Department : " + super.toString() +", Courses Count: " + courses.size();
    }
}


class ClassRoom extends AcademicUnit{

    private String roomStatus; 
    private String roomNumber;

    ClassRoom(){
        super();
    }

    ClassRoom(String e,String n,String l,int no,int equip,String status,String rnum){

        super(e,n,l,no,equip);
        roomStatus= status;
        roomNumber= rnum;
    }

    public String toString(){
        return " ClassRoom : " + super.toString();

    }

    public String checkLoad(String newAvailableRoom){

        if(roomStatus.equals("unavailable")){    
            roomNumber = newAvailableRoom;
            return "Room reassigned to: " + roomNumber + " Affected classes rescheduled." ;
        }else{
            return "Room " + roomNumber + " is available";
        }
    }
}

class Lab extends AcademicUnit{

    Lab(){
        super();
    }

    Lab(String e,String n,String l,int no,int equip){
        super(e,n,l,no,equip);
    }

    public String toString(){
        return " Lab  : " + super.toString();
    }
}

class Library extends Facility implements Reportable{

    protected ArrayList<Book> book=new ArrayList<>();

    Library(){
        super();

    }

    Library(String e,String n,String l,int m,int u){
        super(e,n,l,m,u);
    }

        public void addBook(Book b){
            book.add(b);
        }

    public void generateReport(){
        System.out.println(" Generating Report for Library ! ");
    }

    public String toString(){
        return "Library : " + super.toString() + ", Books: " + book.size();
    }
}

class Cafeteria extends Facility{

    Cafeteria(){
        super();
    }

    Cafeteria(String e,String n,String l,int m,int u){
        super(e,n,l,m,u);
    }

    public String toString(){
        return "Cafeteria  :  " + super.toString();
    }
}

class Hostel extends Facility{

    Hostel(){
        super();

    }

    Hostel(String e,String n,String l,int m,int u){
        super(e,n,l,m,u);
    }


    public String toString(){
        return "Hostel :  " + super.toString();
    }
}

class TransportService extends ServiceUnit implements Schedulable{
    private String route;

    TransportService(){
        super();
    }

    TransportService(String e,String n,String l,int s,int Staff,String r){
        super(e,n,l,s,Staff);
        route= r;
    }

    public void generateSchedule(){
        System.out.println(" Generating Schedule for Transport Service ! ");
    }

    public String toString(){
        return "Transport Service : " + super.toString();
    }

    public String peakHours(boolean peak){

        if(peak){
            if(route.equals("Route A")){
                route = "Route B";
            }else if(route.equals("Route C")){
                route = "Route D";
            }else if(route.equals("Route E")){
                route = "Route F";
            }else{
                return "No such route exists";
            }
                return "Route adjusted during peak hours: " + route;
        }else{
            return " Normal transport running" ;
        }
    }
}

class SecurityService extends ServiceUnit implements Notifable{

    SecurityService(){
        super();
    }

    SecurityService(String e,String n,String l,int s,int Staff){
        super(e,n,l,s,Staff);
    }

    public void sendNotification(){
        System.out.println(" Security Service is Sending Notification ! ");
    }

    public String toString(){
        return "Security Service : " + super.toString();
    }
}

class HealthCenter extends ServiceUnit implements Notifable{

    private boolean emergency;

    HealthCenter(){
        super();
        emergency = false;
    }

    HealthCenter(String e,String n,String l,int s,int Staff){
        super(e,n,l,s,Staff);
        emergency = false;
    }

    public void setEmergency(boolean e, SecurityService sec){
        emergency = e;
        if(emergency){
            handleEmergency(sec);
        }
    }

    public String handleEmergency(SecurityService sec){

        sendNotification();
        sec.sendNotification();
        return " Health Centre is handling Emergency !";
    }

    public void sendNotification(){
        System.out.println(" Health Center is Sending Emergency Notification!");
    }

    public String toString(){
        return "Health Center :  " + super.toString() + ", Emergency: " + emergency;
    }
}

class Courses implements Schedulable,Serializable{

    private String name;
    private String ID;
    private double credits;
    static int totalcourses;
    private ArrayList<Students>student=new ArrayList<>();
    private ArrayList<Assignment>assignments=new ArrayList<>();

    Courses(){

    }

    Courses(String n,String i,double c){
        name=n;
        ID=i;
        credits=c;
        totalcourses++;
    }

    public void addstudent(Students st){
        student.add(st);
    }

    public void addAssgn(Assignment as){
        assignments.add(as);
    }

    public void generateSchedule(){
        System.out.println(" Generating Schedule for Courses ! ");
    }

    public String getCourseID() {
        return ID;
    }

    public String toString(){
        return "Course: " + name +", ID: " + ID +", Credits: " + credits +", Students: " + student.size() +", Assignments: " + assignments.size();
    }

    public String updateSchedule(boolean conflict) {
        if(conflict){
            if (name.contains("DS")){
                name = name.replace("DS", "Data Structures");
            }else if(name.contains("Precal")){
                name = name.replace("Precal", "Precal 2");
            }else if(name.contains("Discrete Structures")){
                name = name.replace("Discrete Structures", "Digital Logic Design");
            }else if(name.contains("ICT")){
                name = name.replace("ICT", "Information Technology");
            }else{
                return "No matching course found for rescheduling: " + name;
            }
                return "Course rescheduled due to conflict: " + name;
        }else{
            return "Schedule is stable for: " + name;
        }
    }
}

class Students implements Serializable{

    private String name;
    private String regno;

    static int totalstudents;

    private Courses[] course = new Courses[10];
    private int coursecount;

    Students(){

    }

    Students(String n,String r){

        name=n;
        regno=r;

        totalstudents++;
    }

    public String getRegNo() {
        return regno;
    }

    public void addcourse(Courses co){

        if(coursecount < course.length){

            course[coursecount]=co;
            coursecount++;
        }
    }

    public String toString(){

        String data = "";

        for(int i=0;i<coursecount;i++){

            data=data + course[i].getCourseID() + " ";
        }
        return "Student Name: " + name +", RegNo: " + regno +", Courses Access: " + data;
    }
}

class Assignment implements Serializable{

    private String title;
    private String deadline;

    Assignment(){

    }

    Assignment(String t,String d){

        title=t;
        deadline=d;
    }

    public String toString(){
        return "Assignment: " + title +", Deadline: " + deadline;
    }
}

class Book implements Serializable{

    private String title;
    private String author;

    Book(){

    }

    Book(String t,String a){
        title=t;
        author=a;
    }

    public String toString(){
        return "Book Title: " + title +", Author: " + author;
    }
}

class CampusZone implements Serializable{
    private String campusname;
    private ArrayList<Facility> fac=new ArrayList<>();
    private ArrayList<ServiceUnit> ser=new ArrayList<>();

    CampusZone(){

    }
    CampusZone(String n){
        campusname=n;
    }

    public void addfacility(Facility f){
        fac.add(f);
    }

    public void addservice(ServiceUnit s){
        ser.add(s);
    }

    public String getCampusName() {
        return campusname;
    }
    public String toString(){
        return "Campus: " + campusname +", Facilities: " + fac.size() +", Services: " + ser.size();
    }
}

class Admin implements Serializable, Notifable{

    private String name;
    private String ID;

    private CampusRepository<Students> studentRepo;
    private CampusRepository<Courses> courseRepo;

    Admin(){

    }

    Admin(String n,String i,CampusRepository<Students> s,CampusRepository<Courses> c){
        name=n;
        ID=i;
        studentRepo=s;
        courseRepo=c;
    }

    public void sendNotification(){
        System.out.println(" Admin is Sending Notification ! ");
    }

    public String toString(){
        return "Admin Name: " + name +", ID: " + ID +", Total Students Access: "+ studentRepo.getRepo().size() +", Total Courses Access: "+ courseRepo.getRepo().size();
    }
}

class Teacher implements Serializable{
    private String name;
    private String teacherID;

    private Courses course;

    Teacher(){

    }

    Teacher(String n,String t,Courses c){

        name=n;
        teacherID=t;
        course=c;
    }

    public String toString(){
        return "Teacher Name: " + name +", Teacher ID: " + teacherID +", Managing Course: "+ course.getCourseID();
    }
}

interface Notifable{

    public abstract void sendNotification();

}

interface Schedulable{

    public abstract void generateSchedule();
}

interface Reportable{
     
    public abstract void generateReport();
}

class CampusRepository<T> implements Serializable{

    private ArrayList<T> repo = new ArrayList<>();

    public CampusRepository(){
    }

    public void setRepo(ArrayList<T> r){
        repo = r;
    }

    public ArrayList<T> getRepo(){
        return repo;
    }

    public void add(T obj){
        for(int i=0; i<repo.size(); i++){
            if(repo.get(i) instanceof CampusEntity && obj instanceof CampusEntity){

                CampusEntity e1 = (CampusEntity) repo.get(i);
                CampusEntity e2 = (CampusEntity) obj;

                if(e1.getEntityID().equalsIgnoreCase(e2.getEntityID())){
                    System.out.println("Duplicate detected!");
                    return;
                }
            }

            if(repo.get(i) instanceof Students && obj instanceof Students){

                Students s1 = (Students) repo.get(i);
                Students s2 = (Students) obj;

                if(s1.getRegNo().equalsIgnoreCase(s2.getRegNo())){
                    System.out.println("Duplicate detected!");
                    return;
                }
            }

            if(repo.get(i) instanceof Courses && obj instanceof Courses){

                Courses c1 = (Courses) repo.get(i);
                Courses c2 = (Courses) obj;

                if(c1.getCourseID().equalsIgnoreCase(c2.getCourseID())){
                    System.out.println("Duplicate detected!");
                    return;
                }
            }

            if(repo.get(i) instanceof CampusZone && obj instanceof CampusZone){

                CampusZone z1 = (CampusZone) repo.get(i);
                CampusZone z2 = (CampusZone) obj;

                if(z1.getCampusName().equalsIgnoreCase(z2.getCampusName())){
                    System.out.println("Duplicate detected!");
                    return;
                }
            }
        }

        repo.add(obj);
    }

    public void displayRepo(){

        for(int i=0; i<repo.size(); i++){
            System.out.println(repo.get(i).toString());
        }
    }
}

class FileHandler<T extends Serializable>{

    private ArrayList<T> list = new ArrayList<>();

    public void add(T obj){
        list.add(obj);
    }

    public ArrayList<T> getList(){
        return list;
    }

    public void setList(ArrayList<T> l){
        list = l;
    }

    public void load(String file){

        try{
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);

            list = (ArrayList<T>) ois.readObject();
            System.out.println(" Previous Data Loaded !");

            ois.close();
            fis.close();
        }
        catch(FileNotFoundException e){
            list = new ArrayList<>();
            System.out.println("New File Created!");
        }
        catch(IOException e){
            list = new ArrayList<>();
            System.out.println("File Error!");
        }
        catch(ClassNotFoundException e){
            list = new ArrayList<>();
            System.out.println("Class Error!");
        }
    }

    public void save(String file){
        try{
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(list);
            System.out.println(" Data Written Successfully! ");

            oos.close();
            fos.close();
        }
        catch(FileNotFoundException e){
            list = new ArrayList<>();
            System.out.println("New File Created!");
        }
        catch(IOException e){
            list = new ArrayList<>();
            System.out.println("File Error!");
        }
    }

    public void display(){
        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }
    }
}

class CampusManagementGUI {

    JFrame f;

    CampusRepository<Students> studentRepo;
    CampusRepository<Courses> courseRepo;
    CampusRepository<CampusEntity> entityRepo;
    FileHandler<Students> student = new FileHandler<>();
    FileHandler<Courses> course = new FileHandler<>();

    JLabel title;

    JTextField studentNameField;
    JTextField studentRegField;

    JTextField courseNameField;
    JTextField courseIDField;
    JTextField courseCreditField;

    JButton addStudentBtn;
    JButton showStudentBtn;
    JButton deleteStudentBtn;

    JButton addCourseBtn;
    JButton showCourseBtn;
    JButton deleteCourseBtn;

    JButton addAcademicBtn;
    JButton addFacilityBtn;
    JButton addServiceBtn;

    JButton showEntitiesBtn;
    JButton statsBtn;
    JButton reportBtn;

    JButton emergencyBtn;
    JButton rescheduleBtn;

    JButton saveBtn;
    JButton loadBtn;

    JButton clearBtn;

    JButton dep;
    JButton lib;
    JButton hostel;
    JButton health;

    JComboBox<String> roleBox;

    JTextArea area;

    JPanel leftPanel;
    JPanel rightPanel;
    JPanel mapPanel;

    CampusManagementGUI(CampusRepository<Students> sRepo,CampusRepository<Courses> cRepo,CampusRepository<CampusEntity> eRepo){

        studentRepo = sRepo;
        courseRepo = cRepo;
        entityRepo = eRepo;

        f = new JFrame("Smart University Campus System");
        f.setLayout(new BorderLayout());

        title = new JLabel("SMART UNIVERSITY CAMPUS MANAGEMENT SYSTEM",JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));

        f.add(title, BorderLayout.NORTH);

        leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(30,1,5,5));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        leftPanel.add(new JLabel("Select Role"));
        roleBox = new JComboBox<String>();
        roleBox.addItem("Admin");
        roleBox.addItem("Teacher");
        roleBox.addItem("Student");
        leftPanel.add(roleBox);

        leftPanel.add(new JLabel("Student Name"));
        studentNameField = new JTextField();
        leftPanel.add(studentNameField);

        leftPanel.add(new JLabel("Registration Number"));
        studentRegField = new JTextField();
        leftPanel.add(studentRegField);

        addStudentBtn =new JButton("Add Student");
        leftPanel.add(addStudentBtn);

        showStudentBtn =new JButton("Show Students");
        leftPanel.add(showStudentBtn);

        deleteStudentBtn =new JButton("Delete Student");
        leftPanel.add(deleteStudentBtn);

        leftPanel.add(new JLabel("Course Name"));
        courseNameField = new JTextField();
        leftPanel.add(courseNameField);

        leftPanel.add(new JLabel("Course ID"));
        courseIDField = new JTextField();
        leftPanel.add(courseIDField);

        leftPanel.add(new JLabel("Course Credits"));
        courseCreditField = new JTextField();
        leftPanel.add(courseCreditField);

        addCourseBtn =new JButton("Add Course");
        leftPanel.add(addCourseBtn);

        showCourseBtn =new JButton("Show Courses");
        leftPanel.add(showCourseBtn);

        deleteCourseBtn =new JButton("Delete Course");
        leftPanel.add(deleteCourseBtn);

        addAcademicBtn =new JButton("Add Academic Unit");
        leftPanel.add(addAcademicBtn);

        addFacilityBtn =new JButton("Add Facility");
        leftPanel.add(addFacilityBtn);

        addServiceBtn =new JButton("Add Service");
        leftPanel.add(addServiceBtn);

        showEntitiesBtn =new JButton("Show Entities");
        leftPanel.add(showEntitiesBtn);

        statsBtn =new JButton("Statistics");
        leftPanel.add(statsBtn);

        reportBtn =new JButton("Generate Report");
        leftPanel.add(reportBtn);

        emergencyBtn =new JButton("Medical Emergency");
        leftPanel.add(emergencyBtn);

        rescheduleBtn =new JButton("Reschedule Classes");
        leftPanel.add(rescheduleBtn);

        saveBtn =new JButton("Save Data");
        leftPanel.add(saveBtn);

        loadBtn =new JButton("Load Data");
        leftPanel.add(loadBtn);

        clearBtn =new JButton("Clear Area");
        leftPanel.add(clearBtn);

        f.add(leftPanel, BorderLayout.WEST);

        rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());

        area = new JTextArea();
        area.setFont(new Font("Arial", Font.PLAIN, 15));

        JScrollPane pane =new JScrollPane(area);

        rightPanel.add(pane, BorderLayout.CENTER);

        mapPanel = new JPanel();
        mapPanel.setBorder(BorderFactory.createTitledBorder("Campus Map Panel"));

        dep = new JButton("Department");
        dep.setBackground(Color.GREEN);

        lib = new JButton("Library");
        lib.setBackground(Color.ORANGE);

        hostel = new JButton("Hostel");
        hostel.setBackground(Color.RED);

        health = new JButton("Health Center");
        health.setBackground(Color.PINK);

        mapPanel.add(dep);
        mapPanel.add(lib);
        mapPanel.add(hostel);
        mapPanel.add(health);

        rightPanel.add(mapPanel, BorderLayout.SOUTH);
        f.add(rightPanel, BorderLayout.CENTER);

        roleBox.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String role = roleBox.getSelectedItem().toString();

                if(role.equals("Admin")){
                    Admin admin = new Admin("ALI","A101",studentRepo,courseRepo);
                    area.append(admin.toString() + "\n");

                    addStudentBtn.setEnabled(true);
                    deleteStudentBtn.setEnabled(true);

                    addCourseBtn.setEnabled(true);
                    deleteCourseBtn.setEnabled(true);

                    addFacilityBtn.setEnabled(true);
                    addServiceBtn.setEnabled(true);
                    addAcademicBtn.setEnabled(true);

                    reportBtn.setEnabled(true);

                }else if(role.equals("Teacher")){
                        Courses c = new Courses("OOP","CSC123",4.0);
                        Teacher t = new Teacher("SIR SAAD","T501",c);
                        area.append(t.toString() + "\n");

                    addStudentBtn.setEnabled(false);
                    deleteStudentBtn.setEnabled(false);

                    addCourseBtn.setEnabled(true);
                    deleteCourseBtn.setEnabled(false);

                    addFacilityBtn.setEnabled(false);
                    addServiceBtn.setEnabled(false);
                    addAcademicBtn.setEnabled(false);

                    reportBtn.setEnabled(true);
                
                }else if(role.equals("Student")){
                    Students s = new Students("ASMA","FA25-BCS-019");
                    area.append(s.toString());

                    addStudentBtn.setEnabled(false);
                    deleteStudentBtn.setEnabled(false);

                    addCourseBtn.setEnabled(false);
                    deleteCourseBtn.setEnabled(false);

                    addFacilityBtn.setEnabled(false);
                    addServiceBtn.setEnabled(false);
                    addAcademicBtn.setEnabled(false);

                    reportBtn.setEnabled(true);
                }   
                area.append("Role Switched To: " + role + "\n\n");
            }
        });

        addStudentBtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String name =studentNameField.getText();
                String r=studentRegField.getText();
                boolean found=false;

                for(int i=0;i<studentRepo.getRepo().size();i++){
                    Students ss=studentRepo.getRepo().get(i);
                    if(ss.getRegNo().equalsIgnoreCase(r)){
                        area.append("Search is successfull!");
                        area.append(ss.toString());
                        found=true;
                    }

                }
                if(found==false){
                    area.append("No student !");
                }

            }
        });




        showStudentBtn.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                area.append("----- STUDENTS -----\n");

                for(int i=0; i<studentRepo.getRepo().size(); i++){
                    area.append(studentRepo.getRepo().get(i).toString()+ "\n");
                }
                area.append("\n");
            }
        });

        deleteStudentBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String reg =studentRegField.getText();

                boolean found = false;

                for(int i=0; i<studentRepo.getRepo().size(); i++){
                    Students s =studentRepo.getRepo().get(i);

                    if(s.getRegNo().equalsIgnoreCase(reg)){
                        studentRepo.getRepo().remove(i);
                        area.append("Student Deleted!\n\n");
                        found = true;
                        break;
                    }
                }

                if(found == false){
                    area.append("Student Not Found!\n\n");
                }

                studentRegField.setText("");
                studentNameField.setText("");


            }
        });

        addCourseBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try{
                    String name =courseNameField.getText();
                    String id =courseIDField.getText();
                    double credit =Double.parseDouble(courseCreditField.getText());

                    if(name.equals("") || id.equals("") || courseCreditField.getText().equals("")){
                        JOptionPane.showMessageDialog(f,"Please Fill All Fields!");
                        return;
                    }
                    Courses c =new Courses(name,id,credit);

                    courseRepo.add(c);

                    area.append("Course Added Successfully!\n");
                    area.append(c.toString() + "\n\n");

                    courseNameField.setText("");
                    courseIDField.setText("");
                    courseCreditField.setText("");
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(f,"Invalid Input!");
                }
            }
        });

        showCourseBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                area.append("----- COURSES -----\n");

                for(int i=0; i<courseRepo.getRepo().size(); i++){
                    area.append(courseRepo.getRepo().get(i).toString() + "\n");
                }
                area.append("\n");
            }
        });

        deleteCourseBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String id =courseIDField.getText();
                boolean found = false;

                for(int i=0; i<courseRepo.getRepo().size(); i++){
                    Courses c =courseRepo.getRepo().get(i);

                    if(c.getCourseID().equalsIgnoreCase(id)){
                        courseRepo.getRepo().remove(i);

                        area.append("Course Deleted!\n\n");
                        found = true;
                        break;
                    }
                }

                if(found == false){
                    area.append("Course Not Found!\n\n");
                }
                courseIDField.setText("");
                courseNameField.setText("");
                courseCreditField.setText("");


            }
        });

        addAcademicBtn.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent e){
                AcademicUnit a = new Department("A" ,"Computer Science Department","AB2 BLOCK",120,20);
                entityRepo.add(a);
                
                area.append("Academic Unit Added Successfully!\n");
                area.append("Department/ClassRoom/Lab Updated\n\n");  
                area.append(a.toString() + "\n\n");
            }
        });

        addFacilityBtn.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e){
                Facility f = new Hostel("F" ,"Pearl Hostel","AB1 BLOCK",20,150);
                entityRepo.add(f);

                area.append("Facility Added Successfully!\n");
                area.append("Library/Cafeteria/Hostel Updated\n\n");
                area.append(f.toString() + "\n\n");
            }
        });

        addServiceBtn.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e){
                ServiceUnit s = new TransportService("S" ,"Campus Bus","Main Parking",10,8,"Route B");
                entityRepo.add(s);

                area.append("Service Unit Added Successfully!\n");
                area.append("Transport/Security/HealthCenter Updated\n\n");
                area.append(s.toString() + "\n\n");
            }
        });

        showEntitiesBtn.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e){
                area.append("----- CAMPUS ENTITIES -----\n");

                for(int i=0; i<entityRepo.getRepo().size(); i++){
                    area.append(entityRepo.getRepo().get(i).toString() + "\n");
                }
                area.append("\n");
            }
        });

        statsBtn.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e){

                area.append("----- SYSTEM STATISTICS -----\n");
                area.append("Total Students: " + Students.totalstudents + "\n");
                area.append("Total Courses: " + Courses.totalcourses + "\n");
                area.append("Total Facility Usage: " + Facility.totalfacilityusage + "\n\n");
            }
        });

        reportBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

                area.append("----- TIMETABLE & REPORTS -----\n");
                area.append("Selected Role: " + roleBox.getSelectedItem() + "\n");

                area.append("Class Schedules Generated Successfully\n");
                area.append("Course Timing Updated\n");
                area.append("Room Allocation Completed\n");
                area.append("Faculty Resources Checked\n");
                area.append("Resource Usage Summary Ready\n\n");
            }
        });

        emergencyBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

                HealthCenter hc = new HealthCenter("H186","MED CENTRE","AB4 BLOCK",24,20);
                SecurityService sec = new SecurityService("S125","SECURITY BLOCK","AB2 BLOCK",24,16);

                String msg = hc.handleEmergency(sec);

                area.append("===== MEDICAL EMERGENCY ALERT =====\n");
                area.append(msg + "\n");
                area.append("Situation Under Control\n\n");
            }
        });

        rescheduleBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

                ClassRoom cr = new ClassRoom("CL128","CL-8","AB2 BLOCK",102,18,"unavailable","G05");
                TransportService ts = new TransportService("T123","BUS-5","LC PARKING",2,12,"Route C");

                String roomMsg = cr.checkLoad("G08");
                String routeMsg = ts.peakHours(true);

                area.append("===== CLASS RESCHEDULING =====\n");
                area.append(roomMsg + "\n");

                area.append("===== ROUTE RESCHEDULING =====\n");
                area.append(routeMsg + "\n");
                area.append("Updated Timetable Generated\n\n");

            }
        });

        saveBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

                student.setList(studentRepo.getRepo());
                course.setList(courseRepo.getRepo());

                student.save("Student.dat");
                course.save("Course.dat");
                area.append("System Data Saved Successfully!\n\n");
            }
        });

        loadBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                student.load("Student.dat");
                course.load("Course.dat");

                studentRepo.setRepo(student.getList());
                courseRepo.setRepo(course.getList());
                area.append("Previous Data Loaded Successfully!\n\n");
            }
        });

        clearBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                area.setText("");
            }
        });

        dep.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                area.append("===== DEPARTMENT INFORMATION =====\n");
                area.append("Building: Computer Science Department\n");
                area.append("Location: AB2 Block\n");
                area.append("Status: ACTIVE\n");
                area.append("Classes Running Normally\n");
                area.append("Labs Available For Students\n\n");
            }
        });

        lib.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                area.append("===== LIBRARY INFORMATION =====\n");
                area.append("Library: Junaid Zaidi Library\n");
                area.append("Status: BUSY\n");
                area.append("Students Currently Studying\n");
                area.append("Reading Area Occupied\n\n");
            }
        });

        hostel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                area.append("===== HOSTEL INFORMATION =====\n");
                area.append("Hostel: Pearl Hostel\n");
                area.append("Status: CLOSED\n");
                area.append("Visitor Timing Ended\n");
                area.append("Security Monitoring Active\n\n");
            }
        });

        health.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                area.append("===== HEALTH CENTER =====\n");
                area.append("Medical Assistance Available\n");
                area.append("Emergency Unit Ready\n");
                area.append("Doctors On Duty\n");
                area.append("Status: AVAILABLE\n\n");
            }
        });

        f.setSize(1000,850);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}

public class OOP_PROJECT{
    public static void main(String[] args){

        CampusEntity a1=new AcademicUnit(" A121"," AcademicUnit"," AB2 BLOCK",101,12);
        CampusEntity f1=new Facility("B121","Facility"," AB3 BLOCK",10,150);
        CampusEntity s1=new ServiceUnit("C121","ServiceUnit"," AB4 BLOCK",24,120);

        Facility l1=new Library("L121","Junaid Zaidi Library","AB2 BLOCK",20,200);
        Facility c1=new Cafeteria("C123","OLD CAFE","AB4 BLOCK",10,140);
        Facility h1=new Hostel("H123","PEARL HOSTAL","AB1 BLOCK",20,200);

        AcademicUnit d1=new Department("D123","CS DEP","AB2 BLOCK",101,14);
        AcademicUnit c2=new ClassRoom("CL128","CL-8","AB2 BLOCK",102,18,"unavailable", "G05");
        AcademicUnit l2=new Lab("L123","CL-5","AB2 BLOCK",18,100);

        ServiceUnit t1=new TransportService("T123","BUS-5"," LC PARKING",2,12, "Route C");
        ServiceUnit s2=new SecurityService("S125","SECURITY BLOCK"," AB2 BLOCK",24,16);
        ServiceUnit h2=new HealthCenter("H186","MED CENTRE"," AB4 BLOCK",24,20);

        Courses course1=new Courses("OOP","CSC123",4.0);
        Courses course2=new Courses("DS","CSC124",4.0);

        Assignment ass1=new Assignment("ASSIGNMENT 01","25-04-2026");

        Students stu1=new Students("LAIBA","FA25-BCS-050");
        Students stu2=new Students("ASMA","FA25-BCS-019");

        Book b1=new Book("Charlie and the Chocolate Factory","William WordSmith");
        ((Library)l1).addBook(b1);
        ((Department)d1).addcourse(course1);

        course1.addAssgn(ass1);
        course1.addstudent(stu1);
        stu1.addcourse(course1);

        System.out.println("---- All Campus Entities ----");

        CampusEntity[] all={a1,f1,s1,l1,c1,h1,d1,c2,l2,t1,s2,h2};

        for(int i=0;i<all.length;i++){
            System.out.println(all[i].toString());
        }

        System.out.println(course1.toString());
        System.out.println(stu1.toString());
        System.out.println(ass1.toString());
        System.out.println(b1.toString());

        CampusZone cam1=new CampusZone("ISLAMABAD CAMPUS");

        cam1.addfacility(l1);
        cam1.addfacility(c1);
        cam1.addservice(t1);
        cam1.addservice(s2);

        CampusRepository<CampusEntity> campusRepo = new CampusRepository<>();
        CampusRepository<Students> studentRepo = new CampusRepository<>();
        CampusRepository<Courses> courseRepo = new CampusRepository<>();

        campusRepo.add(l1);
        campusRepo.add(h1);
        campusRepo.add(c1);

        studentRepo.add(stu1);
        studentRepo.add(stu2);
        courseRepo.add(course1);
        courseRepo.add(course2);

        System.out.println("\n---- Campus Zone ----");
        System.out.println(cam1.toString());

        System.out.println("---- CAMPUS ENTITY REPOSITORY ----");
        campusRepo.displayRepo();

        System.out.println("---- STUDENT REPOSITORY ----");
        studentRepo.displayRepo();

        System.out.println("---- COURSE REPOSITORY ----");
        courseRepo.displayRepo();


        Admin ad1 =new Admin("ALI","A101",studentRepo,courseRepo);
        Teacher t3 =new Teacher("SIR SAAD", "T501",course1);

        System.out.println("---- Notifications ----");

        Notifable[] notif = {(Notifable)s2, (Notifable)h2, (Notifable)ad1};

        for(int i=0;i<notif.length;i++){
            notif[i].sendNotification();
        }

        System.out.println("---- Scheduling ----");

        Schedulable[] sch = { (Schedulable)t1, course1 };

        for(int i=0;i<sch.length;i++){
            sch[i].generateSchedule();
        }

        System.out.println("---- Reports ----");

        Reportable[] rep = { (Reportable)d1, (Reportable)l1 };

        for(int i=0;i<rep.length;i++){
            rep[i].generateReport();
        }

        System.out.println("---- Loading Data From Files ----");

        FileHandler<CampusEntity> system = new FileHandler<>();
        system.load("Campus.dat");

        FileHandler<Admin> adminsystem = new FileHandler<>();
        adminsystem.load("Admin.dat");

        FileHandler<Teacher> teachersystem = new FileHandler<>();
        teachersystem.load("Teacher.dat");

        FileHandler<Students> studentsystem = new FileHandler<>();
        studentsystem.load("Student.dat");

        FileHandler<Courses> coursesystem = new FileHandler<>();
        coursesystem.load("Course.dat");


        System.out.println("---- Adding Data To Files ----");

        coursesystem.add(course1);
        coursesystem.add(course2);

        adminsystem.add(ad1);
        teachersystem.add(t3);
        studentsystem.add(stu1);
        studentsystem.add(stu2);
        system.add(h2);

        System.out.println("---- Saving Data Into Files ----");

        system.save("Campus.dat");
        adminsystem.save("Admin.dat");
        teachersystem.save("Teacher.dat");
        studentsystem.save("Student.dat");
        coursesystem.save("Course.dat");



        System.out.println("\n---- MEDICAL Emergency Test ----");
        HealthCenter hc = (HealthCenter) h2;
        SecurityService sec= (SecurityService)s2;
        hc.setEmergency(true, sec);
        hc.setEmergency(false, sec);
        System.out.println(hc.toString());

        System.out.println("\n---- Classroom Load Check ----");
        ClassRoom cr = (ClassRoom) c2;
        System.out.println(cr.checkLoad("G08"));

        System.out.println("\n---- Course Schedule Test ----");
        System.out.println(course1.updateSchedule(true));
        System.out.println(course1.updateSchedule(false));

        System.out.println("\n---- Transport Peak Hours Test ----");
        TransportService ts = (TransportService) t1;
        System.out.println(ts.peakHours(true));
        System.out.println(ts.peakHours(false));

        System.out.println("\n---- SYSTEM STATISTICS ----");
        System.out.println("Total Students: " + Students.totalstudents);
        System.out.println("Total Courses: " + Courses.totalcourses);
        System.out.println("Total Facility Usage: " + Facility.totalfacilityusage);

        for(int i=0;i<all.length;i++){
            system.add(all[i]);
        }


        System.out.println("\n---- ROLE BASED ACCESS TEST ----");

        System.out.println(ad1.toString());
        System.out.println(t3.toString());
        System.out.println(stu1.toString());
        System.out.println(stu2.toString());

        CampusRepository<CampusEntity> guiEntityRepo =new CampusRepository<>();
        CampusRepository<Students> guiStudentRepo =new CampusRepository<>();
        CampusRepository<Courses> guiCourseRepo =new CampusRepository<>();
    
        for(int i=0; i<all.length; i++){
            guiEntityRepo.add(all[i]);
        }

        guiStudentRepo.add(stu1);
        guiStudentRepo.add(stu2);

        guiCourseRepo.add(course1);
        guiCourseRepo.add(course2);

        new CampusManagementGUI(guiStudentRepo,guiCourseRepo,guiEntityRepo);

    }
}