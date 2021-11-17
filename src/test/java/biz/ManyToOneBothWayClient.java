package biz;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ManyToOneBothWayClient {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");

		try {
			dataInsert(emf);
			dataSelect(emf);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			emf.close();
		}
	}

	private static void dataUpdate(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Department department = new Department();
		department.setName("영업부");
		em.persist(department);

		Employee employee = em.find(Employee.class, 1L);
		employee.setDepartment(department);

		em.getTransaction().commit();

	}

	private static void dataSelect(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		Department department = em.find(Department.class, 1L);

		System.out.println("검색된 부서 : " + department.getName());
		System.out.println("직원 명단");
		for (Employee e : department.getEmployeeList()) {
			System.out.println("고객명 : " + e.getName() + " 부서" + e.getDepartment().getName());
		}
	}

	private static void dataInsert(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Department department = new Department();
		department.setName("개발부");
		// em.persist(department);

		Employee employee1 = new Employee();
		employee1.setName("둘리");
		employee1.setDepartment(department);
		em.persist(employee1);

		// Employee employee2 = new Employee();
		// employee2.setName("도우너");
		// employee2.setDepartment(department);
		// em.persist(employee2);

		System.out.println(department.getName() + " 사이즈:" + department.getEmployeeList().size());

		em.getTransaction().commit();
		em.close();
	}

	private static void dataDelete(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Employee employee1 = em.find(Employee.class, 1L);
		employee1.setDepartment(null);
		Employee employee2 = em.find(Employee.class, 2L);
		employee2.setDepartment(null);

		Department department = em.find(Department.class, 1L);
		em.remove(department);
		em.getTransaction().commit();
	}
}
