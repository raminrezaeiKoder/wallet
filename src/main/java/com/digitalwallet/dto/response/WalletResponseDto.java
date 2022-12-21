package com.digitalwallet.dto.response;

import com.digitalwallet.generic.GenericDto;

public class WalletResponseDto extends GenericDto {

    private String walletCode;
    private Boolean activeStatus ;

    private String nationalCode ;
    public String getWalletCode() {
        return walletCode;
    }

    public void setWalletCode(String walletCode) {
        this.walletCode = walletCode;
    }

    public Boolean getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(Boolean activeStatus) {

        this.activeStatus = activeStatus;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }
}
