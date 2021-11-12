package biz;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ManyToOneOneWayClient {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");

		try {
			dataInsert(emf);
			dataSelect(emf);
			dataUpdate(emf);
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
		Employee employee = em.find(Employee.class, 1L);
		// System.out.println(employee.getName() + "의 부서 :" + employee.getDepartment().getName());
		System.out.println(employee.getName() + " 검색완료");
		System.out.println(employee.getDepartment().getName() + "부서");
	}

	private static void dataInsert(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Department department = new Department();
		department.setName("개발부");
		em.persist(department);

		Employee employee1 = new Employee();
		employee1.setName("둘리");
		employee1.setDepartment(department);
		em.persist(employee1);

		Employee employee2 = new Employee();
		employee2.setName("도우너");
		employee2.setDepartment(department);
		em.persist(employee2);

		em.getTransaction().commit();
		em.close();
	}
}
