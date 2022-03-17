package pri.zhy.gmc.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Task 数据库映射
 *
 * @author zhy
 * @date 2022年03月15日
 */
@Data
@TableName("gmc_task")
public class GmcTask {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String rid;
    private String type;
    private String params;
    private Integer state;
    private String result;
    private String remarks;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
