package org.example.domain.dao;

import org.example.domain.entity.DemoEntity;

/**
 * @author yaml
 * @since 2022/1/22
 */
public interface DemoDAO {
    void saveDemo(DemoEntity demoEntity);

    void removeDemo(Long id);

    void updateDemo(DemoEntity demoEntity);

    DemoEntity findDemoById(Long id);
}
