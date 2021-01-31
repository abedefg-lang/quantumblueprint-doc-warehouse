package pers.tom.docwarehouse.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pers.tom.docwarehouse.model.entity.User;
import pers.tom.docwarehouse.model.supports.OutputData;
import pers.tom.docwarehouse.utils.DateFormatUtils;

/**
 * @author tom
 * @description
 * @date 2021/1/30 22:30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class UserDto implements OutputData<User> {

    @ApiModelProperty("主键")
    private Long userId;

    @ApiModelProperty("username")
    private String username;

    @ApiModelProperty("上次登录时间")
    private String lastLoginTime;

    @Override
    public void converterFrom(User user) {
        if(user != null){
            this.userId = user.getUserId();
            this.username = user.getUsername();
            this.lastLoginTime = DateFormatUtils.parseTime(DateFormatUtils.SECOND_LEVEL_PATTERN, user.getLastLoginTime());
        }
    }
}