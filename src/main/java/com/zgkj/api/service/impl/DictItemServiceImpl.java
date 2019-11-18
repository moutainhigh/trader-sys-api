package com.zgkj.api.service.impl;

import com.zgkj.api.entity.DictItem;
import com.zgkj.api.mapper.DictItemMapper;
import com.zgkj.api.service.IDictItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Lucent
 * @since 2019-11-15
 */
@Service
public class DictItemServiceImpl extends ServiceImpl<DictItemMapper, DictItem> implements IDictItemService {
    @Autowired
    DictItemMapper dictItemMapper;

    public List<DictItem> getListbyType(){
        return dictItemMapper.getListbyType();
    }
}
