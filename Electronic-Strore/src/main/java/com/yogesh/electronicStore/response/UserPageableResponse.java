package com.yogesh.electronicStore.response;

import com.yogesh.electronicStore.payloads.UserDto;
import lombok.*;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPageableResponse<V> {

    private List<UserDto> content;

    private int pageNumber;

    private int pageSize;

    private long totalElement;

    private int totalPages;

    private boolean lastPage;


}
