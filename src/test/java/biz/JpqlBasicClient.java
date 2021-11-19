package biz;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class JpqlBasicClient {

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
		// List<Employee> employeeList = new ArrayList<>();
		// employeeList = query.getResultList();
		// System.out.println("검색된 직업목록");
		// employeeList.forEach(System.out::println);

		String jpql = "SELECT id, name, deptName, salary FROM Employee";
		Query query = em.createQuery(jpql);
		List<Object[]> list = query.getResultList();

		System.out.println("검색된 직업목록");
		for (Object[] objects : list) {
			System.out.println(Arrays.toString(objects));
		}

		em.close();
	}

	private static void dataInsert(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		for (int i = 0; i < 15; i++) {
			Employee employee = Employee.builder()
				.name("직원" + i)
				.mailId("anti-corona" + i)
				.deptName("개발부")
				.salary(12700.00 * i)
				.startDate(LocalDate.now())
				.title("사원")
				.commissionPct(15.00)
				.build();
			em.persist(employee);
		}

		em.getTransaction().commit();
		em.close();
	}
}
