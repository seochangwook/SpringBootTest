package com.tprojectboot.application.vo;

import java.io.Serializable;

public class MessageVO implements Serializable{
	private String senderName;
	private String sendMessage;
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public String getSendMessage() {
		return sendMessage;
	}
	public void setSendMessage(String sendMessage) {
		this.sendMessage = sendMessage;
	}
}
