package org.zsl.web.modules.sys.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zsl.web.modules.sys.model.Org;
/**
 *     
 * 项目名称：gyl_zsl    
 * 类名称：IOrgDao    
 * 类描述：    组织架构
 * 创建人：liujunqing    
 * 创建时间：2015年3月21日  
 * @version 1.0    
 *
 */
public interface IOrgDao extends JpaRepository<Org, Long> {
}
