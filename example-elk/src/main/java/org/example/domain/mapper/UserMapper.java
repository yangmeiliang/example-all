package org.example.domain.mapper;

import org.example.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 数据库表 <user> mapper层
 *
 * @author yangmeiliang
 * @since 2022/2/17
 */
public interface UserMapper extends JpaRepository<User, Integer> {

}