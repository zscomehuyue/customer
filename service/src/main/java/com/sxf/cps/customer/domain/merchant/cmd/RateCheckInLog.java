package com.sxf.cps.customer.domain.merchant.cmd;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class RateCheckInLog implements Serializable {
    private String uuid;
    private String factSn;
    private String factId;
    private String brandId;
    private String rateId;
    private String rateDesc;
    private String checkInUser;
    private Timestamp created;
    private Timestamp modified;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RateCheckInLog that = (RateCheckInLog) o;
        return Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
