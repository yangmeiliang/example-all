package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.domain.dao.DemoDAO;
import org.example.domain.entity.DemoEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yaml
 * @since 2022/1/22
 */
@RestController
@AllArgsConstructor
public class TestController {

    private final DemoDAO demoDAO;

    @PostMapping("/demo/save")
    public void saveDemo(@RequestBody DemoEntity param) {
        demoDAO.saveDemo(param);
    }

    @PostMapping("/demo/delete")
    public void removeDemo(Long id) {
        demoDAO.removeDemo(id);
    }

    @PostMapping("/demo/update")
    public void updateDemo(@RequestBody DemoEntity demoEntity) {
        demoDAO.updateDemo(demoEntity);
    }

    @PostMapping("/demo/find")
    public DemoEntity findDemoById(Long id) {
        return demoDAO.findDemoById(id);
    }
}
