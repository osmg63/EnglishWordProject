package com.example.english.dto;

import com.example.english.entity.TransactionWord;
import com.example.english.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;



@Mapper(componentModel = "spring")
public interface DtoMapper {


    // TransactionWord Entity'den DTO'ya dönüşüm
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "word.id", target = "wordId")
    DtoTransactionWord toDTO(TransactionWord transactionWord);


    // TransactionWordDTO'dan Entity'ye dönüşüm
    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "wordId", target = "word.id")
    TransactionWord toEntity(DtoTransactionWord dtoTransactionWord);

    DtoUser userToUserResponseDto(User user);
   List<DtoUser> usersToUserResponseDto(List<User> user);

    DtoUserIU userToUserRequestDto(User user);
    User userResponseDtoToUser(DtoUserIU dtoUserIU);
    User userRequestDtoToUser(DtoUserIU dtoUserIU);

}

