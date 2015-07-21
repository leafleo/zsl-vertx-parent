package org.zsl.web.modules.sys.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;
import org.zsl.common.entity.DataEntity;
/**
 * 项目名称：	gyl_zsl    
 * 类名称：	Role    
 * 类描述：	角色   
 * 创建人：	liujunqing    
 * 创建时间：	2015年3月25日  
 * @version 1.0    
 *
 */
@Entity
@Table(name = "z_gyl_role")
@DynamicInsert
@DynamicUpdate

public class Role extends DataEntity<Role> {
	private static final long serialVersionUID = 8456427521930013462L;
	
	// 数据范围（1：所有数据；2：所在公司及以下数据；3：所在公司数据；4：所在部门及以下数据；5：所在部门数据；8：仅本人数据；9：按明细设置）	
	public enum DataScope{
		//不设置数据范畴
		DATA_NONE,
		/**所有数据 */
		DATA_SCOPE_ALL,
		/**所在公司及以下数据*/
		DATA_SCOPE_COMPANY_AND_CHILD,
		/** 所在公司数据 */
		DATA_SCOPE_COMPANY,
		/**仅本人数据 */
		DATA_SCOPE_SELF,
		/**按明细设置 */
		DATA_SCOPE_CUSTOM;
		public String label(){
			switch (this) {
			case DATA_NONE:
				return "不设置";
			case DATA_SCOPE_ALL:
				return "所有数据";
			case DATA_SCOPE_COMPANY_AND_CHILD:
				return "所在公司(部门)及以下数据";
			case DATA_SCOPE_COMPANY:
				return "所在公司(部门)数据";
			case DATA_SCOPE_SELF:
				return "仅本人数据";
			case DATA_SCOPE_CUSTOM:
				return "按明细设置";
			}
			return "";
		}
	}
	private String name;									//角色名
	private Set<Admin> admins = new HashSet<Admin>();		//获取管理员
	private Set<Menu> menus = new HashSet<Menu>();			//获得菜单
/*	private Long orgId;										//上级归属组织架构
	private Org org;										// 上级归属组织架构
*/	private Set<Org> orgs = new HashSet<Org>();				// 按明细设置数据范围(跨组织架构的权限)
	private DataScope dataScope; 							//数据范围
	private RoleTag roleTag;								//角色标签

	@Column(name = "name")
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
	@Where(clause="del_flag='"+DEL_FLAG_NORMAL+"'")
	@OrderBy("id")
	@NotFound(action = NotFoundAction.IGNORE)
	
	
	public Set<Admin> getAdmins() {
		return this.admins;
	}
	public void setAdmins(Set<Admin> admins) {
		this.admins = admins;
	}
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "z_gyl_role_menu")
	@Where(clause="del_flag='"+DEL_FLAG_NORMAL+"'")
	@OrderBy("id")
	@NotFound(action = NotFoundAction.IGNORE)
	
	
	public Set<Menu> getMenus() {
		return this.menus;
	}
	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}
	
	/*@Column(name="org_id")
	public Long getOrgId() {
		return this.orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="org_id",insertable=false,updatable=false)
	public Org getOrg() {
		return this.org;
	}
	public void setOrg(Org org) {
		this.org = org;
	}*/
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "z_gyl_org_role")
	@Where(clause="del_flag='"+DEL_FLAG_NORMAL+"'")
	@OrderBy("id")
	@NotFound(action = NotFoundAction.IGNORE)
	public Set<Org> getOrgs() {
		return this.orgs;
	}
	public void setOrgs(Set<Org> orgs) {
		this.orgs = orgs;
	}
	

	@Transient
	public static boolean isRoot(Long id) {
		if(id.equals(1L)) return true;
		return false;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "dataScope")
	public DataScope getDataScope() {
		return this.dataScope;
	}

	public void setDataScope(DataScope dataScope) {
		this.dataScope = dataScope;
	}
	
	public Role() {
		super();
	}
	
	@Enumerated(EnumType.STRING)
	public RoleTag getRoleTag() {
		return this.roleTag;
	}

	public void setRoleTag(RoleTag roleTag) {
		this.roleTag = roleTag;
	}



	/**
	 * 角色标签
	 */
	public enum RoleTag{
		CUSTOMER,//客服员
		TRANZ,//交易员
		OTHER,//其它
		MFPLASTIC_TRANZ,//改性交易员
		MERCHANDISER;//跟单员
		public String label(){
			switch (this) {
				case CUSTOMER:
					return "客服员";
				case TRANZ:
					return "交易员";
				case OTHER:
					return "其它";
				case MFPLASTIC_TRANZ:
					return "改性交易员";
				case MERCHANDISER:
					return "跟单员";
			}
			return "其它";
		}
	}
}
