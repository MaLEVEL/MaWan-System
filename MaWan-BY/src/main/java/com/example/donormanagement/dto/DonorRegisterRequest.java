package com.example.donormanagement.dto;

import com.example.donormanagement.entity.Donor;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Donor self-registration request DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonorRegisterRequest {

    // Account fields
    @NotBlank(message = "用户名不能为空")
    @Size(min = 3, max = 50, message = "用户名长度3-50个字符")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 100, message = "密码长度至少6位")
    private String password;

    // Donor fields
    @NotBlank(message = "姓不能为空")
    @Size(max = 50)
    private String firstName;

    @NotBlank(message = "名不能为空")
    @Size(max = 50)
    private String lastName;

    @NotNull(message = "出生日期不能为空")
    @Past(message = "出生日期必须是过去的日期")
    private LocalDate dateOfBirth;

    @NotNull(message = "性别不能为空")
    private Donor.Gender gender;

    @NotBlank(message = "证件号不能为空")
    @Size(min = 8, max = 14, message = "证件号8-14位数字")
    @Pattern(regexp = "^\\d{8,14}$", message = "证件号必须为8-14位数字")
    private String passportNumber;

    @Pattern(regexp = "^[0-9+\\-\\s()]*$", message = "手机号格式不正确")
    private String phone;

    @Email(message = "邮箱格式不正确")
    private String email;

    private String address;

    @NotNull(message = "血型不能为空")
    private Donor.BloodType bloodType;

    private String hlaType;

    @AssertTrue(message = "捐献者必须年满18周岁")
    public boolean isAdult() {
        if (dateOfBirth == null) return false;
        return !dateOfBirth.plusYears(18).isAfter(LocalDate.now());
    }
}
