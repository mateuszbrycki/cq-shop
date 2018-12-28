package com.cqshop.logging.notification;

import com.cqshop.logging.usermanagement.UserManagementStreamsConfig;
import com.cqshop.notification.avro.ActivationLinkSent;
import com.cqshop.notification.avro.OrderConfirmationSent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * Created by Mateusz Brycki on 13/12/2018.
 */
@Slf4j
@Component
public class NotificationEventsListener {

    @StreamListener(target = NotificationServiceStreamsConfig.NOTIFICATION_EVENTS, condition = "headers['event-type']=='ActivationLinkSent'")
    public void handleActivationLinkSent(ActivationLinkSent event) {
        log.info("Received ActivationLinkSent event " + event.toString());
    }

    @StreamListener(target = NotificationServiceStreamsConfig.NOTIFICATION_EVENTS, condition = "headers['event-type']=='OrderConfirmationSent'")
    public void handleOrderConfirmationSent(OrderConfirmationSent event) {
        log.info("Received OrderConfirmationSent event " + event.toString());
    }

}
