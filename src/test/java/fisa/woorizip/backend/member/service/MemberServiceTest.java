package fisa.woorizip.backend.member.service;

import fisa.woorizip.backend.common.exception.WoorizipDetailException;
import fisa.woorizip.backend.member.domain.Member;
import fisa.woorizip.backend.member.domain.Role;
import fisa.woorizip.backend.member.domain.Status;
import fisa.woorizip.backend.member.dto.request.ApprovalRequest;
import fisa.woorizip.backend.member.dto.request.RevokeApprovalRequest;
import fisa.woorizip.backend.member.repository.MemberRepository;
import fisa.woorizip.backend.support.fixture.MemberFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;
    @InjectMocks
    private MemberServiceImpl memberService;

    @Test
    void 자세한_예외를_던질_수_있다() {
        String[] message = {"word1", "word2"};
        assertThat(String.format("%s %s", message)).isEqualTo("word1 word2");
    }

    @Test
    void 관리자_권한을_승인할_수_있다() {
        Member admin1 = MemberFixture.builder().id(1L).role(Role.ADMIN).status(Status.PENDING_APPROVAL).build();
        Member admin2 = MemberFixture.builder().id(2L).role(Role.ADMIN).status(Status.PENDING_APPROVAL).build();

        ApprovalRequest approvalRequest = new ApprovalRequest(List.of(admin1.getId(), admin2.getId()));
        given(memberRepository.findAdminsInIds(approvalRequest.getAdmins())).willReturn(List.of(admin1, admin2));

        memberService.approve(approvalRequest);
        assertAll(
                () -> assertThat(admin1.getStatus()).isEqualTo(Status.APPROVED),
                () -> assertThat(admin1.getStatus()).isEqualTo(Status.APPROVED)
        );
    }

    @Test
    void 관리자_권한을_취소할_수_있다() {
        Member admin1 = MemberFixture.builder().id(1L).role(Role.ADMIN).status(Status.APPROVED).build();
        Member admin2 = MemberFixture.builder().id(2L).role(Role.ADMIN).status(Status.APPROVED).build();

        RevokeApprovalRequest revokeApprovalRequest = new RevokeApprovalRequest(List.of(admin1.getId(), admin2.getId()));
        given(memberRepository.findAdminsInIds(revokeApprovalRequest.getAdmins())).willReturn(List.of(admin1, admin2));

        memberService.revokeApprovals(revokeApprovalRequest);
        assertAll(
                () -> assertThat(admin1.getStatus()).isEqualTo(Status.REVOKED_APPROVAL),
                () -> assertThat(admin1.getStatus()).isEqualTo(Status.REVOKED_APPROVAL)
        );
    }

    @Test
    void 관리자_권한을_취소시_APPROVE가_아니면_예외를_던진다() {
        Member admin1 = MemberFixture.builder().id(1L).role(Role.ADMIN).status(Status.PENDING_APPROVAL).build();
        Member admin2 = MemberFixture.builder().id(2L).role(Role.ADMIN).status(Status.NOT_ADMIN).build();

        RevokeApprovalRequest revokeApprovalRequest = new RevokeApprovalRequest(List.of(admin1.getId(), admin2.getId()));
        given(memberRepository.findAdminsInIds(revokeApprovalRequest.getAdmins())).willReturn(List.of(admin1, admin2));

        assertThatThrownBy(() -> memberService.revokeApprovals(revokeApprovalRequest))
                .isExactlyInstanceOf(WoorizipDetailException.class);
    }

    @Test
    void 관리자_권한_취소시_없는_회원이면_예외를_던진다() {
        RevokeApprovalRequest revokeApprovalRequest = new RevokeApprovalRequest(List.of(1L));
        given(memberRepository.findAdminsInIds(revokeApprovalRequest.getAdmins())).willReturn(List.of());

        assertThatThrownBy(() -> memberService.revokeApprovals(revokeApprovalRequest))
                .isExactlyInstanceOf(WoorizipDetailException.class);
    }

    @Test
    void 관리자_권한_취소시_관리자가_아니면_예외를_던진다() {
        Member admin1 = MemberFixture.builder().id(1L).build();

        RevokeApprovalRequest revokeApprovalRequest = new RevokeApprovalRequest(List.of(admin1.getId()));
        given(memberRepository.findAdminsInIds(revokeApprovalRequest.getAdmins())).willReturn(List.of(admin1));

        assertThatThrownBy(() -> memberService.revokeApprovals(revokeApprovalRequest))
                .isExactlyInstanceOf(WoorizipDetailException.class);
    }
}