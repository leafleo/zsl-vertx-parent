package org.zsl.web.modules.sys.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zsl.web.modules.sys.model.Role;

public interface IRoleDao extends JpaRepository<Role, Long> {
}
