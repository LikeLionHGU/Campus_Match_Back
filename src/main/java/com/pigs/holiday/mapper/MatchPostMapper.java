package com.pigs.holiday.mapper;

import com.pigs.holiday.dto.MatchPostDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MatchPostMapper {
    List<MatchPostDto.ListResDto> list(@Param("listReqDto") MatchPostDto.ListReqDto listReqDto);
    List<MatchPostDto.ListResDto> mineList(@Param("listReqDto") MatchPostDto.ListReqDto listReqDto, @Param("requestClubId") Long requestClubId);
    List<MatchPostDto.ListResDto> otherList(@Param("listReqDto") MatchPostDto.ListReqDto listReqDto, @Param("requestClubId") Long requestClubId);
}
