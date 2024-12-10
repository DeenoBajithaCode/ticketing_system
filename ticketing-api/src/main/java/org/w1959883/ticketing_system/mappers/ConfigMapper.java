package org.w1959883.ticketing_system.mappers;

import com.w1959883.models.Configuration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.w1959883.ticketing_system.models.Config;

@Mapper
public interface ConfigMapper
{
    ConfigMapper INSTANCE = Mappers.getMapper( ConfigMapper.class );

    @Mapping( target = "configurationId", ignore = true )
    Config toConfig( Configuration configuration );

    Configuration toConfiguration( Config config );
}
