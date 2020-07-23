package com.wandou.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author liming
 * @date 2020/7/22
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MessageDTO implements Delayed {

    private Integer msgId;

    private String msgBody;

    private String tags;

    @Override
    public long getDelay(TimeUnit unit) {
        return 0;
    }

    @Override
    public int compareTo(Delayed o) {
        if (o instanceof MessageDTO) {
            return this.msgId.compareTo(((MessageDTO) o).getMsgId());
        }
        return 0;
    }
}
