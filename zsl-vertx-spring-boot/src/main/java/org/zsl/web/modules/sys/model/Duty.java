/**
 * 
 */
package org.zsl.web.modules.sys.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;
import org.zsl.common.entity.DataEntity;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * 项目名称：web_zsl    
 * 类名称：	Duty    
 * 类描述： 	岗位
 * 创建人：	liujunqing    
 * 创建时间：	2015年2月2日 下午6:11:31   
 * @version 1.0    
 *
 */
@Entity
@Table(name = "z_gyl_duty")
@DynamicInsert
@DynamicUpdate

public class Duty extends DataEntity<Duty>{
	
	public static final String TREE_PATH_SEPARATOR = ",";				/** 树路径分隔符 */
	private static final long serialVersionUID = -1058088871536581563L;
	private Set<Admin> admins = Sets.newHashSet();						/**所有人员*/
	private String name;												/**名称*/
	private Duty parent;												/** 父ID*/
	private Set<Duty> dutys = Sets.newHashSet();						/** 子ID*/
	private Integer sort;												/**排序*/
	private String is_super;											/**是否部门负责人 0：不是 1：是（可以查看和统计订单）*/
	private String treePath;											/**树路径*/
	private DutyTag dutyTag;											/** 职位标签*/

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(mappedBy = "duty", fetch=FetchType.LAZY)
	@OrderBy(value="id")
	@Where(clause = "del_flag='" + DEL_FLAG_NORMAL + "'")
	
	@NotFound(action=NotFoundAction.IGNORE)
	
	public Set<Admin> getAdmins() {
		return this.admins;
	}
	
	public void setAdmins(Set<Admin> admins) {
		this.admins = admins;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public Duty getParent() {
		return this.parent;
	}

	public void setParent(Duty parent) {
		this.parent = parent;
	}

	@OneToMany(mappedBy = "parent", fetch=FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
	@OrderBy(value="sort")
	
	public Set<Duty> getDutys() {
		return this.dutys;
	}

	public void setDutys(Set<Duty> dutys) {
		this.dutys = dutys;
	}
	
	@Column(name = "sort")
	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	@Column(name="tree_path")
	public String getTreePath() {
		return this.treePath;
	}

	public void setTreePath(String treePath) {
		this.treePath = treePath;
	}
	
	@Column(name = "is_super")
	@Length(max=1)
	public String getIs_super() {
		return this.is_super;
	}

	public void setIs_super(String is_super) {
		this.is_super = is_super;
	}
	
	
	@Transient
	public static List<Duty>  sortList(List<Duty> list, Duty parent){
		List<Duty> result = Lists.newArrayList();
		if (list != null) {
			for (Duty duty : list) {
				if ((duty.getParent() != null && duty.getParent().equals(parent)) || (duty.getParent() == null && parent == null)) {
					result.add(duty);
					result.addAll(sortList(list, duty));
				}
			}
		}
		return result;
	}
	
	/**
	 * 获取树路径
	 */
	@Transient
	public List<Long> getTrees() {
		List<Long> treePaths = new ArrayList<Long>();
		String[] ids = StringUtils.split(getTreePath(), TREE_PATH_SEPARATOR);
		if (ids != null) {
			for (String id : ids) {
				treePaths.add(Long.valueOf(id));
			}
		}
		return treePaths;
	}

	/**
	 * 是否顶级部门
	 */
	public static boolean isRoot(Long id) {
		if(id == null) return true;
		if(id.equals(1L)) return true;
		return false;
	}
	
	@Enumerated(EnumType.STRING)
	public DutyTag getDutyTag() {
		return this.dutyTag;
	}

	public void setDutyTag(DutyTag dutyTag) {
		this.dutyTag = dutyTag;
	}

	public Duty() {
	}
	
	
	/**
	 * 职位标签
	 */
	public enum DutyTag{
		PVC,
		PPPE,
		OTHER;
		public String label(){
			switch (this) {
			case PPPE:
				return "PPPE";
			case PVC:
				return "PVC";
			case OTHER:
				return "其它";
			}
			return "其它";
		}
	}
	
}
