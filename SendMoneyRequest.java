package com.upiapp.demo.model;

import lombok.Data;

@Data
public class SendMoneyRequest {
    private String fromMobile;
    private String toMobile;
    private long amount;
    private String pin;
    private String remark;
	public String getFromMobile() {
		return fromMobile;
	}
	public void setFromMobile(String fromMobile) {
		this.fromMobile = fromMobile;
	}
	public String getToMobile() {
		return toMobile;
	}
	public void setToMobile(String toMobile) {
		this.toMobile = toMobile;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
    
    
}

