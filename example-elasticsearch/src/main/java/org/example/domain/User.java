package org.example.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author yaml
 * @since 2022/2/16
 */
@Getter
@Setter
@Accessors(chain = true)
public class User {
    private Integer id;
    private String name;
    private Integer age;
    private char sex;
}
