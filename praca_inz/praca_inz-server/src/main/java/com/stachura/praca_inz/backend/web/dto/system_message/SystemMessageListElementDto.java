package com.stachura.praca_inz.backend.web.dto.system_message;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class SystemMessageListElementDto {
    private Long id;
    private String title;
    private String message;
    private String messageDate;

}
