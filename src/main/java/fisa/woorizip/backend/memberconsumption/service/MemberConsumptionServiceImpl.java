package fisa.woorizip.backend.memberconsumption.service;

import fisa.woorizip.backend.memberconsumption.repository.MemberConsumptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberConsumptionServiceImpl implements MemberConsumptionService {

    private final MemberConsumptionRepository memberConsumptionRepository;
}
