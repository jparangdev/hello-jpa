package biz;

import lombok.Builder;
import lombok.Data;

@Data
public class EmployeeSalaryData {

	private Long id;

	private Double salary;

	private Double commissionPct;

	@Builder
	public EmployeeSalaryData(Long id, Double salary, Double commissionPct) {
		this.id = id;
		this.salary = salary;
		this.commissionPct = commissionPct;
	}
}
