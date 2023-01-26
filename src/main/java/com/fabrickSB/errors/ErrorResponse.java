package com.fabrickSB.errors;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ErrorResponse {
	private String code;
	private String description;
	private String params;
}
