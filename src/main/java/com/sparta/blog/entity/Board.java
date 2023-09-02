package com.sparta.blog.entity;

import com.sparta.blog.dto.BoardRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "board")
@NoArgsConstructor
public class Board extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "contents", nullable = false, length = 500)
    private String contents;
    @Column(name = "title")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Board(BoardRequestDto boardrequestDto) {
        this.contents = boardrequestDto.getContents();
        this.title = boardrequestDto.getTitle();
    }

    public void update(BoardRequestDto boardrequestDto) {
        this.contents = boardrequestDto.getContents();
        this.title = boardrequestDto.getTitle();
    }

}
