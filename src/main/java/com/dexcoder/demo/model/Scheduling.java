package com.dexcoder.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "scheduling")
public class Scheduling {

	public static final String JOB_PARAM_KEY = "jobParam";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long schedulingId;
	private String jobName;
	private String aliasName;
	private String jobGroup;
	private String jobTrigger;
	private String status;
	private String cronExpression;
	private Boolean isSync;
	private String description;
	private Date gmtCreate;
	private Date gmtModify;
	
}
