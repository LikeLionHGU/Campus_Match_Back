package com.pigs.holiday.mapper;

import com.pigs.holiday.dto.MatchHistoryDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MatchHistoryMapper {
    List<MatchHistoryDto.ListResDto> list(@Param("clubId") Long clubId, @Param("listReqDto") MatchHistoryDto.ListReqDto listReqDto);
    List<MatchHistoryDto.ListResDto> createList(@Param("clubId") Long clubId, @Param("listReqDto") MatchHistoryDto.ListReqDto listReqDto);
    List<MatchHistoryDto.ListResDto> addList(@Param("clubId") Long clubId, @Param("listReqDto") MatchHistoryDto.ListReqDto listReqDto);
}
