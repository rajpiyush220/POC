package com.touchblankspot.message.kafka.producer.callback;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Data
@RequiredArgsConstructor
public class ListenableFutureCallbackImpl implements ListenableFutureCallback<SendResult<String, String>> {

    @NonNull  String message;

    @Override
    public void onFailure(Throwable ex) {
        System.out.println("Unable to send message=["
                + message + "] due to : " + ex.getMessage());
    }

    @Override
    public void onSuccess(SendResult<String, String> result) {

        System.out.println("Sent message=[" + message +
                "] with offset=[" + result.getRecordMetadata().offset() + "]");
    }
}
