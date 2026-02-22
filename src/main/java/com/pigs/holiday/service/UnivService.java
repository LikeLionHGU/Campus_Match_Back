package com.pigs.holiday.service;


import com.pigs.holiday.dto.UnivDto;
import com.pigs.holiday.mapper.UnivMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UnivService {

    private final UnivMapper univMapper;

    // Search
    @Transactional(readOnly = true)
    public List<UnivDto.SearchResDto> search(UnivDto.SearchReqDto searchReqDto) {
        return univMapper.search(searchReqDto);
    }

}
