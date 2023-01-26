package com.fabrickSB.model;

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
