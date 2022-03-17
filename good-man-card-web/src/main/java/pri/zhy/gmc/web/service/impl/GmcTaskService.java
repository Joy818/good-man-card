package pri.zhy.gmc.web.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pri.zhy.gmc.web.entity.GmcTask;
import pri.zhy.gmc.web.enums.TaskStateEnum;
import pri.zhy.gmc.web.mapper.IGmcTaskMapper;
import pri.zhy.gmc.web.model.*;
import pri.zhy.gmc.web.service.IGmcTaskService;

import java.time.LocalDateTime;
import java.util.Objects;


/**
 * 任务接口
 *
 * @author zhy
 * @date 2022年03月15日
 */
@Service
public class GmcTaskService implements IGmcTaskService {

    @Autowired
    private IGmcTaskMapper taskMapper;

    @Override
    public AddTaskResult addTask(AddTaskRequest addTaskRequest) {
        Assert.notBlank(addTaskRequest.getType(), "任务类型不能为空");
        Assert.notBlank(addTaskRequest.getParams(), "任务参数不能为空");

        JSONObject obj = JSONUtil.parseObj(addTaskRequest.getParams());
        Assert.isFalse(obj.isEmpty(), "参数不能为空");
        for (String key : obj.keySet()) {
            Assert.isFalse(obj.isNull(key), "参数不能为空");
        }

        GmcTask task = new GmcTask();
        task.setRid(IdUtil.fastSimpleUUID());
        task.setType(addTaskRequest.getType());
        task.setParams(addTaskRequest.getParams());
        task.setCreateTime(LocalDateTime.now());
        task.setUpdateTime(LocalDateTime.now());
        task.setState(TaskStateEnum.CREATED.getCode());

        taskMapper.insert(task);

        return AddTaskResult.builder().rid(task.getRid()).build();
    }

    @Override
    public TaskInfoDTO getTaskInfo(String rid) {
        Assert.notBlank(rid, "任务ID不能为空");
        GmcTask task = taskMapper.selectOne(
                new LambdaQueryWrapper<>(GmcTask.class)
                        .select()
                        .eq(GmcTask::getRid, rid.trim())
        );

        if (task == null) {
            return null;
        }

        TaskInfoDTO dto = new TaskInfoDTO();
        BeanUtils.copyProperties(task, dto);


        if (Objects.equals(TaskStateEnum.CREATED.getCode(), task.getState())) {
            int rank = taskMapper.selectRankByState(rid, TaskStateEnum.CREATED.getCode());
            dto.setRank(rank);
        }
        return dto;
    }


    @Override
    public QueueInfoDTO getQueueInfo(String type) {
        Assert.notBlank(type, "任务类型不能为空");

        int queuedNum = taskMapper.countByState(type, TaskStateEnum.CREATED.getCode());

        QueueInfoDTO queueInfoDTO = new QueueInfoDTO();
        queueInfoDTO.setType(type);
        queueInfoDTO.setNum(queuedNum);
        return queueInfoDTO;
    }

    @Override
    public TaskStatisticInfoDTO getStatisticInfo(String type) {
        Assert.notBlank(type);

        int successCount = taskMapper.countByState(type, TaskStateEnum.SUCCESS.getCode());
        int failedCount = taskMapper.countByState(type, TaskStateEnum.FAILED.getCode());
        int doingCount = taskMapper.countByState(type, TaskStateEnum.ON_DOING.getCode());

        TaskStatisticInfoDTO statisticInfoDTO = new TaskStatisticInfoDTO();
        statisticInfoDTO.setType(type);
        statisticInfoDTO.setSuccess(successCount);
        statisticInfoDTO.setFailed(failedCount);
        statisticInfoDTO.setDoing(doingCount);
        statisticInfoDTO.setTotal(successCount + failedCount + doingCount);
        statisticInfoDTO.setTime(LocalDateTime.now());

        return statisticInfoDTO;
    }
}
