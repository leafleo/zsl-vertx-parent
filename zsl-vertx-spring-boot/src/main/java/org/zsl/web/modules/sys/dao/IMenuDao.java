package org.zsl.web.modules.sys.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.zsl.web.modules.sys.model.Menu;

/**
 * 项目名称：web_zsl    
 * 类名称：IAdminDao    
 * 类描述：   管理员类
 * 创建人：liujunqing    
 * 创建时间：2015年2月3日  
 * @version 1.0    
 *
 */
public interface IMenuDao extends JpaRepository<Menu, Integer>	{
	
}
