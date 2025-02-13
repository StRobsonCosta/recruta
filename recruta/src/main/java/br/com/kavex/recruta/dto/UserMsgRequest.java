package br.com.kavex.recruta.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMsgRequest {
    public String userId;
    public String message;
    public String phone;
}
