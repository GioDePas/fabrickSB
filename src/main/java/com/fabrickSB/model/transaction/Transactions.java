package com.fabrickSB.model.transaction;

import com.fabrickSB.model.transaction.Transaction;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Transactions {
	private List<Transaction> list;
}
