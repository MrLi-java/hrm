package top.lmqstudy.service.impl;

import top.lmqstudy.domain.Meal;
import top.lmqstudy.mapper.MealMapper;
import top.lmqstudy.service.IMealService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Mr.Li
 * @since 2021-02-28
 */
@Service
public class MealServiceImpl extends ServiceImpl<MealMapper, Meal> implements IMealService {

}
