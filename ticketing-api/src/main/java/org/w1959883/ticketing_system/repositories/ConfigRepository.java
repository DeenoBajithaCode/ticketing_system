package org.w1959883.ticketing_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.w1959883.ticketing_system.models.Config;
import org.w1959883.ticketing_system.models.ConfigurationStatus;

import java.util.List;

public interface ConfigRepository extends JpaRepository<Config,Integer>
{
    List<Config> findAllByStatus( ConfigurationStatus status );
}
