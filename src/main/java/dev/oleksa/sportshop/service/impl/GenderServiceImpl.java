package dev.oleksa.sportshop.service.impl;

import dev.oleksa.sportshop.model.user.Gender;
import dev.oleksa.sportshop.repository.GenderRepository;
import dev.oleksa.sportshop.service.GenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class GenderServiceImpl implements GenderService {

    private final GenderRepository genderRepository;

    @Override
    public Gender read(String name) {
        return genderRepository.findByNameEng(name);
    }
}
