package fisa.woorizip.backend.loanchecklist.service;

import fisa.woorizip.backend.loanchecklist.repository.LoanCheckListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoanCheckListServiceImpl implements LoanCheckListService {
    private final LoanCheckListRepository loanCheckListRepository;
}
