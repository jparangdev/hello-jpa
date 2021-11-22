package biz;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

public class CriteriaSearchClient {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter07");
		try {
			dataInsert(emf);
			dataSelect(emf);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			emf.close();
		}
	}

	private static void dataInsert(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Department department1 = new Department();
		department1.setName("개발부");
		em.persist(department1);

		Department department2 = new Department();
		department2.setName("영업부");
		em.persist(department2);

		for (int i = 1; i <= 3; i++) {
			Employee employee = new Employee();
			employee.setName("개발맨" + i);
			employee.setMailId("corona" + i);
			employee.setDept(department1);
			employee.setSalary(12700.00);
			employee.setStartDate(LocalDate.now());
			employee.setCommissionPct(10.00);
			em.persist(employee);
		}

		for (int i = 1; i <= 3; i++) {
			Employee employee = new Employee();
			employee.setName("영업맨" + i);
			employee.setMailId("virus" + i);
			employee.setDept(department2);
			employee.setSalary(23800.00);
			employee.setStartDate(LocalDate.now());
			employee.setCommissionPct(15.00);
			em.persist(employee);
		}

		Employee employee = new Employee();
		employee.setName("아르바이트");
		employee.setMailId("alba");
		employee.setSalary(10000.00);
		em.persist(employee);

		em.getTransaction().commit();
		em.close();

	}

	private static void dataSelect(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();

		String searchCondition = "name";
		String searchKeyword = "아르바이트";

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);

		Root<Employee> emp = criteriaQuery.from(Employee.class);

		Join<Employee, Department> dept = emp.join("dept", JoinType.LEFT);

		String name = "name";
		String mailId = "mailId";
		// criteriaQuery.select(emp);
		criteriaQuery.multiselect(emp.get(name), emp.get(mailId), dept.get(name));

		// if (searchCondition.equals(name)) {
		// 	criteriaQuery.where(builder.equal(emp.get(name), searchKeyword));
		// } else if (searchCondition.equals(mailId)) {
		// 	criteriaQuery.where(builder.equal(emp.get(mailId), searchKeyword));
		// }

		TypedQuery<Object[]> query = em.createQuery(criteriaQuery);
		List<Object[]> list = query.getResultList();

		System.out.println("검색결과");
		list.stream().forEach(o -> System.out.println(Arrays.toString(o)));

		em.close();
	}
}
