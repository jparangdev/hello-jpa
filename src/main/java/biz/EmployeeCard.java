package biz;

import static javax.persistence.FetchType.*;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Getter;

@Entity
@Getter
public class EmployeeCard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cardId;

	private LocalDateTime expireDate;

	private String role;

	@OneToOne(fetch = LAZY)
	@JoinColumn(name = "Id")
	@Column(name = "EMP_CARD_ID")
	private Employee employee;
}
