package org.zsl.web.modules.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zsl.web.modules.sys.dao.IAdminDao;
import org.zsl.web.modules.sys.model.Admin;

@Service
public class AdminService {
	@Autowired
	private IAdminDao adminDao;
	
	public List<Admin> findAll(){
		return adminDao.findAll();
	}

}
