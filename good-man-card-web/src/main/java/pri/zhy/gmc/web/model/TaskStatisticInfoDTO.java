package pri.zhy.gmc.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 任务统计信息
 *
 * @author zhy
 * @date 2022年03月15日
 */
@Data
public class TaskStatisticInfoDTO {
    private String type;
    private Integer total;
    private Integer success;
    private Integer failed;
    private Integer doing;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;
}
