package dev.oleksa.sportshop.service.impl;

import dev.oleksa.sportshop.mapper.DiscountMapper;
import dev.oleksa.sportshop.dto.DiscountDto;
import dev.oleksa.sportshop.repository.DiscountRepository;
import dev.oleksa.sportshop.service.DiscountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepository discountRepository;
    private final DiscountMapper discountMapper;

    @Override
    public DiscountDto createDiscount(DiscountDto discountDto) {
        var discount = discountMapper.toEntity(discountDto);

        discount = discountRepository.save(discount);

        return discountMapper.toDto(discount);
    }

    @Override
    public DiscountDto readDiscount(Long discountId) {
        return discountMapper.toDto(
                discountRepository.findById(discountId)
                        .orElseThrow()
        );
    }

    @Override
    public DiscountDto updateDiscount(DiscountDto discountDto) {
        var discount = discountMapper.toEntity(discountDto);

        discount = discountRepository.save(discount);

        return discountMapper.toDto(discount);
    }

    @Override
    public Boolean deleteDiscount(Long discountId) {
        try {
            discountRepository.deleteById(discountId);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }
}
