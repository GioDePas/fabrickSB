package com.fabrickSB.model.transaction;

import com.fabrickSB.model.transaction.Transactions;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TransactionResponse {
	private String status;
	private Transactions payload;
	private List<Object> error;
}
