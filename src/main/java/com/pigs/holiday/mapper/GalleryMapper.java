package com.pigs.holiday.mapper;

import com.pigs.holiday.dto.GalleryDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GalleryMapper {
    List<GalleryDto.ListResDto> list(@Param("clubId") Long clubId, @Param("listReqDto") GalleryDto.ListReqDto listReqDto);
    List<GalleryDto.ListResDto> matchList(@Param("clubId") Long clubId, @Param("listReqDto") GalleryDto.ListReqDto listReqDto);
    List<GalleryDto.ListResDto> myClubList(@Param("clubId") Long clubId, @Param("listReqDto") GalleryDto.ListReqDto listReqDto);
}
