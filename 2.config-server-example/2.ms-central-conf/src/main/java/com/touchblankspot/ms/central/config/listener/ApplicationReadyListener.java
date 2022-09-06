package com.touchblankspot.ms.central.config.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;

@RequiredArgsConstructor
public class ApplicationReadyListener {

    @EventListener(ApplicationReadyListener.class)
    public void onApplicationReady(){

    }
}
