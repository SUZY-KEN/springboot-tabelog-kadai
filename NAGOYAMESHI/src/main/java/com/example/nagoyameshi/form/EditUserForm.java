package com.example.nagoyameshi.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EditUserForm {

	@NotBlank(message="氏名を入力してください。")
	private String name;
	
	@NotBlank(message = "メールアドレスを入力してください")
	@Email(message="メールアドレスは正しい形式で入力してください")
	private String email;
	
}
