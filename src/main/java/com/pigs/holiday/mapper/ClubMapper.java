package com.pigs.holiday.mapper;

import com.pigs.holiday.dto.ClubDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClubMapper {
    List<ClubDto.ListResDto> list(@Param("listReqDto") ClubDto.ListReqDto listReqDto,@Param("requestClubId") Long requestClubId);
}
