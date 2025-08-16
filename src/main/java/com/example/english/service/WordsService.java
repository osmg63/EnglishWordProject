package com.example.english.service;

import com.example.english.dto.DtoTestGame;
import com.example.english.dto.DtoTransactionWord;
import com.example.english.entity.Words;
import com.example.english.exception.BaseException;
import com.example.english.exception.ErrorMessage;
import com.example.english.exception.MessageType;
import com.example.english.repo.WordsRepo;
import com.itextpdf.io.font.PdfEncodings;


import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;



@RequiredArgsConstructor
@Service
public class WordsService {

    private final WordsRepo wordsRepository;
    private final TransactionService transactionService;
    private final UserService userService;



    public List<Words> findWordIdsByUserIdNotInTransactions(String workType, int userId) {
        try {
            List<Integer> id = transactionService.findWordIdsByUserId(userId);
            if(id.isEmpty()) {
                List<Words> words=wordsRepository.findWithoutIds(workType);
                return words;
            }
            List<Words> words = wordsRepository.findRandomWordsByWorkTypeAndExcludeWordIds(workType, id);
            return words;
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, e.getMessage()));
        }
    }

    public void deleteWorldById(int id) {
        try {
            wordsRepository.deleteById(id);
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.DElETE_FAILED, e.getMessage()));
        }

    }

    public byte[] pdfForWord(String workType,int id){
    try{
      var data =findWordIdsByUserIdNotInTransactions(workType,id);
        DtoTransactionWord dtoTransactionWord = new DtoTransactionWord();
        dtoTransactionWord.setDate(LocalDateTime.now());
        dtoTransactionWord.setKnow(true);
        dtoTransactionWord.setUserId(id);

        for (Words word : data){
            dtoTransactionWord.setWordId(word.getId());
            transactionService.createTransaction(dtoTransactionWord);
        }

      List<Map<String,Object>> map= data.stream().map(word -> {

          Map<String,Object> map1 = new HashMap<>();
          map1.put("terms", word.getTerms());
          map1.put("meanings", word.getMeanings());
          map1.put("meanings2", word.getMeanings2());
          map1.put("meanings3", word.getMeanings3());
          map1.put("workType", word.getWorkType());
          return map1;
      }).collect(Collectors.toList());

      return generateWordListPdf(map);
    }catch (Exception e){
        throw new BaseException(new ErrorMessage(MessageType.RECORD_FAILED,e.getMessage()));
        }
    }

    public byte[] generateWordListPdf(List<Map<String, Object>> words) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdf = new PdfDocument(writer);
        Document doc = new Document(pdf);

        InputStream fontStream = getClass().getClassLoader().getResourceAsStream("fonts/a.ttf");


        PdfFont font = PdfFontFactory.createFont(
                fontStream.readAllBytes(),
                PdfEncodings.IDENTITY_H
        );


        float[] columnWidths = {100f, 150f, 150f, 150f, 50f};
        Table table = new Table(columnWidths).setWidth(UnitValue.createPercentValue(100));

        table.addHeaderCell(new Cell().add(new Paragraph("Kelime")).setBackgroundColor(ColorConstants.LIGHT_GRAY).setFont(font));
        table.addHeaderCell(new Cell().add(new Paragraph("Anlam 1")).setBackgroundColor(ColorConstants.LIGHT_GRAY).setFont(font));
        table.addHeaderCell(new Cell().add(new Paragraph("Anlam 2")).setBackgroundColor(ColorConstants.LIGHT_GRAY).setFont(font));
        table.addHeaderCell(new Cell().add(new Paragraph("Anlam 3")).setBackgroundColor(ColorConstants.LIGHT_GRAY).setFont(font));
        table.addHeaderCell(new Cell().add(new Paragraph("Seviye")).setBackgroundColor(ColorConstants.LIGHT_GRAY).setFont(font));

        for (Map<String, Object> word : words) {
            table.addCell(new Paragraph(String.valueOf(word.get("terms"))).setFont(font));
            table.addCell(new Paragraph(String.valueOf(word.get("meanings"))).setFont(font));
            table.addCell(new Paragraph(String.valueOf(word.get("meanings2"))).setFont(font));
            table.addCell(new Paragraph(String.valueOf(word.get("meanings3"))).setFont(font));
            table.addCell(new Paragraph(String.valueOf(word.get("workType"))).setFont(font));
        }

        doc.add(table);
        doc.close();
        return outputStream.toByteArray();
    }

    public void createWorld(Words words) {
        try {
            wordsRepository.save(words);
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.RECORD_FAILED, e.getMessage()));
        }
    }
    public List<Words> getAllWords() {
        return wordsRepository.findAll();
    }
    public List<Words> findWordsKnowTrue(int userId, String workType) {
        try {
            List<Integer> id = transactionService.findWordIdsByUserIdKnowTrue(userId);
            List<Words> words = wordsRepository.findWorkByIdsAndByWorkType(id, workType);
            return words;
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, e.getMessage()));
        }


    }

    public List<Words> findWordsKnowTrue2(int userId) {

        try {
            List<Integer> id = transactionService.findWordIdsByUserIdKnowTrue(userId);
            List<Words> words = wordsRepository.findWorkByIds(id);
            return words;
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, e.getMessage()));
        }

    }

    public List<Words> findWordsKnowFalse(int userId, String workType) {
        try {
            List<Integer> id = transactionService.findWordIdsByUserIdKnowFalse(userId);
            List<Words> words = wordsRepository.findWorkByIdsAndByWorkType(id, workType);
            Collections.shuffle(words);
            return words;
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, e.getMessage()));
        }


    }

    public DtoTestGame findRandomWord(String workType) {
        try {
            Optional<Words> words = wordsRepository.findRandomWordByWorkType(workType);
            List<String> answer = wordsRepository.findRandomMeaningsExcluding(words.get().getMeanings());
            if (answer == null || words.isEmpty()) {
                throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_FOUND));
            }
            DtoTestGame testGameResponseDto = new DtoTestGame();
            testGameResponseDto.setId(words.get().getId());
            testGameResponseDto.setTerm(words.get().getTerms());
            testGameResponseDto.setWord1(words.get().getMeanings());
            testGameResponseDto.setWord2(answer.get(0));
            testGameResponseDto.setWord3(answer.get(1));
            testGameResponseDto.setWord4(answer.get(2));
            return testGameResponseDto;
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_FOUND, e.getMessage()));
        }

    }

    public Boolean questionTest(int wordId, String meanings, int id) {
        try {
            Words word = wordsRepository.findWordsById(wordId);
            if (word.getMeanings().equals(meanings)) {
                userService.addPoint(id);
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, e.getMessage()));
        }


    }


}
