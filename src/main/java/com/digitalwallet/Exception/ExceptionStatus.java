package com.digitalwallet.Exception;

public enum ExceptionStatus {
    LOW_BALANCE("LOW_BALANCE") ,
    DEST_WALLET_IS_DEACTIVATED("DEST_WALLET_IS_DEACTIVATED"),
    SRC_WALLET_IS_DEACTIVATED("DEST_WALLET_IS_DEACTIVATED"),
    SAME_SRC_AND_DEST("SAME_SRC_AND_DEST");
    private String exceptionMessage ;

    ExceptionStatus(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}
