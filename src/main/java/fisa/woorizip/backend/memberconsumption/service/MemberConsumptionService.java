package fisa.woorizip.backend.memberconsumption.service;

import fisa.woorizip.backend.member.controller.auth.MemberIdentity;
import fisa.woorizip.backend.memberconsumption.dto.response.ConsumptionAnalysisResponse;

public interface MemberConsumptionService {

    ConsumptionAnalysisResponse getConsumptionAnalysis(MemberIdentity memberIdentity);
}
