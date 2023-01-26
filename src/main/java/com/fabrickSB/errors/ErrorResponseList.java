package com.fabrickSB.errors;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ErrorResponseList {
	private String status;
	private List<ErrorResponse> errors;
	private Object payload;
}
