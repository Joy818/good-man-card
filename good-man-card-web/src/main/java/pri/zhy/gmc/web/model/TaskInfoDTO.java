package pri.zhy.gmc.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 任务信息
 *
 * @author zhy
 * @date 2022年03月15日
 */
@Data
public class TaskInfoDTO {
    private String rid;
    private String type;
    private Integer state;
    private String remarks;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    private Integer rank;
}
