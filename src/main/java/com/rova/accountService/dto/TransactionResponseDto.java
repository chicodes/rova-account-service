package com.rova.accountService.dto;

import lombok.Data;

@Data
public class TransactionResponseDto {
	private RespBody respBody;
	private String respDescription;
	private String respCode;
}
