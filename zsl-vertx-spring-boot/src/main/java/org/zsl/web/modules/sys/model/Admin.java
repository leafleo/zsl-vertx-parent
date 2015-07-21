/**
 * com.zsl.web.modules.sys.model.Admin
 */
package org.zsl.web.modules.sys.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
import org.hibernate.validator.constraints.Length;
import org.zsl.common.entity.DataEntity;

import com.google.common.collect.Sets;

/**
 * 项目名称：gyl_zsl 
 * 类名称：	Admin 
 * 类描述： 	管理员
 * 创建人：	liujunqing 
 * 创建时间：	2015年3月19日 下午6:11:31
 * @version 1.0
 */
@Entity
@Table(name = "z_gyl_admin")
@DynamicInsert
@DynamicUpdate

public class Admin extends DataEntity<Admin> {
	private static final long serialVersionUID = -1058088871536581563L;
	private String username;						/** 用户名*/
	private String name;							/** 姓名*/
	private String sex;								/** 性别：1：男 0：女*/
	private Duty duty;								/** 岗位 **/
	private Org org;								/** 组织结构 **/
	private String password;						/** 用户密码 **/
	private String theme;							/** 用户主题 **/
	private String status;							/** 1:启用 0：已停用 -1：已删除*/
	private String phone;							/** 手机*/
	private String email;							/** 邮件*/
	private String tel;								/** 电话号码*/
	private String isqq;							/** 是否QQ*/
	private String ext;								/** 分机号*/
	private Set<Menu> menus = Sets.newHashSet();	/** 用户菜单*/
	private Set<Role> roles = Sets.newHashSet();	/** 用户角色*/
	private Set<Org> chiefOrgs = Sets.newHashSet(); /** 所有负责部门*/
	private Set<Org> parentLeadOrgs = Sets.newHashSet(); /** 上级领导的部门*/

	@Column(name = "username")
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "sex")
	@Length(max=1)
	public String getSex() {
		return this.sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@Where(clause = "del_flag='" + DEL_FLAG_NORMAL + "'")
	@NotFound(action=NotFoundAction.IGNORE)
//	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE) 
	public Duty getDuty() {
		return this.duty;
	}

	public void setDuty(Duty duty) {
		this.duty = duty;
	}

	@Column(name = "password")
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name = "theme")
	public String getTheme() {
		return this.theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}
	@Column(name = "status")
	@Length(max=1)
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "phone")
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column(name = "email")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name = "tel")
	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	@Column(name = "isqq")
	public String getIsqq() {
		return this.isqq;
	}

	public void setIsqq(String isqq) {
		this.isqq = isqq;
	}
	
	@Column(name = "ext")
	public String getExt() {
		return this.ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "z_gyl_admin_menu")
	@Where(clause = "del_flag='" + DEL_FLAG_NORMAL + "'")
	@OrderBy("id")
	@NotFound(action=NotFoundAction.IGNORE)
	public Set<Menu> getMenus() {
		return this.menus;
	}

	public void setMenus(Set<Menu> menus) {
		this.menus = menus;
	}
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "z_gyl_admin_role")
	@Where(clause = "del_flag='" + DEL_FLAG_NORMAL + "'")
	@OrderBy("id")
	@NotFound(action=NotFoundAction.IGNORE)
	//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE) 
	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@Where(clause = "del_flag='" + DEL_FLAG_NORMAL + "'")
	@NotFound(action=NotFoundAction.IGNORE)
	//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE) 
	public Org getOrg() {
		return this.org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}

	@Transient
	public boolean isAdmin(){
		return isAdmin(this.id);
	}
	
	@Transient
	public static  boolean isAdmin(Long id){
		return id != null && id.equals(1L);
	}

	public Admin() {
		super();
	}

	@OneToMany(mappedBy = "chief")
	@NotFound(action=NotFoundAction.IGNORE)
	//
	public Set<Org> getChiefOrgs() {
		return this.chiefOrgs;
	}

	public void setChiefOrgs(Set<Org> chiefOrgs) {
		this.chiefOrgs = chiefOrgs;
	}

	@OneToMany(mappedBy = "parentLead")
	@NotFound(action=NotFoundAction.IGNORE)
	public Set<Org> getParentLeadOrgs() {
		return this.parentLeadOrgs;
	}

	public void setParentLeadOrgs(Set<Org> parentLeadOrgs) {
		this.parentLeadOrgs = parentLeadOrgs;
	}

	/**
	 * 是否查询交易员
	 */
	private Boolean isSearchTradeAdmin = Boolean.FALSE;

	@Transient
	public Boolean getIsSearchTradeAdmin() {
		return this.isSearchTradeAdmin;
	}

	public void setIsSearchTradeAdmin(Boolean isSearchTradeAdmin) {
		this.isSearchTradeAdmin = isSearchTradeAdmin;
	}

}
