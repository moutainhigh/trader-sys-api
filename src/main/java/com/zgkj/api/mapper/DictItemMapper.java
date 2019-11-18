package com.zgkj.api.mapper;

import com.zgkj.api.entity.DictItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Lucent
 * @since 2019-11-15
 */
public interface DictItemMapper extends BaseMapper<DictItem> {

    List<DictItem> getListbyType();
}
