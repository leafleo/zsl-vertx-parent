package org.zsl.web.modules.sys.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zsl.web.modules.sys.model.Region;

public interface IRegionDao extends JpaRepository<Region, Long> {

}
