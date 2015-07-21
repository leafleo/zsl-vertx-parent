/**
 * 
 */
package org.zsl.web.modules.sys.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
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
import org.zsl.common.entity.DataEntity;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * 项目名称：web_zsl    
 * 类名称：	Org    
 * 类描述：    组织架构
 * 创建人：	liujunqing    
 * 创建时间：	2015年2月2日 下午6:11:31   
 * @version 1.0    
 *
 */
@Entity
@Table(name = "z_gyl_org")
@DynamicInsert @DynamicUpdate

public class Org extends DataEntity<Org>{
	private static final long serialVersionUID = -1058088871536581563L;
	public static final String TREE_PATH_SEPARATOR = ",";/** 树路径分隔符 */				
	private Org parent;									 /** 父id */
	private Set<Admin> admins = Sets.newHashSet();		/** 所有人员 */
	private Set<Org> orgs = Sets.newHashSet();			/** 子组织架 */
	private String name;								/** 简称 */
	private String code; 								/** 组织架构编码 */
	private Region region;								/**归属区域*/
	private String type; 								/** 组织架构类型（1：公司；2：部门；）*/
	private Integer grade; 								/** 组织架构等级*/
	private String address; 							/**地址*/
	private String treePath;							/** 树结构*/
	private Admin chief;								/** 负责人*/
	private String chiefName;							/** 负责人名称*/
	private Admin parentLead;							/** 上级领导*/
	private String parentLeadName;						/** 上级领导*/
	private OrgTag orgTag = OrgTag.OTHER;				/** 部门标签*/ 
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "parent_id")
	@NotFound(action=NotFoundAction.IGNORE)  
	public Org getParent() {
		return this.parent;
	}

	public void setParent(Org parent) {
		this.parent = parent;
	}

	@OneToMany(mappedBy = "org", fetch=FetchType.LAZY)
	@Where(clause="del_flag='"+DEL_FLAG_NORMAL+"'")
	@OrderBy(value="id")
	@NotFound(action=NotFoundAction.IGNORE)
	
	public Set<Admin> getAdmins() {
		return this.admins;
	}

	public void setAdmins(Set<Admin> admins) {
		this.admins = admins;
	}

	@OneToMany(mappedBy = "parent", fetch=FetchType.LAZY, cascade = CascadeType.REMOVE)
	@Where(clause="del_flag='"+DEL_FLAG_NORMAL+"'")
	@OrderBy(value="grade")
	
	@NotFound(action=NotFoundAction.IGNORE)
	
	public Set<Org> getOrgs() {
		return this.orgs;
	}

	public void setOrgs(Set<Org> orgs) {
		this.orgs = orgs;
	}
	
	@Column(name = "tree_path")
	public String getTreePath() {
		return this.treePath;
	}

	public void setTreePath(String treePath) {
		this.treePath = treePath;
	}
	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "code")
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="region_id")
	@NotFound(action=NotFoundAction.IGNORE)  
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE) 
	public Region getRegion() {
		return this.region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	@Column( name = "type")
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "grade")
	public Integer getGrade() {
		return this.grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	@Column(name = "address")
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * 获取树路径
	 * 
	 * @return 树路径
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
	
	
	@Transient
	public static List<Org>  sortList(List<Org> list, Org parent){
		List<Org> result = Lists.newArrayList();
		if (list != null) {
			for (Org org : list) {
				if ((org.getParent() != null && org.getParent().equals(parent)) || (org.getParent() == null && parent == null)) {
					result.add(org);
					result.addAll(sortList(list, org));
				}
			}
		}
		return result;
	}

	@Transient
	public boolean isRoot(){
		return isRoot(this.id);
	}
	
	@Transient
	public static boolean isRoot(Long id){
		return id != null && id.equals(1L);
	}
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "chief",nullable=true)
	@Where(clause="del_flag='"+DEL_FLAG_NORMAL+"'")
	@NotFound(action=NotFoundAction.IGNORE)  
	public Admin getChief() {
		return this.chief;
	}

	public void setChief(Admin chief) {
		this.chief = chief;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "parentLead",nullable=true)
	@Where(clause="del_flag='"+DEL_FLAG_NORMAL+"'")
	@NotFound(action=NotFoundAction.IGNORE)  
	public Admin getParentLead() {
		return this.parentLead;
	}

	public void setParentLead(Admin parentLead) {
		this.parentLead = parentLead;
	}
	
	public String getChiefName() {
		return this.chiefName;
	}

	public void setChiefName(String chiefName) {
		this.chiefName = chiefName;
	}

	public String getParentLeadName() {
		return this.parentLeadName;
	}

	public void setParentLeadName(String parentLeadName) {
		this.parentLeadName = parentLeadName;
	}

	@Enumerated(EnumType.STRING)
	@Column(name="org_tag")
	public OrgTag getOrgTag() {
		return this.orgTag;
	}

	public void setOrgTag(OrgTag orgTag) {
		this.orgTag = orgTag;
	}

	public Org() {
	}

	/**
	 * 部门标签
	 */
	public enum OrgTag{
		PVC,
		PPPE,
		Trade,
		OTHER;
		public String label(){
			switch (this) {
			case PPPE:
				return "PP PE";
			case PVC:
				return "PVC";
			case OTHER:
				return "其它";
			case Trade:
				return "国际贸易";
			}
			return "其它";
		}
	}
}
