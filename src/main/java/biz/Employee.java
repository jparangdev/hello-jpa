package biz;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table(name = "S_EMP")
@ToString(exclude = "employeeCard")
@NoArgsConstructor
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 25, nullable = false)
	private String name;

	private String mailId;

	private LocalDate startDate;

	private String title;

	private String deptName;

	private Double salary;

	private Double commissionPct;

	@Builder
	public Employee(Long id, String name, String mailId, LocalDate startDate, String title, String deptName,
		Double salary, Double commissionPct) {
		this.id = id;
		this.name = name;
		this.mailId = mailId;
		this.startDate = startDate;
		this.title = title;
		this.deptName = deptName;
		this.salary = salary;
		this.commissionPct = commissionPct;
	}
}
