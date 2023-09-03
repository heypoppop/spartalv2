package com.sparta.blog.service;

import com.sparta.blog.dto.BoardRequestDto;
import com.sparta.blog.dto.BoardResponseDto;
import com.sparta.blog.entity.Board;
import com.sparta.blog.entity.User;
import com.sparta.blog.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    //수정
//    @Transactional
//    public BoardResponseDto updateBoard(Long id, BoardRequestDto boardRequestDto) {
//        String password = boardRequestDto.getPassword();
//        Board board = findBoard(id);
//
//        if(board.getPassword().equals(password)) {
//            board.update(boardRequestDto);
//            return new BoardResponseDto(board);
//        } else {
//            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
//        }
//    }


    //조회
    public List<BoardResponseDto> getBoard() {
        return boardRepository.findAllByOrderByModifiedAtDesc().stream().map(BoardResponseDto::new).toList();
    }
    //생성
    public BoardResponseDto createBoard(BoardRequestDto boardRequestDto, User user) {
        Board board = boardRepository.save(new Board(boardRequestDto, user));
        return new BoardResponseDto(board);
    }
    //키워드 조회
//    public List<BoardResponseDto> getBoardByKeyword(String keyword) {
//        return boardRepository.findAllByContentsContainsOrderByModifiedAtDesc(keyword).stream().map(BoardResponseDto::new).toList();
//    }
    public BoardResponseDto getBoardById(Long id) {
        Board board = boardRepository.findBoardById(id).orElseThrow(() -> new RuntimeException("게시물이 존재하지 않습니다"));
        return new BoardResponseDto(board);
    }


    //삭제
//    public BoardResponseDto deleteBoard(Long id, BoardRequestDto boardRequestDto) {
////        String password = boardRequestDto.getPassword();
//        Board board = findBoard(id);
//
//        if(board.getPassword().equals(password)) {
//            boardRepository.delete(board);
//            return new BoardResponseDto(board);
//        } else {
//            throw new IllegalArgumentException("비밀번호가 다릅니다.");
//        }
//
//    }
    //검색
    private Board findBoard(Long id) {
        return boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("선택한 게시글이 없습니다."));
    }

//    private Board passwordCheck(Board board, String password) {
//        if(board.getPassword().equals(password)) {
//            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
//        } else {
//            return board;
//        }
//    }
}
