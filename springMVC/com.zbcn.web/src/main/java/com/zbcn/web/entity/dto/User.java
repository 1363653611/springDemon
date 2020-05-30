package com.zbcn.web.entity.dto;

import com.zbcn.web.validate.group.First;
import com.zbcn.web.validate.group.Second;
import com.zbcn.web.validate.self.Forbidden;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * 通过@GroupSequence指定验证顺序：先验证First分组，如果有错误立即返回而不会验证Second分组，接着如果First分组验证通过了，那么才去验证Second分组，
 * 最后指定User.class表示那些没有分组的在最后。这样我们就可以实现按顺序验证分组了
 */
@Data
@GroupSequence({First.class, Second.class, User.class})
public class User implements Serializable {
    private static final long serialVersionUID = 7001905127567672313L;

    @NotNull(message = "{user.id.null}", groups = {First.class})
    private Long id;

    @Length(min = 5, max = 20, message = "{user.name.length.illegal}", groups = {First.class})
    @Pattern(regexp = "[a-zA-Z]{5,20}", message = "{user.name.illegal}", groups = {Second.class})
    @Forbidden()
    private String name;

    private Date createTime;
}
