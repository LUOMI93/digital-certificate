package com.example.graduatedemo.demos.web.domain;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Table(name = "style_table")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Style {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long styleid;
    private String stylebase64;

    public Long getStyleid() {
        return styleid;
    }

    public void setStyleid(Long styleid) {
        this.styleid = styleid;
    }

    public String getStylebase64() {
        return stylebase64;
    }

    public void setStylebase64(String stylebase64) {
        this.stylebase64 = stylebase64;
    }
}
