package pers.tom.docwarehouse.model.param;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.util.StringUtils;
import pers.tom.docwarehouse.model.entity.AuditableEntity;
import pers.tom.docwarehouse.model.supports.InputData;
import pers.tom.docwarehouse.utils.DateFormatUtils;

/**
 * @author tom
 * @description 审记数据的查询对象
 * @date 2021/1/30 23:42
 */
@Data
public abstract class AuditableQuery<T extends AuditableEntity> implements InputData<QueryWrapper<T>> {

    @ApiModelProperty("创建人")
    private String createBy;

    @ApiModelProperty("起始创建时间")
    private String startCreateTime;

    @ApiModelProperty("结束创建时间")
    private String endCreateTime;

    @ApiModelProperty("修改人")
    private String updateBy;

    @ApiModelProperty("起始修改时间")
    private String startUpdateTime;

    @ApiModelProperty("结束修改时间")
    private String endUpdateTime;

    @Override
    public QueryWrapper<T> converterTo(){

        //创建查询条件
        String pattern = DateFormatUtils.SECOND_LEVEL_PATTERN;
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight(!StringUtils.isEmpty(createBy), "create_by", createBy)
                .ge(!StringUtils.isEmpty(startCreateTime), "create_time", DateFormatUtils.getTime(pattern, startCreateTime))
                .le(StringUtils.isEmpty(endCreateTime), "create_time", DateFormatUtils.getTime(pattern, endCreateTime))
                .likeRight(!StringUtils.isEmpty(updateBy), "update_by", updateBy)
                .ge(!StringUtils.isEmpty(startUpdateTime), "update_time", DateFormatUtils.getTime(pattern, startUpdateTime))
                .le(StringUtils.isEmpty(endUpdateTime), "update_time", DateFormatUtils.getTime(pattern, endCreateTime));

        this.fillConditions(queryWrapper);

        return queryWrapper;
    }

    /**
     * 交给子类填充条件
     * @param queryWrapper 查询条件
     */
    protected abstract void fillConditions(QueryWrapper<T> queryWrapper);
}