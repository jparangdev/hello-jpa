package biz;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EmployeeServiceClient {

	public static void main(String[] args) {
		EntityManagerFactory emf
			= Persistence.createEntityManagerFactory("Chapter02");

		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();

		try  {
			Employee employee = new Employee();
			// employee.setId(2L);
			employee.setName("둘리");
			employee.setMailId("gurum");
			employee.setStartDate(new Date());
			employee.setTitle("과장");
			employee.setDeptName("총무부");
			employee.setSalary(2500.00);
			employee.setCommissionPct(12.50);

			// Employee employee = new Employee(1L, "둘리", "gurum", new Date(), "과장", "총무부"
			// , 2500.00, 12.50, null, null);

			tx.begin();
			System.out.println("등록 전 ID: "+employee.getId());
			em.persist(employee);

			for (int i = 0; i < 30; i++) {
				 Thread.sleep(1000);
				System.out.println("ZZZ....");
			}

			System.out.println("등록 후 ID: "+employee.getId());
			tx.commit();

			// Employee findEmployee = em.find(Employee.class, 1L);
			// System.out.println("검색한 회원정보");
			// System.out.println(findEmployee.toString());
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
	}
}
