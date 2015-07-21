package org.zsl.web.modules.sys.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 日志Entity
 *     
 * 项目名称：gyl_zsl    
 * 类名称：Log    
 * 类描述：    
 * 创建人：liujunqing    
 * 创建时间：2015年3月30日  
 * @version 1.0    
 *
 */
@Entity
@Table(name = "z_gyl_log")
@DynamicInsert @DynamicUpdate

public class Log {

	private static final long serialVersionUID = 1L;
	private Long id;									// 日志编号
	private String type; 								// 日志类型（1：接入日志；2：错误日志）
	private Admin createBy;								// 创建者
	private Date createDate;							// 日志创建时间
	private String remoteAddr; 							// 操作用户的IP地址
	private String requestUri; 							// 操作的URI
	private String method; 								// 操作的方式
	private String params; 								// 操作提交的数据
	private String userAgent;							// 操作用户代理信息
	private String exception; 							// 异常信息
	
	public static final String TYPE_ACCESS = "1";
	public static final String TYPE_EXCEPTION = "2";
	
	public Log(){
		super();
	}
	
	public Log(Long id){
		this();
		this.id = id;
	}

	@PrePersist
	public void prePersist(){
		createDate = new Date();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	public Admin getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Admin createBy) {
		this.createBy = createBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public String getRemoteAddr() {
		return remoteAddr;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getRequestUri() {
		return requestUri;
	}

	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	@Lob
	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}
	
	@Lob
	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}
}