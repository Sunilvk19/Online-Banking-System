package com.onlinebank.OnlineBankingSystem.utility;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseStructure <T>{
    private int status;
    private String message;
    private T data;
}
