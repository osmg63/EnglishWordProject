package com.example.english.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorMessage {
    private MessageType messageType;
    private String ofStatic;
    public ErrorMessage(MessageType messageType) {
        this.messageType = messageType;
    }
    public String prepareErrorMessage() {
        StringBuilder builder=new StringBuilder();
        builder.append(messageType.getMessage());
        if (ofStatic != null) {
            builder.append(" : "+ofStatic);
        }
        return builder.toString();
    }
}
