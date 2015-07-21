package org.zsl.web.modules.sys.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.zsl.common.entity.DataEntity;

/**
 * 项目名称：	gyl_zsl    
 * 类名称：	Region    
 * 类描述：	地区    
 * 创建人：	liujunqing    
 * 创建时间：2015年3月25日  
 * @version 1.0    
 *
 */
@Entity
@Table(name = "z_region")

public class Region extends DataEntity<Region>{
	private static final long serialVersionUID = -2158109459123036967L;
	private static final String TREE_PATH_SEPARATOR = ",";	/** 树路径分隔符 */
	private String name;									/** 名称 */	
	private String fullName;								/** 全称 */
	private String shortName;								/** 简称 */
	private String regionCode;								/** 区号*/
	private Integer areaId;									/** 区域id*/
	private String treePath;								/** 树路径 */
	private Region parent;									/** 上级地区 */
	private Integer level;									/** 级别 */
	private Set<Region> children = new HashSet<Region>();	/** 下级地区 */

	
	@NotEmpty
	@Length(max = 100)
	@Column(nullable = false, length = 100)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable = false, length = 500,name="full_name")
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Column(nullable = false, updatable = false,name="tree_path")
	public String getTreePath() {
		return treePath;
	}

	public void setTreePath(String treePath) {
		this.treePath = treePath;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="pid",updatable=false,insertable=false) 
	@NotFound(action=NotFoundAction.IGNORE)
	public Region getParent() {
		return parent;
	}

	public void setParent(Region parent) {
		this.parent = parent;
	}

	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@OrderBy("id asc")
	@NotFound(action=NotFoundAction.IGNORE)
	
	public Set<Region> getChildren() {
		return children;
	}
	
	public void setChildren(Set<Region> children) {
		this.children = children;
	}

	@Column(length = 500,name="short_name")
	public String getShortName() {
		return this.shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	@Column(name="region_code")
	public String getRegionCode() {
		return this.regionCode;
	}
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	@Column(name="area_id")
	public Integer getAreaId() {
		return this.areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	
	@Column(name = "level")
	public Integer getLevel() {
		return this.level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	
	/**
	 * 持久化前处理
	 */
	@PrePersist
	public void prePersist() {
		Region parent = getParent();
		if (parent != null) {
			setTreePath(parent.getTreePath() + parent.getId() + TREE_PATH_SEPARATOR);
		} else {
			setTreePath(TREE_PATH_SEPARATOR);
		}
	}

	/**
	 * 更新前处理
	 */
	@PreUpdate
	public void preUpdate() {
		/*Region parent = getParent();
		if (parent != null) {
			setFullName(parent.getFullName() + getName());
		} else {
			setFullName(getName());
		}*/
	}

	/**
	 * 重写toString方法
	 * 
	 * @return 全称
	 */
	@Override
	public String toString() {
		return getFullName();
	}
	

	public Region() {
	}
	

}