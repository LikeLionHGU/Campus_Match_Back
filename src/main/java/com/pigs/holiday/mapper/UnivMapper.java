package com.pigs.holiday.mapper;

import com.pigs.holiday.dto.UnivDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UnivMapper {
    List<UnivDto.SearchResDto> search(@Param("searchReqDto") UnivDto.SearchReqDto searchReqDto);
}
