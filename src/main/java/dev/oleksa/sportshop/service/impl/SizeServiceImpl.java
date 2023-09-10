package dev.oleksa.sportshop.service.impl;

import dev.oleksa.sportshop.mapper.SizeMapper;
import dev.oleksa.sportshop.model.dto.SizeDto;
import dev.oleksa.sportshop.repository.SizeRepository;
import dev.oleksa.sportshop.service.SizeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SizeServiceImpl implements SizeService {

    private final SizeRepository sizeRepository;
    private final SizeMapper sizeMapper;

    @Override
    public SizeDto createSize(SizeDto sizeDto) {
        var size = sizeMapper.toEntity(sizeDto);

        size = sizeRepository.save(size);

        return sizeMapper.toDto(size);
    }

    @Override
    public SizeDto readSize(Long sizeId) {
        return sizeMapper.toDto(
                sizeRepository.findById(sizeId)
                        .orElseThrow()
        );
    }

    @Override
    public SizeDto updateSize(SizeDto sizeDto) {
        var size = sizeMapper.toEntity(sizeDto);

        size = sizeRepository.save(size);

        return sizeMapper.toDto(size);
    }

    @Override
    public Boolean deleteSize(Long sizeId) {
        try {
            sizeRepository.deleteById(sizeId);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
