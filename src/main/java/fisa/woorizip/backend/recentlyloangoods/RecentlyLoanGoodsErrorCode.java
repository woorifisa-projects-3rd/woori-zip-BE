package fisa.woorizip.backend.recentlyloangoods;

import fisa.woorizip.backend.common.exception.errorcode.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum RecentlyLoanGoodsErrorCode implements ErrorCode {
   ;

   private final HttpStatus status;
   private final String message;

   RecentlyLoanGoodsErrorCode(HttpStatus status, String message) {
       this.status = status;
       this.message = message;
   }
}
