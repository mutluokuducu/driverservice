package com.hypothetical.mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

import com.hypothetical.dto.DriverDto;
import com.hypothetical.repository.entity.Driver;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface HypotheticalMapper {

  DriverDto toDriverResponse(final Driver driver);
}

