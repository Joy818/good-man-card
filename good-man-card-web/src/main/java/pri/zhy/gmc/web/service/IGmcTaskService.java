package pri.zhy.gmc.web.service;

import pri.zhy.gmc.web.model.AddTaskRequest;
import pri.zhy.gmc.web.model.QueueInfoDTO;
import pri.zhy.gmc.web.model.TaskInfoDTO;
import pri.zhy.gmc.web.model.TaskStatisticInfoDTO;

/**
 * TODO
 *
 * @author zhy
 * @date 2022年03月15日
 */
public interface IGmcTaskService {

    void addTask(AddTaskRequest addTaskRequest);

    TaskInfoDTO getTaskInfo(String rid);

    QueueInfoDTO getQueueInfo(String type);

    TaskStatisticInfoDTO getStatisticInfo(String type);
}
