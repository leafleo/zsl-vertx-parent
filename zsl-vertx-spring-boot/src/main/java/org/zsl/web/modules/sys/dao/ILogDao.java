package org.zsl.web.modules.sys.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zsl.web.modules.sys.model.Log;

public interface ILogDao extends JpaRepository<Log, Integer> {

}
