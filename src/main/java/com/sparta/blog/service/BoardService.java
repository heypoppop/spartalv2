package com.sparta.blog.service;

import com.sparta.blog.dto.BoardRequestDto;
import com.sparta.blog.dto.BoardResponseDto;
import com.sparta.blog.entity.Board;
import com.sparta.blog.entity.User;
import com.sparta.blog.repository.BoardRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    // 전체 조회
    public List<BoardResponseDto> getBoard() {
        return boardRepository.findAllByOrderByModifiedAtDesc().stream().map(BoardResponseDto::new).toList();
    }

    //생성
    public BoardResponseDto createBoard(BoardRequestDto boardRequestDto, User user) {
        Board board = boardRepository.save(new Board(boardRequestDto, user));
        return new BoardResponseDto(board);
    }

    // 게시물 id로 조회
    public BoardResponseDto getBoardById(Long id) {
        Board board = boardRepository.findBoardById(id).orElseThrow(() -> new RuntimeException("게시물이 존재하지 않습니다"));
        return new BoardResponseDto(board);
    }

    // 수정
    @Transactional
    public ResponseEntity<String> updateBoard(Long id, BoardRequestDto boardRequestDto, User user) {
        Board board = findBoard(id);
        if (!board.getUser().getUsername().equals(user.getUsername())) {
            return ResponseEntity.status(400).body("상태코드 : " + HttpStatus.BAD_REQUEST.value()  + " 메세지 : 선생님 게시물이 아닙니다.");}
        board.update(boardRequestDto, user);
        return ResponseEntity.status(200).body("상태코드 : " + HttpStatus.OK.value() + " 메세지 : 게시물 수정 성공");
    }

    // 삭제
    public ResponseEntity<String> deleteBoard(Long id, User user) throws IllegalArgumentException {
        Board board = findBoard(id);
        if(!board.getUser().getUsername().equals(user.getUsername())) {
            return ResponseEntity.status(400).body("상태코드 : " + HttpStatus.BAD_REQUEST.value() + " 메세지 : 선생님 게시물이 아닙니다.");}
        boardRepository.delete(board);
        return ResponseEntity.status(200).body("상태코드 : " + HttpStatus.OK.value() + " 메세지 : 게시물 삭제 성공");
    }

    // DB에서 찾기
    private Board findBoard(Long id) {
        return boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("선택한 게시글이 없습니다."));
    }
}
