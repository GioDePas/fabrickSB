package com.fabrickSB.model;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ErrorResponseList {
	private String status;
	private List<ErrorResponse> errors;
	private Object payload;
}
