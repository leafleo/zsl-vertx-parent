/**
 * Copyright &copy; 2014-2015 <a href="http://www.zhaosuliao.com/">baisu</a> All rights reserved.
 *
 * 
 */
package org.zsl.common.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 数据Entity类
 * @author liujunqing
 * @version 2015-03-27
 */
@MappedSuperclass
public abstract class DataEntity<T> extends IdEntity<T> implements Serializable {

	private static final long serialVersionUID = 3650940890315347745L;
	
	protected String remarks;	// 备注
	
	protected Date createDate;// 创建日期
	
	protected Date updateDate;// 更新日期
	
	protected String delFlag; // 删除标记（0：正常；1：删除；）

	
	@PrePersist
	public void prePersist(){
		this.updateDate = new Date();
		this.createDate = this.updateDate;
	}
	
	@PreUpdate
	public void preUpdate(){
		this.updateDate = new Date();
	}
	
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	@Column(name = "del_flag")
	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
