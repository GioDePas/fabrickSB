package com.fabrickSB.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ErrorResponse {
	private String code;
	private String description;
	private String params;
}
