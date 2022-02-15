package org.example.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * @author yaml
 * @since 2021/8/17
 */
@RestController
public class TestController {

    /**
     * 测试
     */
    @PostMapping("/test")
    public Data test(Data param) {
        return param;
    }


    @Getter
    @Setter
    @NoArgsConstructor
    public static class Data {

        /**
         * 主键
         */
        @NotBlank
        private String id;
    }
}
