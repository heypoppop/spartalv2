package com.sparta.blog.controller;
import com.sparta.blog.dto.BoardRequestDto;
import com.sparta.blog.dto.BoardResponseDto;
import com.sparta.blog.security.UserDetailsImpl;
import com.sparta.blog.service.BoardService;
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

    //게시글 작성
    @PostMapping("/board")
    public BoardResponseDto createBoard(
            @RequestBody BoardRequestDto boardRequestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return boardService.createBoard(boardRequestDto, userDetails.getUser());
    }
//    //게시글 키워드 조회
//    @GetMapping("/board/contents")
//    public List<BoardResponseDto> getBoardByKeyword(String keyword) {
//        return boardService.getBoardByKeyword(keyword);
//    }

    @GetMapping("/board/{id}")
    public BoardResponseDto getBoardById(@PathVariable Long id) {
        return boardService.getBoardById(id);
    }




        //수정
//    @PutMapping("/board/{id}")
//    public BoardResponseDto updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto) {
//        return boardService.updateBoard(id, boardRequestDto);
//    }

    // 삭제
//    @DeleteMapping("/board/{id}")
//    public BoardResponseDto deleteBoard(@PathVariable Long id, @RequestBody BoardRequestDto boardRequestDto) {
//        return boardService.deleteBoard(id, boardRequestDto);
//    }

}
