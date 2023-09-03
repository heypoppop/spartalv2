package com.sparta.blog.controller;

import com.sparta.blog.dto.BoardRequestDto;
import com.sparta.blog.dto.BoardResponseDto;
import com.sparta.blog.security.UserDetailsImpl;
import com.sparta.blog.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    //전체 게시물 조회
    @GetMapping("/board")
    public List<BoardResponseDto> getBoard() {
        return boardService.getBoard();
    }

    //게시글의 id로 조회
    @GetMapping("/board/{id}")
    public BoardResponseDto getBoardById(@PathVariable Long id) {
        return boardService.getBoardById(id);
    }

    //게시글 작성
    @PostMapping("/board")
    public BoardResponseDto createBoard(
            @RequestBody BoardRequestDto boardRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return boardService.createBoard(boardRequestDto, userDetails.getUser());
    }

    // 수정
    @PutMapping("/board/{id}")
    public ResponseEntity<String> updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return boardService.updateBoard(id, boardRequestDto, userDetails.getUser());
    }

    // 삭제
    @DeleteMapping("/board/{id}")
    public ResponseEntity<String> deleteBoard(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return boardService.deleteBoard(id, userDetails.getUser());
    }

}
