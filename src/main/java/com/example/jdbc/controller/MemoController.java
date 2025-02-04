package com.example.jdbc.controller;

import com.example.jdbc.dto.MemoRequestDto;
import com.example.jdbc.dto.MemoResponseDto;
import com.example.jdbc.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @PostMapping("/memos")
    public ResponseEntity<MemoResponseDto> saveMemo(@RequestBody MemoRequestDto dto) {
        return ResponseEntity.ok(memoService.saveMemo(dto));
    }

    @GetMapping("/memos")
    public ResponseEntity<List<MemoResponseDto>> getMemos() {
        return ResponseEntity.ok(memoService.getMemos());
    }

    @GetMapping("/memos/{memoId}")
    public ResponseEntity<MemoResponseDto> getMemo(@PathVariable Long memoId) {
        return ResponseEntity.ok(memoService.getMemo(memoId));
    }

    @PutMapping("/memos/{memoId}")
    public ResponseEntity<MemoResponseDto> updateMemo(@PathVariable Long memoId, @RequestBody MemoRequestDto dto) {
        return ResponseEntity.ok(memoService.updateMemo(memoId, dto));
    }

    @DeleteMapping("/memos/{memoId}")
    public void deleteMemo(@PathVariable Long memoId) {
        memoService.deleteMemoById(memoId);
    }
}
