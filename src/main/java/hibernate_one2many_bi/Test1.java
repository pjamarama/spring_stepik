package hibernate_one2many_bi;


import hibernate_one2many_uni.enitiy.Department;
import hibernate_one2many_uni.enitiy.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Test1 {
    public static void main(String[] args) {
        try (
                SessionFactory factory = new Configuration()
                        .configure("hib.conf.xml")
                        .addAnnotatedClass(Employee.class)
                        .addAnnotatedClass(Department.class)
                        .buildSessionFactory();
                Session session = factory.getCurrentSession();
        ) {
//            Department department = new Department("Sales", 800, 1500);
//            Employee employee1 = new Employee("Zaur", "Tregulov", 800);
//            Employee employee2 = new Employee("Elena", "Smirnova", 1500);
//            Employee employee3 = new Employee("Anton", "Sidorov", 1200);
//
//            department.addEmployeeToDepartment(employee1);
//            department.addEmployeeToDepartment(employee2);
//            department.addEmployeeToDepartment(employee3);
//
//            session.beginTransaction();
//            session.save(department);
//            session.getTransaction().commit();
//            System.out.println("Done!");
            session.beginTransaction();
            System.out.println("Get department");
            Department department = session.get(Department.class, 2);

            System.out.println("Подгружаем работников");
            department.getEmps().get(0); // подгружаем, обращаясь к первому элементу, но ничего с ним не делаем

            session.getTransaction().commit();

            System.out.println("Show department");
            System.out.println(department);
            System.out.println("Show department employees");
            System.out.println(department.getEmps());

            System.out.println("Done!");
        }
    }
}
