package org.xxx.modules.base.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xxx.modules.base.dao.UserDao;
import org.xxx.modules.base.entity.User;

/**
 * @author Deveik
 */
@Service
public class UserService extends ServiceImpl<UserDao, User> {
    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Transactional(rollbackFor = Exception.class)
    public synchronized void decreaseAge(String userId) throws InterruptedException {
        User user = getById(userId);

        logger.info("user {}", user.getAge());

        this.wait(13000);
        if (user.getAge() >= 0) {
            user.setAge(user.getAge() - 1);
            updateById(user);
        }
    }
}
