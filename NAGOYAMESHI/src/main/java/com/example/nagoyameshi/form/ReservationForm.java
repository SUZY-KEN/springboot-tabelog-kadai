package com.example.nagoyameshi.form;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReservationForm {
	

	@NotBlank(message="予約日を指定してください。")
	private String checkinDate;
	
	@NotBlank(message="時間を指定してください。")
	private String checkinTime;
	
	@NotNull(message="予約人数を指定してください。")
	@Min(value=1,message="予約人数は1人以上にしてください。")
	private Integer numberOfPeople;
	
	
}
