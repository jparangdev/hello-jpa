package biz;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "S_EMP_CARD")
public class EmployeeCard {
	@Id
	private Long cardId;

	private LocalDateTime expireDate;

	private String role;

	@MapsId
	@OneToOne
	@JoinColumn(name = "Id")
	private Employee employee;
}
