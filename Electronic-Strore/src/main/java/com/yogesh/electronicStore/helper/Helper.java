package com.yogesh.electronicStore.helper;

import com.yogesh.electronicStore.payloads.UserDto;
import com.yogesh.electronicStore.response.UserPageableResponse;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class Helper {

    public static <U,V> UserPageableResponse<V> getPageableResponse(Page<U> page, Class<V> type) {

        List<U> entry = page.getContent();

        List<V> dtoList = entry.stream().map(object -> new ModelMapper().map(object, type)).collect(Collectors.toList());

        UserPageableResponse<V> response = new UserPageableResponse<>();

        response.setContent((List<UserDto>) dtoList);

        response.setPageNumber(page.getNumber());

        response.setPageSize(page.getSize());

        response.setTotalElement(page.getTotalElements());

        response.setTotalPages(page.getTotalPages());

        response.setLastPage(page.isLast());

        return response;
    }

}
