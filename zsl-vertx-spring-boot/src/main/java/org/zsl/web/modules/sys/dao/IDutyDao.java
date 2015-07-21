package org.zsl.web.modules.sys.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zsl.web.modules.sys.model.Duty;
/**
 *     
 * 项目名称：gyl_zsl    
 * 类名称：IDutyDao    
 * 类描述：    岗位
 * 创建人：liujunqing    
 * 创建时间：2015年3月21日  
 * @version 1.0    
 *
 */
public interface IDutyDao  extends JpaRepository<Duty, Integer> {


}
