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
import javax.persistence.ManyToMany;
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
 * 类名称：Menu    
 * 类描述：    菜单与权限
 * 创建人：liujunqing    
 * 创建时间：2015年2月2日  
 * @version 1.0    
 *
 */
@Entity
@Table(name = "z_gyl_menu")
@DynamicInsert 
@DynamicUpdate

public class Menu extends DataEntity<Menu> {
	private static final long serialVersionUID = 6956203451200570064L;
	
	public static final String TREE_PATH_SEPARATOR = ",";/** 树路径分隔符 */
	private String name;								/** 名称 */
	private String url;									/** 链接 */
	private Integer listOrder;							/** 权限标识 */
	private String permission;							/** 权限标识 */
	private String treePath;							/** 父子结构 */
	private Type type;									/** 类型 */
	private Menu parent;								/** 上级 */
	private Set<Menu> children = Sets.newHashSet();		/** 下级 */
	private Set<Role> roles = Sets.newHashSet();		/**角色*/
	private Set<Admin> admins = Sets.newHashSet();		/** 人员 */
	private Integer grade;								/** 级别*/
	private String icon;								/**图标 */
	
	
	
	@Column(name = "name")
	public  String getName() {
		return name;
	}

	public  void setName(String name) {
		this.name = name;
	}

	@Column(name = "url")
	public  String getUrl() {
		return url;
	}

	public  void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "list_order")
	public  Integer getListOrder() {
		return listOrder;
	}

	public  void setListOrder(Integer listOrder) {
		this.listOrder = listOrder;
	}

	@Column(name = "permission")
	public  String getPermission() {
		return permission;
	}

	public  void setPermission(String permission) {
		this.permission = permission;
	}
	
	@Column(name = "tree_path")
	public String getTreePath() {
		return this.treePath;
	}

	public void setTreePath(String treePath) {
		this.treePath = treePath;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="parent_id")
	@NotFound(action = NotFoundAction.IGNORE)
	public Menu getParent() {
		return this.parent;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}
	
	@OneToMany(mappedBy = "parent")
	@OrderBy("listOrder asc")
	
	@NotFound(action=NotFoundAction.IGNORE)
	
	public Set<Menu> getChildren() {
		return this.children;
	}

	public void setChildren(Set<Menu> children) {
		this.children = children;
	}
	
	@ManyToMany(mappedBy = "menus")
	@Where(clause="del_flag='"+DEL_FLAG_NORMAL+"'")
	@OrderBy("id")
	
	@NotFound(action=NotFoundAction.IGNORE)
	
	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	@Enumerated(EnumType.STRING)
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	@Column(name = "grade")
	public Integer getGrade() {
		return this.grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	
	@Column(name = "icon")
	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
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
	public String getTypeText(){
		switch (this.getType()) {
		case NAV:
			return "导航";
		case MENU:
			return "菜单";
		case OPT:
			return "操作";
		}
		return "其它";
	}
	
	@ManyToMany(mappedBy = "menus")
	@Where(clause="del_flag='"+DEL_FLAG_NORMAL+"'")
	@OrderBy("id") 
	
	
	public Set<Admin> getAdmins() {
		return this.admins;
	}

	public void setAdmins(Set<Admin> admins) {
		this.admins = admins;
	}


	/**
	 * 菜单类型
	 */
	public enum Type{
		NAV,//导航
		MENU,//菜单
		OPT;//操作
		
		public String label(){
			switch (this) {
			case NAV:
				return "导航";
			case MENU:
				return "菜单";
			case OPT:
				return "操作";
			}
			return "其它";
		}
	}
	
	
	@Transient
	public static List<Menu>  sortList(List<Menu> list, Menu parent){
		List<Menu> result = Lists.newArrayList();
		if (list != null) {
			for (Menu menu : list) {
				if ((menu.getParent() != null && menu.getParent().equals(parent)) || (menu.getParent() == null && parent == null)) {
					result.add(menu);
					result.addAll(sortList(list, menu));
				}
			}
		}
		return result;
	}
	
	
	/**
	 * 是否顶级部门
	 */
	public static boolean isRoot(Long id) {
		if(id == null) return true;
		if(id.equals(1L)) return true;
		return false;
	}
	

	public Menu() {
	}
	
}
