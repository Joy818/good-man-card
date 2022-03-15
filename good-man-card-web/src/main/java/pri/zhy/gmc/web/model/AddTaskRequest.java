package pri.zhy.gmc.web.model;

import lombok.Data;

/**
 * 新增任务
 *
 * @author zhy
 * @date 2022年03月15日
 */
@Data
public class AddTaskRequest {
    private String type;
    private String params;
}
