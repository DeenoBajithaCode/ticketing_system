package org.w1959883.ticketing_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.w1959883.ticketing_system.models.ConfigurationEntity;
import org.w1959883.ticketing_system.models.ConfigurationStatus;

import java.util.List;

public interface ConfigRepository extends JpaRepository<ConfigurationEntity,Integer>
{
    List<ConfigurationEntity> findAllByStatus( ConfigurationStatus status );
}
