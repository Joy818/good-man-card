package pri.zhy.gmc.web.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pri.zhy.gmc.web.enums.ResultEnum;
import pri.zhy.gmc.web.model.*;
import pri.zhy.gmc.web.service.IGmcTaskService;

/**
 * 任务接口
 *
 * @author zhy
 * @date 2022年03月15日
 */
@Slf4j
@RestController
@RequestMapping("/task")
public class GmcTaskController {

    @Autowired
    private IGmcTaskService taskService;

    @PostMapping("/add")
    public R<AddTaskResult> addTask(@RequestBody AddTaskRequest addTaskRequest) {
        log.info("request={}", addTaskRequest);
        return R.success(taskService.addTask(addTaskRequest));
    }

    @GetMapping("/get")
    public R<TaskInfoDTO> getTaskInfo(String rid) {
        TaskInfoDTO taskInfo = taskService.getTaskInfo(rid);
        if (taskInfo == null) {
            return R.failed(ResultEnum.DATA_NOT_FOUND);
        }
        return R.success(taskInfo);
    }

    @GetMapping("/queueInfo")
    public R<QueueInfoDTO> getQueueInfo(String type) {
        return R.success(taskService.getQueueInfo(type));
    }



    @GetMapping("/statistic")
    public R<TaskStatisticInfoDTO> getTaskStatisticInfo(String type) {
        return R.success(taskService.getStatisticInfo(type));
    }

}
