package com.example.festival.report.entity;

import com.example.festival.festival.entity.Festival;
import com.example.festival.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Integer reportId;

    @Column(name = "title", nullable = false, length = 100)
    private String title; //제목

    @Column(name = "content", nullable = false, columnDefinition="TEXT")
    private String content; //내용

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt; // 작성일

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; //작성자

    @Column(name="done")
    private Boolean done = false; //조치 됐는지 (안 됐으면 - false)

    @Column(columnDefinition="TEXT")
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "festival_id", nullable = false)
    private Festival festival; //축제 정보


    @PrePersist
    protected void onCreate() {
        this.createdAt = new Timestamp(System.currentTimeMillis()); //한국 시간이 안 됨
        //this.updatedAt = new Timestamp(System.currentTimeMillis());
    }
}
