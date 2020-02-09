package com.csv.service;

import com.csv.dto.UserDto;
import com.csv.mapper.UserMapper;
import com.csv.repository.UserRepository;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public HttpStatus processFile(MultipartFile file) throws IOException {
        log.info("Start processing file {}", file.getOriginalFilename());
        List<UserDto> rows = parseFile(file);
        log.info("Parsed successfully {} rows", rows.size());
        saveRows(rows);
        return HttpStatus.OK;
    }

    private List<UserDto> parseFile(MultipartFile file) throws IOException {
        Reader reader = new InputStreamReader(file.getInputStream());
        CsvToBean<UserDto> csvToBean = new CsvToBeanBuilder(reader)
                .withType(UserDto.class)
                .withSeparator(';')
                .withThrowExceptions(false)
                .build();
        return csvToBean.parse();
    }

    private void saveRows(List<UserDto> rows) {
        log.info("Saving records from file");
        for (UserDto userDto : rows) {
            try {
                userRepository.save(userMapper.toUser(userDto));
            } catch (Exception e) {
                userDto.setPhone("");
                userRepository.save(userMapper.toUser(userDto));
                continue;
            }
        }
    }

    public List<UserDto> getAllUsers() {
        return userMapper.toUserDtoList(userRepository.findAll());
    }
}
