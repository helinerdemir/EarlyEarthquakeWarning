package com.example.dto.user;

import com.example.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;

    @NotBlank(message = "İsim alanı boş olamaz")
    @Size(min = 2, max = 50, message = "İsim 2-50 karakter arasında olmalıdır")
    private String firstName;

    @NotBlank(message = "Soyisim alanı boş olamaz")
    @Size(min = 2, max = 50, message = "Soyisim 2-50 karakter arasında olmalıdır")
    private String lastName;

    @NotBlank(message = "E-posta alanı boş olamaz")
    @Email(message = "Geçerli bir e-posta adresi giriniz")
    private String email;

    private Role role;
} 