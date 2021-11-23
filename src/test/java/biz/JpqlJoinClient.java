package biz;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class JpqlJoinClient {

	public static void main(String[] args) {
		EntityManagerFactory emf
			= Persistence.createEntityManagerFactory("Chapter06");

		try {
			dataInsert(emf);
			dataSelect(emf);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			emf.close();
		}
	}

	private static void dataSelect(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();

		// String jpql = "SELECT e FROM Employee e";
		// TypedQuery<Employee> query = em.createQuery(jpql, Employee.class);
		//
		// List<Employee> list = query.getResultList();
		// System.out.println("검색된 직업목록");
		// for (Employee employee : list) {
		// 	System.out.println(employee);
		// }

		String jpql = "SELECT e, e.dept FROM Employee e";
		TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);

		List<Object[]> list = query.getResultList();
		System.out.println("검색된 직업목록");
		for (Object[] result : list) {
			Employee employee = (Employee)result[0];
			Department department = (Department)result[1];
			System.out.println(employee.getName() + "부서" + department.getName());
		}

		em.close();
	}

	private static void dataInsert(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Department department1 = new Department();
		department1.setName("개발부");

		for (int i = 1; i <= 5; i++) {
			Employee employee = Employee.builder()
				.name("개발" + i)
				.mailId("DEV" + i)
				.salary(12700.00 * i)
				.startDate(LocalDate.now())
				.title("사원")
				.commissionPct(15.00)
				.build();
			employee.setDept(department1);
		}
		em.persist(department1);

		Department department2 = new Department();
		department2.setName("영업부");

		for (int i = 1; i <= 5; i++) {
			Employee employee = Employee.builder()
				.name("영업" + i)
				.mailId("SALE" + i)
				.salary(12700.00 * i)
				.startDate(LocalDate.now())
				.title("사원")
				.commissionPct(15.00)
				.build();
			employee.setDept(department2);
		}
		em.persist(department2);

		Department department3 = new Department();
		department3.setName("인사부");
		// for (int i = 1; i <= 5; i++) {
		// 	Employee employee = Employee.builder()
		// 		.name("인사" + i)
		// 		.mailId("HRD" + i)
		// 		.salary(12700.00 * i)
		// 		.startDate(LocalDate.now())
		// 		.title("사원")
		// 		.commissionPct(15.00)
		// 		.dept(department3)
		// 		.build();
		// }
		em.persist(department3);

		em.getTransaction().commit();
		em.close();
	}
}
