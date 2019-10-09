package com.keya.demoelastic.artiz.infra;

import com.keya.demoelastic.artiz.exposition.SubscriberPostDTO;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class SubscriberResponseDTO {
    private SubscriberPostDTO subscriberPostDTO;
    private String message;

}
