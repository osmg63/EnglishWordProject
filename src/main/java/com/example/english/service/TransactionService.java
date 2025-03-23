package com.example.english.service;

import com.example.english.dto.DtoMapper;
import com.example.english.dto.DtoTransactionWord;
import com.example.english.entity.TransactionWord;
import com.example.english.exception.BaseException;
import com.example.english.exception.ErrorMessage;
import com.example.english.exception.MessageType;
import com.example.english.repo.TransactionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;



@RequiredArgsConstructor
@Service
public class TransactionService {
    private final TransactionRepo transactionRepository;
    private final DtoMapper dtoMapper;
    public List<Integer> findWordIdsByUserIdKnowTrue(int userId) {
        try {
            return transactionRepository.findWordIdsByUserIdAAndKnowTrue(userId);
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_FOUND));
        }

    }

    public List<Integer> findWordIdsByUserIdKnowFalse(int userId) {
        try {
            return transactionRepository.findWordIdsByUserIdKAndKnowIsFalse(userId);
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_FOUND));
        }

    }

    public DtoTransactionWord createTransaction(DtoTransactionWord transaction) {
        try {
            transaction.setDate(LocalDateTime.now());
            TransactionWord word = dtoMapper.toEntity(transaction);
            transactionRepository.save(word);
            return dtoMapper.toDTO(word);
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.RECORD_FAILED, e.getMessage()));
        }

    }

    public int getKnownWordsCountByUserId(int userId) {
        try {
            return transactionRepository.countKnownWordsByUserId(userId);
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_FOUND, e.getMessage()));
        }

    }

}
