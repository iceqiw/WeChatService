package org.qiwei.mapper;

import org.apache.ibatis.annotations.Select;
import org.qiwei.domain.SysErrorCode;

/**
 * Created by admin on 2017/7/10.
 */
public interface TestMapper {
    @Select("select  error_code as errorCode ,error_msg as errorMsg from sys_error_code where error_code=#{id}")
    public SysErrorCode getById(final Long id);
}
