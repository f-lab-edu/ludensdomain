package com.ludensdomain.dto;

import com.ludensdomain.domain.AuthLevel;
import lombok.Builder;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Value
@Builder
public class UserDto {

    @NotNull @NotEmpty(message = "아이디를 입력하세요.")
    long id;

    @NotEmpty(message = "이름을 입력하세요.")
    String name;

    @NotEmpty(message = "비밀번호를 입력하세요.")
    @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})",
            message = "비밀 번호는 6~20개 숫자와 특수 문자가 포함된 영문 대소문자로 입력해 주세요")
    String password;

    @NotEmpty
    @Email(message = "이메일 형식으로 입력해주세요.")
    String email;

    @NotEmpty
    Date dateOfBirth;

    @NotEmpty
    @Length(max = 11, message = "전화번호는 11자리 이하로 입력해주세요.")
    String phoneNo;

    @NotEmpty
    AuthLevel role;
}
