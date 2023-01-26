package com.fabrickSB.exception;

import com.fabrickSB.errors.ErrorResponseList;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BadRequestException extends RuntimeException {
	private static final long serialVersionUID = -2901421369287682731L;
	private ErrorResponseList error;
}
