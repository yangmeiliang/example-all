package org.example.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author yaml
 * @since 2022/1/22
 */
@Getter
@Setter
@Document(collection = "demo_collection")
public class DemoEntity {
    @Id
    private Long id;
    private String title;
    private String description;
    private Date createTime;
    private Date modifyTime;
}
