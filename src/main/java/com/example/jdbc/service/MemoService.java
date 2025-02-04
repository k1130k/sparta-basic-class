package com.example.jdbc.service;

import com.example.jdbc.dto.MemoRequestDto;
import com.example.jdbc.dto.MemoResponseDto;
import com.example.jdbc.entity.Memo;
import com.example.jdbc.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    @Transactional
    public MemoResponseDto saveMemo(MemoRequestDto dto) {
        Memo memo = new Memo(dto.getContent());
        Memo savedMemo = memoRepository.save(memo);
        return new MemoResponseDto(
                savedMemo.getId(),
                savedMemo.getContent()
        );
    }

    @Transactional(readOnly = true)
    public List<MemoResponseDto> getMemos() {
        List<Memo> memoList = memoRepository.findAll();

        List<MemoResponseDto> dtoList = new ArrayList<>();
        for (Memo memo : memoList) {
            MemoResponseDto memoResponseDto = new MemoResponseDto(
                    memo.getId(),
                    memo.getContent()
            );
            dtoList.add(memoResponseDto);
        }
        return dtoList;
    }

    @Transactional(readOnly = true)
    public MemoResponseDto getMemo(Long id) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 메모가 존재하지 않습니다.")
        );

        return new MemoResponseDto(
                memo.getId(),
                memo.getContent()
        );
    }

    @Transactional
    public MemoResponseDto updateMemo(Long memoId, MemoRequestDto dto) {
        Memo memo = memoRepository.updateContent(memoId, dto.getContent());

        return new MemoResponseDto(
                memo.getId(),
                memo.getContent()
        );
    }

    @Transactional
    public void deleteMemoById(Long memoId) {
        memoRepository.deleteById(memoId);
    }
}
