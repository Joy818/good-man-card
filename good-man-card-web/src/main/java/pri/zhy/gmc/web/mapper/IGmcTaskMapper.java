package pri.zhy.gmc.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import pri.zhy.gmc.web.entity.GmcTask;

/**
 * Task Mapper
 *
 * @author zhy
 * @date 2022年03月15日
 */
@Repository
public interface IGmcTaskMapper extends BaseMapper<GmcTask> {

    int countByState(String type, Integer state);


    int selectRankByState(String rid, Integer state);
}
