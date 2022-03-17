package pri.zhy.gmc.web.service;

import pri.zhy.gmc.web.model.*;

/**
 * TODO
 *
 * @author zhy
 * @date 2022年03月15日
 */
public interface IGmcTaskService {

    AddTaskResult addTask(AddTaskRequest addTaskRequest);

    TaskInfoDTO getTaskInfo(String rid);

    QueueInfoDTO getQueueInfo(String type);

    TaskStatisticInfoDTO getStatisticInfo(String type);
}
