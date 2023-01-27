package com.fabrickSB.exceptions;

import com.fabrickSB.errors.ErrorResponseList;
import lombok.*;

import java.io.Serial;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BadRequestException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -2901421369287682731L;
    private ErrorResponseList error;
}
