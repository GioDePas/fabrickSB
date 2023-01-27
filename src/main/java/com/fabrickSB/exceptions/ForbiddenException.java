package com.fabrickSB.exceptions;

import com.fabrickSB.errors.ErrorResponseList;
import lombok.*;

import java.io.Serial;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ForbiddenException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -3224233561364457224L;
    private ErrorResponseList error;
}
